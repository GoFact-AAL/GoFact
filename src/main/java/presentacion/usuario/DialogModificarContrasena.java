/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.usuario;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author root
 */
public class DialogModificarContrasena extends javax.swing.JDialog {

    /**
     * Creates new form DialogModificarContrasena
     */
    public DialogModificarContrasena(java.awt.Frame parent, boolean modal) {
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

        pnlEdicionContrasena = new javax.swing.JPanel();
        lblContrasena = new javax.swing.JLabel();
        lblNuevaContra = new javax.swing.JLabel();
        lblConfirmacion = new javax.swing.JLabel();
        passContrasena = new javax.swing.JPasswordField();
        pssNuevaContra = new javax.swing.JPasswordField();
        passConfirmacion = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlEdicionContrasena.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Información"));
        pnlEdicionContrasena.setName("Modificar Contraseña"); // NOI18N

        lblContrasena.setText("Contraseña actual");

        lblNuevaContra.setText("Nueva contraseña");

        lblConfirmacion.setText("Confirmar contraseña");

        passContrasena.setText("jPasswordField1");

        pssNuevaContra.setText("jPasswordField2");

        passConfirmacion.setText("jPasswordField3");

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aceptar.png"))); // NOI18N
        btnAceptar.setText("Aceptar");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout pnlEdicionContrasenaLayout = new javax.swing.GroupLayout(pnlEdicionContrasena);
        pnlEdicionContrasena.setLayout(pnlEdicionContrasenaLayout);
        pnlEdicionContrasenaLayout.setHorizontalGroup(
            pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEdicionContrasenaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEdicionContrasenaLayout.createSequentialGroup()
                        .addComponent(lblNuevaContra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pssNuevaContra, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEdicionContrasenaLayout.createSequentialGroup()
                        .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEdicionContrasenaLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblConfirmacion))
                            .addGroup(pnlEdicionContrasenaLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnAceptar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEdicionContrasenaLayout.createSequentialGroup()
                        .addComponent(lblContrasena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlEdicionContrasenaLayout.setVerticalGroup(
            pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEdicionContrasenaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(passContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevaContra)
                    .addComponent(pssNuevaContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmacion)
                    .addComponent(passConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlEdicionContrasenaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEdicionContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEdicionContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEdicionContrasena.getAccessibleContext().setAccessibleName("Modificar Contraseña");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DialogModificarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogModificarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogModificarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogModificarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogModificarContrasena dialog = new DialogModificarContrasena(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblConfirmacion;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblNuevaContra;
    private javax.swing.JPasswordField passConfirmacion;
    private javax.swing.JPasswordField passContrasena;
    private javax.swing.JPanel pnlEdicionContrasena;
    private javax.swing.JPasswordField pssNuevaContra;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JPasswordField getPassConfirmacion() {
        return passConfirmacion;
    }

    public void setPassConfirmacion(JPasswordField passConfirmacion) {
        this.passConfirmacion = passConfirmacion;
    }

    public JPasswordField getPassContrasena() {
        return passContrasena;
    }

    public void setPassContrasena(JPasswordField passContrasena) {
        this.passContrasena = passContrasena;
    }

    public JPanel getPnlEdicionContrasena() {
        return pnlEdicionContrasena;
    }

    public void setPnlEdicionContrasena(JPanel pnlEdicionContrasena) {
        this.pnlEdicionContrasena = pnlEdicionContrasena;
    }

    public JPasswordField getPssNuevaContra() {
        return pssNuevaContra;
    }

    public void setPssNuevaContra(JPasswordField pssNuevaContra) {
        this.pssNuevaContra = pssNuevaContra;
    }

    public void mostrarMensaje (String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
