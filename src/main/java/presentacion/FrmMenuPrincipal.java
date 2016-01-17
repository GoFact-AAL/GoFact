/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JMenuItem;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author camm
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmMenuPrincipal
     */

    public FrmMenuPrincipal() {
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

        jPanel1 = new javax.swing.JPanel();
        menuBarPrincipal = new javax.swing.JMenuBar();
        menuProveedor = new javax.swing.JMenu();
        menuItemConsultarProveedor = new javax.swing.JMenuItem();
        menuFactura = new javax.swing.JMenu();
        menuItemConsultarFactura = new javax.swing.JMenuItem();
        menuItemExportarXML = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();
        menuItemObtenerReporte = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenu();
        menuItemModificar = new javax.swing.JMenu();
        menuItemModificarInformacion = new javax.swing.JMenuItem();
        menuItemModificarContrasena = new javax.swing.JMenuItem();
        menuItemModificarEliminar = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        menuItemAcerca = new javax.swing.JMenuItem();
        menuCerrarSesion = new javax.swing.JMenu();
        menuItemCerrarSesion = new javax.swing.JMenuItem();
        menuItemCerrarSistema = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GoFact!");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        menuProveedor.setText("Proveedor");

        menuItemConsultarProveedor.setText("Consultar");
        menuProveedor.add(menuItemConsultarProveedor);

        menuBarPrincipal.add(menuProveedor);

        menuFactura.setText("Factura");

        menuItemConsultarFactura.setText("Consultar");
        menuFactura.add(menuItemConsultarFactura);

        menuItemExportarXML.setText("Exportar XML");
        menuFactura.add(menuItemExportarXML);

        menuBarPrincipal.add(menuFactura);

        menuReportes.setText("Reportes");

        menuItemObtenerReporte.setText("Obtener");
        menuReportes.add(menuItemObtenerReporte);

        menuBarPrincipal.add(menuReportes);

        menuUsuario.setText("Usuario");

        menuItemModificar.setText("Modificar");

        menuItemModificarInformacion.setText("Información");
        menuItemModificar.add(menuItemModificarInformacion);

        menuItemModificarContrasena.setText("Contraseña");
        menuItemModificar.add(menuItemModificarContrasena);

        menuUsuario.add(menuItemModificar);

        menuItemModificarEliminar.setText("Eliminar");
        menuUsuario.add(menuItemModificarEliminar);

        menuBarPrincipal.add(menuUsuario);

        menuAyuda.setText("Ayuda");

        menuItemAcerca.setText("Acerca ...");
        menuAyuda.add(menuItemAcerca);

        menuBarPrincipal.add(menuAyuda);

        menuCerrarSesion.setText("Salir");

        menuItemCerrarSesion.setText("Cerrar Sesión");
        menuCerrarSesion.add(menuItemCerrarSesion);

        menuItemCerrarSistema.setText("Cerrar Sistema");
        menuCerrarSesion.add(menuItemCerrarSistema);

        menuBarPrincipal.add(menuCerrarSesion);

        setJMenuBar(menuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuBar menuBarPrincipal;
    private javax.swing.JMenu menuCerrarSesion;
    private javax.swing.JMenu menuFactura;
    private javax.swing.JMenuItem menuItemAcerca;
    private javax.swing.JMenuItem menuItemCerrarSesion;
    private javax.swing.JMenuItem menuItemCerrarSistema;
    private javax.swing.JMenuItem menuItemConsultarFactura;
    private javax.swing.JMenuItem menuItemConsultarProveedor;
    private javax.swing.JMenuItem menuItemExportarXML;
    private javax.swing.JMenu menuItemModificar;
    private javax.swing.JMenuItem menuItemModificarContrasena;
    private javax.swing.JMenuItem menuItemModificarEliminar;
    private javax.swing.JMenuItem menuItemModificarInformacion;
    private javax.swing.JMenuItem menuItemObtenerReporte;
    private javax.swing.JMenu menuProveedor;
    private javax.swing.JMenu menuReportes;
    private javax.swing.JMenu menuUsuario;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getMenuItemAcerca() {
        return menuItemAcerca;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public JMenuItem getMenuItemCerrarSistema() {
        return menuItemCerrarSistema;
    }

    public JMenuItem getMenuItemConsultarFactura() {
        return menuItemConsultarFactura;
    }

    public JMenuItem getMenuItemConsultarProveedor() {
        return menuItemConsultarProveedor;
    }

    public JMenuItem getMenuItemExportarXML() {
        return menuItemExportarXML;
    }

    public JMenuItem getMenuItemModificarEliminar() {
        return menuItemModificarEliminar;
    }

    public JMenuItem getmenuItemModificarInformacion() {
        return menuItemModificarInformacion;
    }
    
    public JMenuItem getmenuItemModificarContrasena() {
        return menuItemModificarContrasena;
    }

    public JMenuItem getMenuItemObtenerReporte() {
        return menuItemObtenerReporte;
    }

	public void setjPanel1(ChartPanel panel) {
		setContentPane(panel);
		validate();
	}
}
