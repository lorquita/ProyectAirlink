package Vista;

import Modelo.Destino;
import dao.DestinoDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DestinosVista extends javax.swing.JFrame {

    private DestinoDAO dao = new DestinoDAO();
    private Destino destinoSeleccionado = new Destino();

    // Componentes
    private JTable tablaDestinos;
    private JTextField txtNombre, txtPrecio, txtCiudad, txtPais, txtImagen, txtDescripcion;
    private JCheckBox chkDestacado;
    private JButton btnAgregar, btnEditar, btnEliminar, btnVerViajes, btnVolver;

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public DestinosVista() {
        initComponentss(); // doble "s" para no interferir con NetBeans
        setLocationRelativeTo(null);
        listar();
    }

    // ===============================
    // LISTAR DESTINOS
    // ===============================
    private void listar() {
        DefaultTableModel model = (DefaultTableModel) tablaDestinos.getModel();
        model.setRowCount(0);
        List<Destino> lista = dao.listar();
        for (Destino d : lista) {
            model.addRow(new Object[]{
                d.getIdDestino(),
                d.getNombre(),
                d.getPrecio(),
                d.getCiudad(),
                d.getPais(),
                d.getImagen(),
                d.getDescripcion(),
                d.isDestacado() ? "Sí" : "No"
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCiudad.setText("");
        txtPais.setText("");
        txtImagen.setText("");
        txtDescripcion.setText("");
        chkDestacado.setSelected(false);
        destinoSeleccionado = new Destino();
    }

    // ===============================
    // EVENTOS CRUD
    // ===============================
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        Destino d = new Destino();
        d.setNombre(txtNombre.getText());
        d.setPrecio(Double.parseDouble(txtPrecio.getText()));
        d.setCiudad(txtCiudad.getText());
        d.setPais(txtPais.getText());
        d.setImagen(txtImagen.getText());
        d.setDescripcion(txtDescripcion.getText());
        d.setDestacado(chkDestacado.isSelected());

        if (dao.agregar(d)) {
            JOptionPane.showMessageDialog(this, "Destino agregado correctamente.");
            listar();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar destino.");
        }
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaDestinos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un destino para editar.");
            return;
        }

        destinoSeleccionado.setNombre(txtNombre.getText());
        destinoSeleccionado.setPrecio(Double.parseDouble(txtPrecio.getText()));
        destinoSeleccionado.setCiudad(txtCiudad.getText());
        destinoSeleccionado.setPais(txtPais.getText());
        destinoSeleccionado.setImagen(txtImagen.getText());
        destinoSeleccionado.setDescripcion(txtDescripcion.getText());
        destinoSeleccionado.setDestacado(chkDestacado.isSelected());

        if (dao.actualizar(destinoSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Destino actualizado correctamente.");
            listar();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar destino.");
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaDestinos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un destino para eliminar.");
            return;
        }

        int id = Integer.parseInt(tablaDestinos.getValueAt(fila, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar este destino?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Destino eliminado correctamente.");
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar destino (posibles dependencias).");
            }
        }
    }

    private void tablaDestinosMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaDestinos.getSelectedRow();
        destinoSeleccionado.setIdDestino(Integer.parseInt(tablaDestinos.getValueAt(fila, 0).toString()));
        txtNombre.setText(tablaDestinos.getValueAt(fila, 1).toString());
        txtPrecio.setText(tablaDestinos.getValueAt(fila, 2).toString());
        txtCiudad.setText(tablaDestinos.getValueAt(fila, 3).toString());
        txtPais.setText(tablaDestinos.getValueAt(fila, 4).toString());
        txtImagen.setText(tablaDestinos.getValueAt(fila, 5).toString());
        txtDescripcion.setText(tablaDestinos.getValueAt(fila, 6).toString());
        chkDestacado.setSelected(tablaDestinos.getValueAt(fila, 7).toString().equals("Sí"));
    }

    // ===============================
    // INTERFAZ GRÁFICA
    // ===============================
    private void initComponentss() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Destinos - Airlink");
        setSize(950, 620);
        getContentPane().setBackground(new Color(35, 22, 81));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Título
        JLabel lblTitulo = new JLabel("Gestión de Destinos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 400, 30));

        // Tabla
        JScrollPane scroll = new JScrollPane();
        tablaDestinos = new JTable();
        tablaDestinos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaDestinos.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Nombre", "Precio", "Ciudad", "País", "Imagen", "Descripción", "Destacado"
                }
        ));
        scroll.setViewportView(tablaDestinos);
        add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 880, 250));

        // Labels y campos
        JLabel lblNombre = crearLabel("Nombre:");
        JLabel lblPrecio = crearLabel("Precio:");
        JLabel lblCiudad = crearLabel("Ciudad:");
        JLabel lblPais = crearLabel("País:");
        JLabel lblImagen = crearLabel("Imagen:");
        JLabel lblDescripcion = crearLabel("Descripción:");

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtCiudad = new JTextField();
        txtPais = new JTextField();
        txtImagen = new JTextField();
        txtDescripcion = new JTextField();
        chkDestacado = new JCheckBox("Destacado");
        chkDestacado.setOpaque(false);
        chkDestacado.setForeground(Color.WHITE);

        add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 80, 25));
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 180, 30));

        add(lblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 80, 25));
        add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 120, 30));

        add(lblCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 80, 25));
        add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 320, 140, 30));

        add(lblPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 80, 25));
        add(txtPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 180, 30));

        add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 80, 25));
        add(txtImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 180, 30));

        add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 100, 25));
        add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 400, 30));

        add(chkDestacado, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 100, 30));

        // Botones
        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");
        btnVerViajes = crearBoton("Ver Viajes");
        btnVolver = crearBoton("Volver");

        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 130, 45));
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 130, 45));
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, 130, 45));
        add(btnVerViajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 490, 130, 45));
        add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 490, 130, 45));

        // Eventos
        tablaDestinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDestinosMouseClicked(evt);
            }
        });

        btnAgregar.addActionListener(evt -> btnAgregarActionPerformed(evt));
        btnEditar.addActionListener(evt -> btnEditarActionPerformed(evt));
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed(evt));

        btnVerViajes.addActionListener(evt -> {
            int fila = tablaDestinos.getSelectedRow();
            if (fila >= 0) {
                int idDestino = Integer.parseInt(tablaDestinos.getValueAt(fila, 0).toString());
                new ViajesVista(idDestino).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un destino para ver sus viajes.");
            }
        });

        btnVolver.addActionListener(evt -> {
            new MenuAdminModern().setVisible(true);
            dispose();
        });
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(35, 22, 81));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(240, 240, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
            }
        });
        return btn;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DestinosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DestinosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DestinosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DestinosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DestinosVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
