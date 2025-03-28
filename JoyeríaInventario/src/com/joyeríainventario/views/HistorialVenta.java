/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.joyeríainventario.views;

import com.joyeríainventario.dao.ClienteDAO;
import com.joyeríainventario.dao.VentaDAO;
import com.joyeríainventario.documentos.ExportarExcel;
import com.joyeríainventario.documentos.ExportarFactura;
import com.joyeríainventario.models.Cliente;
import com.joyeríainventario.models.Venta;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estebjavax.swing.JFrame {
 *
 * /**
 * Creates new form HistorialVenta
 */
public class HistorialVenta extends javax.swing.JFrame {

    /**
     * Creates new form HistorialVenta
     */
    private VentaDAO eboHistorial = new VentaDAO();

    private ExportarExcel exportarExcel = new ExportarExcel();

    VentaDAO ventaDAO = new VentaDAO();

    public HistorialVenta() throws Exception {
        initComponents();
        listarHistorial();
        configurarFiltroCliente();
        configurarFiltroFecha();
        
        setTitle("Historial de Venta - Generacion de archivos");
        setLocationRelativeTo(null);
        setResizable(false);
        //ventaDAO.filtrarVentaPorCliente("venta", "c.nombreCliente", idCliente, cboClientes);
    }

    public void listarHistorial() throws Exception {
        eboHistorial.listarVentas(tbHistorial);
    }

    private void configurarFiltroCliente() {
        txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarVentasPorCliente();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarVentasPorCliente();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarVentasPorCliente();
            }
        });
    }

    private void configurarFiltroFecha() {
        txtFiltroFecha.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarVentasPorFecha();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarVentasPorFecha();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarVentasPorFecha();
            }
        });
    }

    private void filtrarVentasPorFecha() {
        String filtroFechaStr = txtFiltro.getText().trim();

        LocalDate filtroFecha = null;

        try {
            // parseamos la fecha del campo de texto
            if (!filtroFechaStr.isEmpty()) {
                filtroFecha = LocalDate.parse(filtroFechaStr);
            }

            // si el campo de fecha esta vacio, lista todas las ventas 
            if (filtroFecha == null) {
                eboHistorial.listarVentas(tbHistorial);
                return;
            }

            // Filtrar ventas por fecha
            List<Venta> ventasFiltradas = eboHistorial.filtrarVentaPorFecha(filtroFecha);

            // Actualizar tabla con ventas filtradas
            DefaultTableModel model = (DefaultTableModel) tbHistorial.getModel();
            model.setRowCount(0); // Limpiar tabla

            for (Venta venta : ventasFiltradas) {
                Object[] rowData = {
                    venta.getIdVenta(),
                    venta.getCliente().getNombreCliente(),
                    venta.getJoya().getNombre(),
                    venta.getCantidad(),
                    venta.getPrecioTotal(),
                    venta.getFechaVenta()
                };
                model.addRow(rowData);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                    "Error al filtra ventas por fecha: "+ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void filtrarVentasPorCliente() {
        String filtroCliente = txtFiltro.getText().trim();

        try {
            // Si el campo está vacío, listar todas las ventas
            if (filtroCliente.isEmpty()) {
                eboHistorial.listarVentas(tbHistorial);
                return;
            }

            // Buscar cliente por nombre parcial
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarPorNombreCliente(filtroCliente);

            // Si encuentra el cliente, filtrar sus ventas
            if (cliente != null) {
                List<Venta> ventasFiltradas = eboHistorial.filtrarVentaPorCliente(cliente.getNombreCliente());

                // Actualizar tabla con ventas filtradas
                DefaultTableModel model = (DefaultTableModel) tbHistorial.getModel();
                model.setRowCount(0); // Limpiar tabla

                for (Venta venta : ventasFiltradas) {
                    Object[] rowData = {
                        venta.getIdVenta(),
                        venta.getCliente().getNombreCliente(),
                        venta.getJoya().getNombre(),
                        venta.getCantidad(),
                        venta.getPrecioTotal(),
                        venta.getFechaVenta()
                    };
                    model.addRow(rowData);
                }
            } else {
                // Si no encuentra cliente, limpiar tabla
                DefaultTableModel model = (DefaultTableModel) tbHistorial.getModel();
                model.setRowCount(0);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al filtrar ventas: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgGrupos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHistorial = new javax.swing.JTable();
        btnBorrarFiltros = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        rbtExcel = new javax.swing.JRadioButton();
        rbtFactura = new javax.swing.JRadioButton();
        txtFiltro = new javax.swing.JTextField();
        txtFiltroFecha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHistorialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHistorial);

        btnBorrarFiltros.setBackground(new java.awt.Color(153, 0, 153));
        btnBorrarFiltros.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrarFiltros.setText("Borrar Filtros");
        btnBorrarFiltros.setBorder(null);
        btnBorrarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarFiltrosActionPerformed(evt);
            }
        });

        btnExportar.setBackground(new java.awt.Color(0, 153, 51));
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar");
        btnExportar.setBorder(null);
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        rbgGrupos.add(rbtExcel);
        rbtExcel.setText("Excel");

        rbgGrupos.add(rbtFactura);
        rbtFactura.setText("Factura");

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtrar por cliente:");

        jLabel2.setText("Filtrar por fecha:");

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFiltroFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(txtFiltro, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(btnBorrarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(71, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(rbtExcel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFiltroFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtFactura)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHistorialMouseClicked
        try {
            // TODO add your handling code here:
            listarHistorial();
        } catch (Exception ex) {
            Logger.getLogger(HistorialVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbHistorialMouseClicked

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // exportamos en excel o pdf
        if (rbtExcel.isSelected()) {
            // EXPORTAMOS A EXCEL
            try {
                exportarExcel.exportarExcel(tbHistorial); // llamamos al m+etodo de exportación
                JOptionPane.showMessageDialog(this, "Excel Generado con éxito");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al exportar a Excel: " + e.getMessage());
            }
        } else if (rbtFactura.isSelected()) {
            try {
                ExportarFactura expotarFactura = new ExportarFactura();
                
                String nombreCliente = txtFiltro.getText().trim();
                List<Venta> ventas = eboHistorial.filtrarVentaPorCliente(nombreCliente);
                
                if(!ventas.isEmpty()){
                    expotarFactura.generarFactura(ventas, nombreCliente); // llamamos al m+etodo de exportación
                    JOptionPane.showMessageDialog(this, "Factura Generado con éxito");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron ventas para el cliente especificado");
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al exportar a Excel: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(HistorialVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnExportarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnBorrarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarFiltrosActionPerformed
        // TODO add your handling code here:
        txtFiltro.setText("");
        txtFiltroFecha.setText("");
        
    }//GEN-LAST:event_btnBorrarFiltrosActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(HistorialVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistorialVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistorialVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistorialVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HistorialVenta().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(HistorialVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarFiltros;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup rbgGrupos;
    private javax.swing.JRadioButton rbtExcel;
    private javax.swing.JRadioButton rbtFactura;
    private javax.swing.JTable tbHistorial;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtFiltroFecha;
    // End of variables declaration//GEN-END:variables
}
