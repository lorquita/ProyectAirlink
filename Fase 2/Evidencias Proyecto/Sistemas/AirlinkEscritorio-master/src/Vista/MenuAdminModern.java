package Vista;

import Modelo.Usuario;
import Modelo.ReporteViajes;
import javax.swing.*;
import java.awt.*;

public class MenuAdminModern extends javax.swing.JFrame {

    private Usuario usuario;
    private JLabel lblBienvenido, lblAvatar, lblLogo;
    private JButton btnUsuarios, btnEmpresas, btnDestinos, btnRutas, btnViajes, btnReportes, btnCerrar;

    public MenuAdminModern(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        this.setLocationRelativeTo(null);
        lblBienvenido.setText("Bienvenido, " + usuario.getNombre());
        lblAvatar.setText(usuario.getNombre().substring(0, 1).toUpperCase());
    }

    public MenuAdminModern() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de Administración - Airlink");
        setSize(750, 600);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(35, 22, 81));

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(25, 15, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblLogo = new JLabel(new ImageIcon(getClass().getResource("/Img/logo.png")));
        headerPanel.add(lblLogo, BorderLayout.WEST);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        userPanel.setOpaque(false);

        lblAvatar = new JLabel("A", SwingConstants.CENTER);
        lblAvatar.setOpaque(true);
        lblAvatar.setBackground(new Color(255, 255, 255, 80));
        lblAvatar.setForeground(Color.WHITE);
        lblAvatar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAvatar.setPreferredSize(new Dimension(40, 40));
        lblAvatar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setBackground(new Color(88, 63, 155));
        btnCerrar.addActionListener(e -> cerrarSesion());

        userPanel.add(lblAvatar);
        userPanel.add(btnCerrar);
        headerPanel.add(userPanel, BorderLayout.EAST);

        // CENTRO
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblBienvenido = new JLabel("Bienvenido", SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBienvenido.setForeground(Color.WHITE);

        btnUsuarios = crearBoton("Usuarios");
        btnEmpresas = crearBoton("Empresas");
        btnDestinos = crearBoton("Destinos");
        btnRutas = crearBoton("Rutas");
        btnViajes = crearBoton("Viajes");
        btnReportes = crearBoton("Reportes");

        gbc.gridy = 0; centerPanel.add(lblBienvenido, gbc);
        gbc.gridy++; centerPanel.add(btnUsuarios, gbc);
        gbc.gridy++; centerPanel.add(btnEmpresas, gbc);
        gbc.gridy++; centerPanel.add(btnDestinos, gbc);
        gbc.gridy++; centerPanel.add(btnRutas, gbc);
        gbc.gridy++; centerPanel.add(btnViajes, gbc);
        gbc.gridy++; centerPanel.add(btnReportes, gbc);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);

        // EVENTOS
        btnUsuarios.addActionListener(e -> new UsuariosVista().setVisible(true));
        btnEmpresas.addActionListener(e -> new EmpresasVista().setVisible(true));
        btnDestinos.addActionListener(e -> new DestinosVista().setVisible(true));
        btnRutas.addActionListener(e -> new RutasVista().setVisible(true));
        btnViajes.addActionListener(e -> new ViajesVista().setVisible(true));
        btnReportes.addActionListener(e -> abrirReportes());
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(35, 22, 81));
        boton.setPreferredSize(new Dimension(180, 45));
        return boton;
    }

    private void abrirReportes() {
        String ruta = System.getProperty("user.home") + "/Documents/reporte_viajes.pdf";
        new ReporteViajes().generarPDF(ruta);
        JOptionPane.showMessageDialog(this, "📄 Reporte generado en: " + ruta);
    }

    private void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            new Login().setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MenuAdminModern().setVisible(true));
    }
}
