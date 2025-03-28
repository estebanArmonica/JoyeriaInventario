/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.joyeríainventario.views;

import com.joyeríainventario.dao.JoyaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esteb
 */
public class Menu extends javax.swing.JFrame {

    // llamamos al dao de joya
    JoyaDAO joyaDAO;
    
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        joyaDAO = new JoyaDAO();
        
        // definimos un titulo y el proyecto al centro
        setTitle("Menu de Joyeria de Inventario y Ventas");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnMenu = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        PbAgregarJoya = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        PbRealizarCompras = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JpnMenu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JpnMenuLayout = new javax.swing.GroupLayout(JpnMenu);
        JpnMenu.setLayout(JpnMenuLayout);
        JpnMenuLayout.setHorizontalGroup(
            JpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
        JpnMenuLayout.setVerticalGroup(
            JpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        jMenu1.setText("Inventario");

        PbAgregarJoya.setText("Formulario Joyas");
        PbAgregarJoya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbAgregarJoyaActionPerformed(evt);
            }
        });
        jMenu1.add(PbAgregarJoya);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ventas");

        PbRealizarCompras.setText("Realizar Compra");
        PbRealizarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PbRealizarComprasActionPerformed(evt);
            }
        });
        jMenu2.add(PbRealizarCompras);

        jMenuItem1.setText("Historial de Ventas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PbAgregarJoyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbAgregarJoyaActionPerformed
        // TODO add your handling code here:
        FrmFormularioJoyas formulario;
        try {
            formulario = new FrmFormularioJoyas();
            formulario.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PbAgregarJoyaActionPerformed

    private void PbRealizarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PbRealizarComprasActionPerformed
        // TODO add your handling code here:
        FrmCompras compra;
        try {
            compra = new FrmCompras();
            compra.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_PbRealizarComprasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        HistorialVenta historial;
        try {
            historial = new HistorialVenta();
            historial.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpnMenu;
    private javax.swing.JMenuItem PbAgregarJoya;
    private javax.swing.JMenuItem PbRealizarCompras;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
