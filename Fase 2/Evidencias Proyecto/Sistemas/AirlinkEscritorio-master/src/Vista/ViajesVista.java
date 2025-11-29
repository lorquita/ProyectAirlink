package Vista;

import dao.ViajeDAO;
import dao.DestinoDAO;
import Modelo.Viaje;
import Modelo.Destino;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViajesVista extends JFrame {

    private int idDestino = -1;
    private String nombreDestino = "";

    private JPanel panelContenido;
    private JTable tablaViajes;
    private JScrollPane scrollTabla;
    private JLabel lblTitulo;
    private JButton btnAgregar, btnEditar, btnEliminar, btnVolver;

    // ==============================
    // CONSTRUCTORES
    // ==============================
    public ViajesVista(int idDestino) {
        this.idDestino = idDestino;
        initComponentsExtra();
    }

    public ViajesVista() {
        initComponentsExtra();
    }

    // ==============================
    // INTERFAZ GRÁFICA PERSONALIZADA
    // ==============================
    private void initComponentsExtra() {
        // Panel principal
        panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(new Color(248, 249, 255));
        panelContenido.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Título
        lblTitulo = new JLabel("✈ Todos los viajes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(108, 99, 255));
        panelContenido.add(lblTitulo, BorderLayout.NORTH);

        // Tabla
        tablaViajes = new JTable();
        tablaViajes.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Salida", "Llegada", "Estado", "Destino"}
        ));
        tablaViajes.setRowHeight(26);
        tablaViajes.setSelectionBackground(new Color(108, 99, 255));
        tablaViajes.setSelectionForeground(Color.WHITE);
        tablaViajes.getTableHeader().setBackground(new Color(230, 230, 255));
        tablaViajes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        scrollTabla = new JScrollPane(tablaViajes);
        panelContenido.add(scrollTabla, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(new Color(248, 249, 255));

        // Subpanel izquierda (volver)
        JPanel panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelIzq.setBackground(new Color(248, 249, 255));

        btnVolver = new JButton("⬅ Volver");
        estilizarBoton(btnVolver);
        btnVolver.addActionListener(evt -> btnVolverActionPerformed(evt));
        panelIzq.add(btnVolver);

        // Subpanel centro (acciones CRUD)
        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelCentro.setBackground(new Color(248, 249, 255));

        btnAgregar = new JButton("➕ Agregar");
        btnEditar = new JButton("✏️ Editar");
        btnEliminar = new JButton("🗑️ Eliminar");

        estilizarBoton(btnAgregar);
        estilizarBoton(btnEditar);
        estilizarBoton(btnEliminar);

        btnAgregar.addActionListener(evt -> btnAgregarActionPerformed(evt));
        btnEditar.addActionListener(evt -> btnEditarActionPerformed(evt));
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed(evt));

        panelCentro.add(btnAgregar);
        panelCentro.add(btnEditar);
        panelCentro.add(btnEliminar);

        // Ensamblar paneles
        panelBotones.add(panelIzq, BorderLayout.WEST);
        panelBotones.add(panelCentro, BorderLayout.CENTER);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);

        // Mostrar panel
        this.setContentPane(panelContenido);
        this.setTitle("Gestión de Viajes");
        this.setSize(850, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.revalidate();
        this.repaint();

        // Cargar datos
        cargarNombreDestino();
        cargarViajes();
    }

    // ==============================
    // ESTILOS
    // ==============================
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(108, 99, 255));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(90, 80, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(108, 99, 255));
            }
        });
    }

    // ==============================
    // FUNCIONALIDAD
    // ==============================
    private void cargarNombreDestino() {
        if (idDestino <= 0) {
            lblTitulo.setText("✈ Todos los viajes");
            return;
        }
        DestinoDAO dao = new DestinoDAO();
        List<Destino> destinos = dao.listar();
        for (Destino d : destinos) {
            if (d.getIdDestino() == idDestino) {
                nombreDestino = d.getNombre();
                lblTitulo.setText("✈ Viajes de " + nombreDestino);
                break;
            }
        }
    }

    private void cargarViajes() {
        ViajeDAO dao = new ViajeDAO();
        DefaultTableModel modelo = (DefaultTableModel) tablaViajes.getModel();
        modelo.setRowCount(0);

        List<Viaje> lista;
        if (idDestino > 0) {
            lista = dao.listarPorDestino(idDestino);
        } else {
            lista = dao.listar();
        }

        for (Viaje v : lista) {
            modelo.addRow(new Object[]{
                v.getIdViaje(),
                v.getSalida(),
                v.getLlegada(),
                v.getEstado(),
                v.getIdDestino()
            });
        }
    }

    // ==============================
    // EVENTOS
    // ==============================
// ==============================
// BOTONES AGREGAR / EDITAR
// ==============================

private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
    // Abrir el formulario en modo "Agregar"
    this.dispose();
    new ViajeFormularioVista(null).setVisible(true);
}

private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
    int fila = tablaViajes.getSelectedRow();
    if (fila >= 0) {
        // Crear objeto Viaje con los datos seleccionados
        Viaje v = new Viaje();
        v.setIdViaje(Integer.parseInt(tablaViajes.getValueAt(fila, 0).toString()));
        v.setSalida(java.sql.Timestamp.valueOf(tablaViajes.getValueAt(fila, 1).toString()));
        v.setLlegada(java.sql.Timestamp.valueOf(tablaViajes.getValueAt(fila, 2).toString()));
        v.setEstado(tablaViajes.getValueAt(fila, 3).toString());
        v.setIdDestino(Integer.parseInt(tablaViajes.getValueAt(fila, 4).toString()));

        // Abrir el formulario en modo edición
        this.dispose();
        new ViajeFormularioVista(v).setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un viaje para editar.");
    }
}


    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaViajes.getSelectedRow();
        if (fila >= 0) {
            int idViaje = Integer.parseInt(tablaViajes.getValueAt(fila, 0).toString());
            ViajeDAO dao = new ViajeDAO();
            if (dao.eliminar(idViaje)) {
                JOptionPane.showMessageDialog(this, "✅ Viaje eliminado correctamente.");
                cargarViajes();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al eliminar el viaje.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un viaje primero.");
        }
    }

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); // Cierra esta ventana
        new MenuAdminModern().setVisible(true); // Abre la ventana de menu admin 
    }

    // ==============================
    // MAIN (para testeo independiente)
    // ==============================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViajesVista().setVisible(true));
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

