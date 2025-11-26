package Vista;

import Modelo.Ruta;
import dao.RutaDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RutasVista extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnEditar, btnEliminar, btnVolver;
    private RutaDAO rutaDAO;

    public RutasVista() {
        rutaDAO = new RutaDAO();
        initComponentss();
        cargarRutas();
        this.setLocationRelativeTo(null);
    }

    private void initComponentss() {
        setTitle("Gestión de Rutas - Airlink");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JLabel lblTitulo = new JLabel("Gestión de Rutas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(35, 22, 81));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID", "Origen", "Destino", "Distancia (Km)", "Duración (Min)", "Activo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior (botones)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");
        btnVolver = crearBoton("Volver al Menú");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(e -> agregarRuta());
        btnEditar.addActionListener(e -> editarRuta());
        btnEliminar.addActionListener(e -> eliminarRuta());
        btnVolver.addActionListener(e -> volverMenu());
    }

    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(new Color(88, 63, 155));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(130, 35));
        return b;
    }

    private void cargarRutas() {
        modeloTabla.setRowCount(0);
        List<Ruta> rutas = rutaDAO.listar();
        for (Ruta r : rutas) {
            modeloTabla.addRow(new Object[]{
                    r.getIdRuta(),
                    r.getOrigen(),
                    r.getDestino(),
                    r.getDistanciaKm(),
                    r.getDuracionEstimadaMin(),
                    r.isActivo() ? "Sí" : "No"
            });
        }
    }

    private void agregarRuta() {
        JTextField txtIdOrigen = new JTextField();
        JTextField txtIdDestino = new JTextField();
        JTextField txtDistancia = new JTextField();
        JTextField txtDuracion = new JTextField();

        Object[] campos = {
                "ID Terminal Origen:", txtIdOrigen,
                "ID Terminal Destino:", txtIdDestino,
                "Distancia (Km):", txtDistancia,
                "Duración Estimada (Min):", txtDuracion
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Agregar Nueva Ruta", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                Ruta r = new Ruta();
                r.setIdTerminalOrigen(Integer.parseInt(txtIdOrigen.getText()));
                r.setIdTerminalDestino(Integer.parseInt(txtIdDestino.getText()));
                r.setDistanciaKm(Double.parseDouble(txtDistancia.getText()));
                r.setDuracionEstimadaMin(Integer.parseInt(txtDuracion.getText()));
                r.setActivo(true);

                if (rutaDAO.agregar(r)) {
                    JOptionPane.showMessageDialog(this, "Ruta agregada correctamente.");
                    cargarRutas();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar ruta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarRuta() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una ruta para editar.");
            return;
        }

        int idRuta = (int) modeloTabla.getValueAt(fila, 0);
        Ruta existente = rutaDAO.obtenerPorId(idRuta);
        if (existente == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la ruta.");
            return;
        }

        JTextField txtIdOrigen = new JTextField(String.valueOf(existente.getIdTerminalOrigen()));
        JTextField txtIdDestino = new JTextField(String.valueOf(existente.getIdTerminalDestino()));
        JTextField txtDistancia = new JTextField(String.valueOf(existente.getDistanciaKm()));
        JTextField txtDuracion = new JTextField(String.valueOf(existente.getDuracionEstimadaMin()));
        JCheckBox chkActivo = new JCheckBox("Activa", existente.isActivo());

        Object[] campos = {
                "ID Terminal Origen:", txtIdOrigen,
                "ID Terminal Destino:", txtIdDestino,
                "Distancia (Km):", txtDistancia,
                "Duración Estimada (Min):", txtDuracion,
                chkActivo
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Editar Ruta", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                existente.setIdTerminalOrigen(Integer.parseInt(txtIdOrigen.getText()));
                existente.setIdTerminalDestino(Integer.parseInt(txtIdDestino.getText()));
                existente.setDistanciaKm(Double.parseDouble(txtDistancia.getText()));
                existente.setDuracionEstimadaMin(Integer.parseInt(txtDuracion.getText()));
                existente.setActivo(chkActivo.isSelected());

                if (rutaDAO.actualizar(existente)) {
                    JOptionPane.showMessageDialog(this, "Ruta actualizada correctamente.");
                    cargarRutas();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar ruta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Datos inválidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarRuta() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una ruta para eliminar.");
            return;
        }

        int idRuta = (int) modeloTabla.getValueAt(fila, 0);
        int opcion = JOptionPane.showConfirmDialog(this, "¿Eliminar la ruta seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            if (rutaDAO.eliminar(idRuta)) {
                JOptionPane.showMessageDialog(this, "Ruta eliminada correctamente.");
                cargarRutas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la ruta. Puede tener viajes asociados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void volverMenu() {
        new MenuAdminModern().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RutasVista().setVisible(true));
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents
}
    /**
     * @param args the command line arguments
     */
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

