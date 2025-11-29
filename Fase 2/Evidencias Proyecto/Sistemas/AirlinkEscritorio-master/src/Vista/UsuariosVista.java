package Vista;

import Modelo.Usuario;
import dao.UsuarioDAO;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UsuariosVista extends JFrame {

    private final UsuarioDAO dao = new UsuarioDAO();
    private DefaultTableModel modelo;

    private JTable tablaUsuarios;
    private JTextField txtId, txtNombre, txtCorreo, txtPass, txtBuscar;
    private JComboBox<String> cbRol;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnRefrescar, btnVolver;
    private JLabel lblTotal;

    private List<Usuario> listaUsuarios; // cache para el filtro

    public UsuariosVista() {
        initComponents();
        setLocationRelativeTo(null);
        listar();

        // selección de fila → autocompletar
        tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaUsuarios.getSelectedRow() != -1) {
                int fila = tablaUsuarios.getSelectedRow();
                txtId.setText(tablaUsuarios.getValueAt(fila, 0).toString());
                txtNombre.setText(tablaUsuarios.getValueAt(fila, 1).toString());
                txtCorreo.setText(tablaUsuarios.getValueAt(fila, 2).toString());
                txtPass.setText("");
                cbRol.setSelectedItem(tablaUsuarios.getValueAt(fila, 3).toString());
            }
        });
    }

    private void listar() {
        listaUsuarios = dao.listar();
        cargarTabla(listaUsuarios);
    }

    private void cargarTabla(List<Usuario> lista) {
        modelo.setRowCount(0);
        for (Usuario u : lista) {
            Object[] fila = {u.getId(), u.getNombre(), u.getCorreo(), u.getRol()};
            modelo.addRow(fila);
        }
        lblTotal.setText("Mostrando " + lista.size() + " usuario" + (lista.size() == 1 ? "" : "s"));
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPass.setText("");
        cbRol.setSelectedIndex(0);
    }

    private void initComponents() {
        setTitle("Gestión de Usuarios - Airlink");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 580);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 242, 245));
        add(panel);

        // ----------- PANEL SUPERIOR (BUSCADOR + VOLVER) -----------
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(35, 22, 81));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Subpanel izquierda: botón volver
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setBackground(new Color(35, 22, 81));

        btnVolver = new JButton("⬅ Volver");
        btnVolver.setBackground(new Color(108, 99, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setPreferredSize(new Dimension(100, 30));
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // hover
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(90, 80, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(108, 99, 255));
            }
        });

        btnVolver.addActionListener(e -> {
            this.dispose(); // cierra esta ventana
            new MenuAdminModern().setVisible(true); // 👉 cambia esto por la ventana a la que quieres volver
        });

        leftPanel.add(btnVolver);
        topPanel.add(leftPanel, BorderLayout.WEST);

        // Subpanel centro: buscador
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setBackground(new Color(35, 22, 81));

        JLabel lblBuscar = new JLabel("🔍 Buscar:");
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtBuscar = new JTextField(28);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBuscar.setToolTipText("Buscar por nombre o correo...");
        txtBuscar.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = txtBuscar.getText().toLowerCase();
                List<Usuario> filtrados = listaUsuarios.stream()
                        .filter(u -> u.getNombre().toLowerCase().contains(texto)
                                || u.getCorreo().toLowerCase().contains(texto))
                        .collect(Collectors.toList());
                cargarTabla(filtrados);
            }
        });

        searchPanel.add(lblBuscar);
        searchPanel.add(txtBuscar);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);

        // ----------- TABLA -----------
        modelo = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Nombre", "Correo", "Rol"});
        tablaUsuarios = new JTable(modelo);
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setSelectionBackground(new Color(51, 102, 204));
        tablaUsuarios.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tablaUsuarios);

        lblTotal = new JLabel("Mostrando 0 usuarios");
        lblTotal.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblTotal.setForeground(new Color(60, 60, 60));

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.add(scroll, BorderLayout.CENTER);
        tableContainer.add(lblTotal, BorderLayout.SOUTH);

        panel.add(tableContainer, BorderLayout.CENTER);

        // ----------- FORMULARIO INFERIOR -----------
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 242, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblCorreo = new JLabel("Correo:");
        JLabel lblPass = new JLabel("Contraseña:");
        JLabel lblRol = new JLabel("Rol:");

        txtId = new JTextField(15);
        txtId.setEditable(false);
        txtNombre = new JTextField(15);
        txtCorreo = new JTextField(15);
        txtPass = new JTextField(15);
        cbRol = new JComboBox<>(new String[]{"1 - Cliente", "2 - Usuario", "3 - Administrador"});

        btnAgregar = crearBoton("Agregar");
        btnActualizar = crearBoton("Actualizar");
        btnEliminar = crearBoton("Eliminar");
        btnRefrescar = crearBoton("Refrescar");

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(lblId, gbc);
        gbc.gridx = 1; formPanel.add(txtId, gbc);
        gbc.gridx = 2; formPanel.add(btnAgregar, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(lblNombre, gbc);
        gbc.gridx = 1; formPanel.add(txtNombre, gbc);
        gbc.gridx = 2; formPanel.add(btnActualizar, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(lblCorreo, gbc);
        gbc.gridx = 1; formPanel.add(txtCorreo, gbc);
        gbc.gridx = 2; formPanel.add(btnEliminar, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(lblPass, gbc);
        gbc.gridx = 1; formPanel.add(txtPass, gbc);
        gbc.gridx = 2; formPanel.add(btnRefrescar, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(lblRol, gbc);
        gbc.gridx = 1; formPanel.add(cbRol, gbc);

        panel.add(formPanel, BorderLayout.SOUTH);

        // ----------- ACCIONES CRUD -----------
        btnAgregar.addActionListener(e -> {
            Usuario u = new Usuario();
            u.setNombre(txtNombre.getText());
            u.setCorreo(txtCorreo.getText());
            u.setContraseña(txtPass.getText());
            u.setRol(cbRol.getSelectedItem().toString().split(" - ")[0]);

            if (dao.agregar(u)) {
                JOptionPane.showMessageDialog(this, "✅ Usuario agregado correctamente.");
                listar();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Error al agregar usuario. Ver consola.");
            }
        });

        btnActualizar.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para actualizar.");
                return;
            }
            Usuario u = new Usuario();
            u.setId(Integer.parseInt(txtId.getText()));
            u.setNombre(txtNombre.getText());
            u.setCorreo(txtCorreo.getText());
            u.setContraseña(txtPass.getText());
            u.setRol(cbRol.getSelectedItem().toString().split(" - ")[0]);

            if (dao.actualizar(u)) {
                JOptionPane.showMessageDialog(this, "✏️ Usuario actualizado correctamente.");
                listar();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Error al actualizar usuario.");
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                int id = (int) tablaUsuarios.getValueAt(fila, 0);
                if (dao.eliminar(id)) {
                    JOptionPane.showMessageDialog(this, "🗑️ Usuario eliminado.");
                    listar();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.");
            }
        });

        btnRefrescar.addActionListener(e -> listar());
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(35, 22, 81));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(110, 30));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 35, 120));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(35, 22, 81));
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsuariosVista().setVisible(true));
    }
}
