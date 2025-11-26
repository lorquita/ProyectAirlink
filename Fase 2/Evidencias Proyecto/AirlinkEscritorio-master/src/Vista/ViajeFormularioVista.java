package Vista;

import Modelo.Ruta;
import Modelo.Viaje;
import Modelo.EmpresaEquipo;
import Modelo.Destino;
import dao.ViajeDAO;
import dao.RutaDAO;
import dao.EmpresaEquipoDAO;
import dao.DestinoDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

public class ViajeFormularioVista extends JFrame {

    private JComboBox<String> cbRuta;
    private JComboBox<String> cbEquipo;
    private JComboBox<String> cbDestino;
    private JTextField txtSalida, txtLlegada, txtEstado;
    private JButton btnGuardar, btnVolver;

    private ViajeDAO viajeDAO;
    private RutaDAO rutaDAO;
    private EmpresaEquipoDAO equipoDAO;
    private DestinoDAO destinoDAO;
    private Viaje viajeEditando;

    public ViajeFormularioVista() {
        this(null);
    }

    public ViajeFormularioVista(Viaje viaje) {
        this.viajeEditando = viaje;
        viajeDAO = new ViajeDAO();
        rutaDAO = new RutaDAO();
        equipoDAO = new EmpresaEquipoDAO();
        destinoDAO = new DestinoDAO();
        initComponentesExtra();
        setLocationRelativeTo(null);
        if (viajeEditando != null) {
            cargarDatos();
        }
    }

    private void initComponentesExtra() {
        setTitle("Formulario de Viaje - Airlink");
        setSize(520, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(35, 22, 81));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Formulario de Viaje");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);
        gbc.gridwidth = 1;

        // Ruta
        JLabel lblRuta = new JLabel("Ruta:");
        lblRuta.setForeground(Color.WHITE);
        cbRuta = new JComboBox<>();
        llenarComboRutas();
        gbc.gridy = 1; gbc.gridx = 0; add(lblRuta, gbc);
        gbc.gridx = 1; add(cbRuta, gbc);

        // Equipo
        JLabel lblEquipo = new JLabel("Equipo:");
        lblEquipo.setForeground(Color.WHITE);
        cbEquipo = new JComboBox<>();
        llenarComboEquipos();
        gbc.gridy = 2; gbc.gridx = 0; add(lblEquipo, gbc);
        gbc.gridx = 1; add(cbEquipo, gbc);

        // Destino
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setForeground(Color.WHITE);
        cbDestino = new JComboBox<>();
        llenarComboDestinos();
        gbc.gridy = 3; gbc.gridx = 0; add(lblDestino, gbc);
        gbc.gridx = 1; add(cbDestino, gbc);

        // Salida
        JLabel lblSalida = new JLabel("Salida (yyyy-MM-dd HH:mm:ss):");
        lblSalida.setForeground(Color.WHITE);
        txtSalida = new JTextField();
        gbc.gridy = 4; gbc.gridx = 0; add(lblSalida, gbc);
        gbc.gridx = 1; add(txtSalida, gbc);

        // Llegada
        JLabel lblLlegada = new JLabel("Llegada (yyyy-MM-dd HH:mm:ss):");
        lblLlegada.setForeground(Color.WHITE);
        txtLlegada = new JTextField();
        gbc.gridy = 5; gbc.gridx = 0; add(lblLlegada, gbc);
        gbc.gridx = 1; add(txtLlegada, gbc);

        // Estado
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setForeground(Color.WHITE);
        txtEstado = new JTextField("programado");
        gbc.gridy = 6; gbc.gridx = 0; add(lblEstado, gbc);
        gbc.gridx = 1; add(txtEstado, gbc);

        // Botones
        btnGuardar = crearBoton("Guardar");
        btnVolver = crearBoton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2;
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> guardarViaje());
        btnVolver.addActionListener(e -> volver());
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(35, 22, 81));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 40));
        return btn;
    }

    private void llenarComboRutas() {
        cbRuta.removeAllItems();
        List<Ruta> rutas = rutaDAO.listar();
        for (Ruta r : rutas) {
            cbRuta.addItem(r.getIdRuta() + " - " + r.getOrigen() + " → " + r.getDestino());
        }
    }

    private void llenarComboEquipos() {
        cbEquipo.removeAllItems();
        List<EmpresaEquipo> equipos = equipoDAO.listar();
        for (EmpresaEquipo e : equipos) {
            cbEquipo.addItem(e.getIdEquipo() + " - " + e.getModelo());
        }
    }

    private void llenarComboDestinos() {
        cbDestino.removeAllItems();
        List<Destino> destinos = destinoDAO.listar();
        for (Destino d : destinos) {
            cbDestino.addItem(d.getIdDestino() + " - " + d.getNombre() + " (" + d.getCiudad() + ")");
        }
    }

    private void cargarDatos() {
        cbRuta.setSelectedItem(viajeEditando.getIdRuta() + " - ");
        cbEquipo.setSelectedItem(viajeEditando.getIdEquipo() + " - ");
        cbDestino.setSelectedItem(viajeEditando.getIdDestino() + " - ");
        txtSalida.setText(viajeEditando.getSalida().toString());
        txtLlegada.setText(viajeEditando.getLlegada().toString());
        txtEstado.setText(viajeEditando.getEstado());
    }

    private void guardarViaje() {
        try {
            int idRuta = Integer.parseInt(cbRuta.getSelectedItem().toString().split(" - ")[0]);
            int idEquipo = Integer.parseInt(cbEquipo.getSelectedItem().toString().split(" - ")[0]);
            int idDestino = Integer.parseInt(cbDestino.getSelectedItem().toString().split(" - ")[0]);

            Timestamp salida = Timestamp.valueOf(txtSalida.getText());
            Timestamp llegada = Timestamp.valueOf(txtLlegada.getText());
            String estado = txtEstado.getText();

            if (viajeEditando == null) {
                Viaje nuevo = new Viaje();
                nuevo.setIdRuta(idRuta);
                nuevo.setIdEquipo(idEquipo);
                nuevo.setIdDestino(idDestino);
                nuevo.setSalida(salida);
                nuevo.setLlegada(llegada);
                nuevo.setEstado(estado);
                if (viajeDAO.agregar(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Viaje agregado correctamente.");
                    volver();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar viaje.");
                }
            } else {
                viajeEditando.setIdRuta(idRuta);
                viajeEditando.setIdEquipo(idEquipo);
                viajeEditando.setIdDestino(idDestino);
                viajeEditando.setSalida(salida);
                viajeEditando.setLlegada(llegada);
                viajeEditando.setEstado(estado);

                if (viajeDAO.actualizar(viajeEditando)) {
                    JOptionPane.showMessageDialog(this, "Viaje actualizado correctamente.");
                    volver();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar viaje.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        new ViajesVista().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViajeFormularioVista().setVisible(true));
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




    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

