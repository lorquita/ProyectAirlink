package Vista;

import dao.EmpresaDAO;
import Modelo.Empresa;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmpresasVista extends javax.swing.JFrame {

    EmpresaDAO dao = new EmpresaDAO();
    Empresa empresaSeleccionada = new Empresa();

    // ======= CONSTRUCTOR =======
    public EmpresasVista() {
        initComponentss(); // ← ojo: con doble "s"
        setLocationRelativeTo(null);
        listar();
    }

    // ======= LISTAR =======
    private void listar() {
        DefaultTableModel model = (DefaultTableModel) tablaEmpresas.getModel();
        model.setRowCount(0);
        List<Empresa> lista = dao.listar();
        for (Empresa e : lista) {
            model.addRow(new Object[]{
                e.getIdEmpresa(),
                e.getNombreEmpresa(),
                e.getTipoEmpresa(),
                e.getLogo(),
                e.getDescripcion(),
                e.getSitioWeb(),
                e.isActivo() ? "Sí" : "No"
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTipo.setText("");
        txtLogo.setText("");
        txtDescripcion.setText("");
        txtSitio.setText("");
        chkActivo.setSelected(false);
        empresaSeleccionada = new Empresa();
    }

    // ======= EVENTOS =======
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        Empresa e = new Empresa();
        e.setNombreEmpresa(txtNombre.getText());
        e.setTipoEmpresa(txtTipo.getText());
        e.setLogo(txtLogo.getText());
        e.setDescripcion(txtDescripcion.getText());
        e.setSitioWeb(txtSitio.getText());
        e.setActivo(chkActivo.isSelected());

        if (dao.agregar(e)) {
            JOptionPane.showMessageDialog(this, "✅ Empresa agregada correctamente");
            listar();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al agregar empresa");
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaEmpresas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una empresa para actualizar");
            return;
        }

        empresaSeleccionada.setNombreEmpresa(txtNombre.getText());
        empresaSeleccionada.setTipoEmpresa(txtTipo.getText());
        empresaSeleccionada.setLogo(txtLogo.getText());
        empresaSeleccionada.setDescripcion(txtDescripcion.getText());
        empresaSeleccionada.setSitioWeb(txtSitio.getText());
        empresaSeleccionada.setActivo(chkActivo.isSelected());

        if (dao.actualizar(empresaSeleccionada)) {
            JOptionPane.showMessageDialog(this, "✅ Empresa actualizada correctamente");
            listar();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al actualizar empresa");
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaEmpresas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una empresa para eliminar");
            return;
        }

        int id = Integer.parseInt(tablaEmpresas.getValueAt(fila, 0).toString());
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta empresa?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
            if (dao.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "🗑️ Empresa eliminada correctamente");
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al eliminar empresa");
            }
        }
    }

    private void tablaEmpresasMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaEmpresas.getSelectedRow();
        empresaSeleccionada.setIdEmpresa(Integer.parseInt(tablaEmpresas.getValueAt(fila, 0).toString()));
        txtNombre.setText(tablaEmpresas.getValueAt(fila, 1).toString());
        txtTipo.setText(tablaEmpresas.getValueAt(fila, 2).toString());
        txtLogo.setText(tablaEmpresas.getValueAt(fila, 3).toString());
        txtDescripcion.setText(tablaEmpresas.getValueAt(fila, 4).toString());
        txtSitio.setText(tablaEmpresas.getValueAt(fila, 5) != null ? tablaEmpresas.getValueAt(fila, 5).toString() : "");
        chkActivo.setSelected(tablaEmpresas.getValueAt(fila, 6).toString().equals("Sí"));
    }

    // ======= DISEÑO DE INTERFAZ =======
    private void initComponentss() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Empresas - Airlink");
        setSize(900, 600);
        getContentPane().setBackground(new java.awt.Color(35, 22, 81));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // ===== BOTÓN VOLVER =====
        JButton btnVolver = new JButton("⬅ Volver");
        btnVolver.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(108, 99, 255));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(90, 80, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolver.setBackground(new Color(108, 99, 255));
            }
        });

        btnVolver.addActionListener(e -> {
            this.dispose();
            new MenuAdminModern().setVisible(true);
        });

        add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 30));

        // ===== TÍTULO =====
        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("Gestión de Empresas");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblTitulo.setForeground(java.awt.Color.WHITE);
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 300, 30));

        // ===== TABLA =====
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpresas = new javax.swing.JTable();
        tablaEmpresas.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        tablaEmpresas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Nombre", "Tipo", "Logo", "Descripción", "Sitio Web", "Activa"
                }
        ));
        jScrollPane1.setViewportView(tablaEmpresas);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 830, 250));

        // ===== CAMPOS =====
        javax.swing.JLabel lblNombre = new javax.swing.JLabel("Nombre:");
        javax.swing.JLabel lblTipo = new javax.swing.JLabel("Tipo:");
        javax.swing.JLabel lblLogo = new javax.swing.JLabel("Logo:");
        javax.swing.JLabel lblDescripcion = new javax.swing.JLabel("Descripción:");
        javax.swing.JLabel lblSitio = new javax.swing.JLabel("Sitio Web:");

        lblNombre.setForeground(Color.WHITE);
        lblTipo.setForeground(Color.WHITE);
        lblLogo.setForeground(Color.WHITE);
        lblDescripcion.setForeground(Color.WHITE);
        lblSitio.setForeground(Color.WHITE);

        txtNombre = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        txtLogo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtSitio = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox("Activa");
        chkActivo.setOpaque(false);
        chkActivo.setForeground(Color.WHITE);

        add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 80, 25));
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 180, 30));

        add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 60, 25));
        add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 180, 30));

        add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 90, 25));
        add(txtLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, 210, 30));

        add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 100, 25));
        add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 390, 30));

        add(lblSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 80, 25));
        add(txtSitio, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 190, 30));

        add(chkActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, 100, 30));

        // ===== BOTONES =====
        btnAgregar = crearBoton("Agregar");
        btnActualizar = crearBoton("Actualizar");
        btnEliminar = crearBoton("Eliminar");
        btnLimpiar = crearBoton("Limpiar");

        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 140, 45));
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, 140, 45));
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 460, 140, 45));
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, 140, 45));

        // ===== EVENTOS =====
        tablaEmpresas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpresasMouseClicked(evt);
            }
        });

        btnAgregar.addActionListener(evt -> btnAgregarActionPerformed(evt));
        btnActualizar.addActionListener(evt -> btnActualizarActionPerformed(evt));
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed(evt));
        btnLimpiar.addActionListener(evt -> limpiarCampos());
    }

    // ======= ESTILO DE BOTONES =======
    private javax.swing.JButton crearBoton(String texto) {
        javax.swing.JButton btn = new javax.swing.JButton(texto);
        btn.setBackground(new java.awt.Color(255, 255, 255));
        btn.setForeground(new java.awt.Color(35, 22, 81));
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new java.awt.Color(240, 240, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
            }
        });
        return btn;
    }

    // ======= VARIABLES =======
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEmpresas;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtLogo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSitio;
    private javax.swing.JTextField txtTipo;



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
    }// </editor-fold>//GEN-END:initComponents
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

