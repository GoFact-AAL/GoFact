/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.presentacion;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author camm
 */
public class DialogRestaurarContrasena extends javax.swing.JDialog {

    /**
     * Creates new form DialogRestaurarContrasena
     */
    public DialogRestaurarContrasena(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRestaurarContrasena = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtResp1 = new javax.swing.JTextField();
        cmbPregunta1 = new javax.swing.JComboBox();
        txtResp2 = new javax.swing.JTextField();
        cmbPregunta2 = new javax.swing.JComboBox();
        lblPreguntaSeguridad1 = new javax.swing.JLabel();
        lblRespuesta1 = new javax.swing.JLabel();
        lblPreguntaSeguridad2 = new javax.swing.JLabel();
        lblRespuesta2 = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        btnVerificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlRestaurarContrasena.setBorder(javax.swing.BorderFactory.createTitledBorder("Restaurar Contraseña"));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtResp1.setEnabled(false);

        cmbPregunta1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "¿Cuál es tu película favorita?", "¿Nombre de tu primera mascota?", "¿Cumpleaños de tu mamá?", "¿Cuál es tu libro preferido?" }));
        cmbPregunta1.setEnabled(false);

        txtResp2.setEnabled(false);

        cmbPregunta2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "¿Cuál es tu película favorita?", "¿Nombre de tu primera mascota?", "¿Cumpleaños de tu mamá?", "¿Cuál es tu libro preferido?" }));
        cmbPregunta2.setEnabled(false);

        lblPreguntaSeguridad1.setText("Pregunta de seguridad:");

        lblRespuesta1.setText("Respuesta:");

        lblPreguntaSeguridad2.setText("Pregunta de seguridad:");

        lblRespuesta2.setText("Respuesta:");

        lblCedula.setText("Cedula Identidad:");

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRestaurarContrasenaLayout = new javax.swing.GroupLayout(pnlRestaurarContrasena);
        pnlRestaurarContrasena.setLayout(pnlRestaurarContrasenaLayout);
        pnlRestaurarContrasenaLayout.setHorizontalGroup(
            pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestaurarContrasenaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPreguntaSeguridad1)
                    .addComponent(lblRespuesta1)
                    .addComponent(lblPreguntaSeguridad2)
                    .addComponent(lblRespuesta2)
                    .addComponent(lblCedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 38, Short.MAX_VALUE)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtResp1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRestaurarContrasenaLayout.createSequentialGroup()
                        .addComponent(txtCedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerificar))
                    .addComponent(cmbPregunta1, 0, 269, Short.MAX_VALUE)
                    .addComponent(cmbPregunta2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtResp2))
                .addContainerGap())
            .addGroup(pnlRestaurarContrasenaLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnAceptar)
                .addGap(49, 49, 49)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRestaurarContrasenaLayout.setVerticalGroup(
            pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRestaurarContrasenaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificar))
                .addGap(29, 29, 29)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPregunta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreguntaSeguridad1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRespuesta1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPregunta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreguntaSeguridad2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRespuesta2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(pnlRestaurarContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRestaurarContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRestaurarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(DialogRestaurarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogRestaurarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogRestaurarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogRestaurarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogRestaurarContrasena dialog = new DialogRestaurarContrasena(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox cmbPregunta1;
    private javax.swing.JComboBox cmbPregunta2;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblPreguntaSeguridad1;
    private javax.swing.JLabel lblPreguntaSeguridad2;
    private javax.swing.JLabel lblRespuesta1;
    private javax.swing.JLabel lblRespuesta2;
    private javax.swing.JPanel pnlRestaurarContrasena;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtResp1;
    private javax.swing.JTextField txtResp2;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnVerificar() {
        return btnVerificar;
    }

    public JComboBox getCmbPregunta1() {
        return cmbPregunta1;
    }

    public JComboBox getCmbPregunta2() {
        return cmbPregunta2;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public JTextField getTxtResp1() {
        return txtResp1;
    }

    public JTextField getTxtResp2() {
        return txtResp2;
    }

    public void mostrarMensaje (String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    
    
}
