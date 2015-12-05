/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gofact.presentacion.factura;

/**
 *
 * @author jose nathy
 */
public class DialogFacturas extends javax.swing.JDialog {

    /**
     * Creates new form DialogFacturas
     */
    public DialogFacturas(java.awt.Frame parent, boolean modal, boolean editar) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        if (editar) {
            this.txtNumFactura.setEnabled(false);
            this.dateFechaFactura.setEnabled(false);
            this.txtRUCProveedor.setEnabled(false);
            this.gridProveedor.setEnabled(false);
            
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

        jPanel1 = new javax.swing.JPanel();
        grpDetalleGastos = new javax.swing.JPanel();
        cmdMasAlimentacion = new javax.swing.JButton();
        cmdMenosAlimentacion = new javax.swing.JButton();
        lblEqTotalDeducibles = new javax.swing.JLabel();
        txtTotalAlimentacion = new javax.swing.JTextField();
        lblEqTipoGasto = new javax.swing.JLabel();
        txtValAlimentacion = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        lblAlimentacion1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        gridRubros = new javax.swing.JTable();
        grpTotales = new javax.swing.JPanel();
        lblEqDeducible = new javax.swing.JLabel();
        lblEqNoDeducible = new javax.swing.JLabel();
        lblEqTotalSinIVA = new javax.swing.JLabel();
        lblEqIVA = new javax.swing.JLabel();
        lblEqTotal = new javax.swing.JLabel();
        txtTotalDeducible = new javax.swing.JTextField();
        txtTotalNoDeducible = new javax.swing.JTextField();
        txtTotalSinIVA = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        grpInfoGeneral = new javax.swing.JPanel();
        lblEqNumFactura = new javax.swing.JLabel();
        txtNumFactura = new javax.swing.JTextField();
        lblEqFecha = new javax.swing.JLabel();
        dateFechaFactura = new com.toedter.calendar.JDateChooser();
        grpInfoGeneral1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridProveedor = new javax.swing.JTable();
        txtRUCProveedor = new javax.swing.JTextField();
        lblEqDeducible1 = new javax.swing.JLabel();
        btnAnadir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturas");
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1021, 560));

        grpDetalleGastos.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de gastos"));

        cmdMasAlimentacion.setText("+");
        cmdMasAlimentacion.setEnabled(false);
        cmdMasAlimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMasAlimentacionActionPerformed(evt);
            }
        });

        cmdMenosAlimentacion.setText("-");
        cmdMenosAlimentacion.setEnabled(false);
        cmdMenosAlimentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMenosAlimentacionActionPerformed(evt);
            }
        });

        lblEqTotalDeducibles.setText("Total");

        txtTotalAlimentacion.setText("0.0");
        txtTotalAlimentacion.setEnabled(false);

        lblEqTipoGasto.setText("Rubros:");

        txtValAlimentacion.setText("0");
        txtValAlimentacion.setEnabled(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alimentación", "Educación", "Salud", "Vestimenta", "Vivienda", "Otros", " " }));

        lblAlimentacion1.setText("Total por Rubros:");

        gridRubros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(gridRubros);

        javax.swing.GroupLayout grpDetalleGastosLayout = new javax.swing.GroupLayout(grpDetalleGastos);
        grpDetalleGastos.setLayout(grpDetalleGastosLayout);
        grpDetalleGastosLayout.setHorizontalGroup(
            grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpDetalleGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grpDetalleGastosLayout.createSequentialGroup()
                        .addGroup(grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, grpDetalleGastosLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblEqTipoGasto)))
                        .addGroup(grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(grpDetalleGastosLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtValAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdMasAlimentacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdMenosAlimentacion)
                                .addGap(27, 27, 27)
                                .addComponent(txtTotalAlimentacion)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grpDetalleGastosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEqTotalDeducibles)
                                .addGap(41, 41, 41))))
                    .addGroup(grpDetalleGastosLayout.createSequentialGroup()
                        .addComponent(lblAlimentacion1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(grpDetalleGastosLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        grpDetalleGastosLayout.setVerticalGroup(
            grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grpDetalleGastosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEqTotalDeducibles)
                    .addComponent(lblEqTipoGasto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grpDetalleGastosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdMasAlimentacion)
                    .addComponent(cmdMenosAlimentacion)
                    .addComponent(txtTotalAlimentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAlimentacion1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grpTotales.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales"));

        lblEqDeducible.setText("Deducible:");

        lblEqNoDeducible.setText("No deducible:");

        lblEqTotalSinIVA.setText("Total sin IVA:");

        lblEqIVA.setText("IVA (%):");

        lblEqTotal.setText("Total:");

        txtTotalDeducible.setText("0.0");
        txtTotalDeducible.setEnabled(false);
        txtTotalDeducible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalDeducibleActionPerformed(evt);
            }
        });

        txtTotalNoDeducible.setText("0.0");
        txtTotalNoDeducible.setEnabled(false);
        txtTotalNoDeducible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalNoDeducibleActionPerformed(evt);
            }
        });

        txtTotalSinIVA.setEnabled(false);
        txtTotalSinIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalSinIVAActionPerformed(evt);
            }
        });

        txtIVA.setText("12");
        txtIVA.setEnabled(false);
        txtIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIVAActionPerformed(evt);
            }
        });

        txtTotal.setText("0.0");
        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout grpTotalesLayout = new javax.swing.GroupLayout(grpTotales);
        grpTotales.setLayout(grpTotalesLayout);
        grpTotalesLayout.setHorizontalGroup(
            grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grpTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grpTotalesLayout.createSequentialGroup()
                        .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEqDeducible)
                            .addComponent(lblEqNoDeducible))
                        .addGap(10, 10, 10)
                        .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalNoDeducible, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(txtTotalDeducible)))
                    .addGroup(grpTotalesLayout.createSequentialGroup()
                        .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEqIVA)
                            .addComponent(lblEqTotal)
                            .addComponent(lblEqTotalSinIVA))
                        .addGap(11, 11, 11)
                        .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalSinIVA)
                            .addComponent(txtIVA)
                            .addComponent(txtTotal))))
                .addGap(218, 218, 218))
        );
        grpTotalesLayout.setVerticalGroup(
            grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpTotalesLayout.createSequentialGroup()
                .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEqDeducible)
                    .addComponent(txtTotalDeducible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEqNoDeducible)
                    .addComponent(txtTotalNoDeducible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEqTotalSinIVA)
                    .addComponent(txtTotalSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEqIVA)
                    .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grpTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEqTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grpInfoGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder("Información general"));

        lblEqNumFactura.setText("N° factura:");

        txtNumFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumFacturaActionPerformed(evt);
            }
        });

        lblEqFecha.setText("Fecha:");

        grpInfoGeneral1.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));

        gridProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        gridProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gridProveedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(gridProveedor);

        lblEqDeducible1.setText("Nombre o RUC:");

        javax.swing.GroupLayout grpInfoGeneral1Layout = new javax.swing.GroupLayout(grpInfoGeneral1);
        grpInfoGeneral1.setLayout(grpInfoGeneral1Layout);
        grpInfoGeneral1Layout.setHorizontalGroup(
            grpInfoGeneral1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpInfoGeneral1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpInfoGeneral1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grpInfoGeneral1Layout.createSequentialGroup()
                        .addComponent(lblEqDeducible1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRUCProveedor))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        grpInfoGeneral1Layout.setVerticalGroup(
            grpInfoGeneral1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpInfoGeneral1Layout.createSequentialGroup()
                .addGroup(grpInfoGeneral1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRUCProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEqDeducible1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        btnAnadir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/anadir.png"))); // NOI18N
        btnAnadir.setName("btnAnadir"); // NOI18N
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout grpInfoGeneralLayout = new javax.swing.GroupLayout(grpInfoGeneral);
        grpInfoGeneral.setLayout(grpInfoGeneralLayout);
        grpInfoGeneralLayout.setHorizontalGroup(
            grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpInfoGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(grpInfoGeneral1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(grpInfoGeneralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAnadir)
                            .addGroup(grpInfoGeneralLayout.createSequentialGroup()
                                .addGroup(grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEqNumFactura)
                                    .addComponent(lblEqFecha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNumFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateFechaFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        grpInfoGeneralLayout.setVerticalGroup(
            grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grpInfoGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEqNumFactura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grpInfoGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEqFecha)
                    .addComponent(dateFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(grpInfoGeneral1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(grpInfoGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grpTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grpDetalleGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(grpInfoGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grpDetalleGastos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(grpTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMasAlimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMasAlimentacionActionPerformed
        /*
        if (validarCajas(txtValAlimentacion.getText()))
        txtTotalAlimentacion.setText(String.valueOf(Double.parseDouble(txtTotalAlimentacion.getText()) + Double.parseDouble(txtValAlimentacion.getText())));
        txtValAlimentacion.setText("0");
        calcularTotalDeducible();
        */
    }//GEN-LAST:event_cmdMasAlimentacionActionPerformed

    private void cmdMenosAlimentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMenosAlimentacionActionPerformed
        /*
        if (validarCajas(txtValAlimentacion.getText())){
            double resto = Double.parseDouble(txtTotalAlimentacion.getText()) - Double.parseDouble(txtValAlimentacion.getText());
            if (resto < 0) resto = 0;
            txtTotalAlimentacion.setText(String.valueOf(resto));
            calcularTotalDeducible();
        }
        txtValAlimentacion.setText("0");
        */
    }//GEN-LAST:event_cmdMenosAlimentacionActionPerformed

    private void txtTotalDeducibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalDeducibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDeducibleActionPerformed

    private void txtTotalNoDeducibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalNoDeducibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalNoDeducibleActionPerformed

    private void txtTotalSinIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSinIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSinIVAActionPerformed

    private void txtIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtNumFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumFacturaActionPerformed

    private void gridProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gridProveedorMouseClicked
        int fila = gridProveedor.rowAtPoint(evt.getPoint());
        //idProveedor = Integer.parseInt(gridProveedor.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_gridProveedorMouseClicked

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed

    }//GEN-LAST:event_btnAnadirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(DialogFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogFacturas dialog = new DialogFacturas(new javax.swing.JFrame(), true, false);
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
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton cmdMasAlimentacion;
    private javax.swing.JButton cmdMenosAlimentacion;
    private com.toedter.calendar.JDateChooser dateFechaFactura;
    private javax.swing.JTable gridProveedor;
    private javax.swing.JTable gridRubros;
    private javax.swing.JPanel grpDetalleGastos;
    private javax.swing.JPanel grpInfoGeneral;
    private javax.swing.JPanel grpInfoGeneral1;
    private javax.swing.JPanel grpTotales;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAlimentacion1;
    private javax.swing.JLabel lblEqDeducible;
    private javax.swing.JLabel lblEqDeducible1;
    private javax.swing.JLabel lblEqFecha;
    private javax.swing.JLabel lblEqIVA;
    private javax.swing.JLabel lblEqNoDeducible;
    private javax.swing.JLabel lblEqNumFactura;
    private javax.swing.JLabel lblEqTipoGasto;
    private javax.swing.JLabel lblEqTotal;
    private javax.swing.JLabel lblEqTotalDeducibles;
    private javax.swing.JLabel lblEqTotalSinIVA;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtNumFactura;
    private javax.swing.JTextField txtRUCProveedor;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalAlimentacion;
    private javax.swing.JTextField txtTotalDeducible;
    private javax.swing.JTextField txtTotalNoDeducible;
    private javax.swing.JTextField txtTotalSinIVA;
    private javax.swing.JTextField txtValAlimentacion;
    // End of variables declaration//GEN-END:variables
}
