/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.view;

import com.coast.controler.Controler;
import com.coast.model.Discount;
import com.coast.model.ResultMSG;
import com.coast.table.DiscountTableListener;
import com.coast.table.DiscountTableModel;
import com.coast.util.DiscountUtil;
import com.coast.util.ExcelFileFilter;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Coast
 */
public class MainFrame extends javax.swing.JFrame {

    private boolean isOutOrder = false;
    private boolean isOrder = false;
    private boolean isSell = false;
    private List<Discount> discounts;

    private static final String NEXT_LINE = System.getProperty("line.separator");

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        shopinFileTextField.setText("");
        this.setLocation((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2,
                (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2);
        outputLocationTextField.setText(getAppDir());
        sapRadioButton.setSelected(true);
        buttonGroup.add(sapRadioButton);
        buttonGroup.add(orderRadioButton);
        buttonGroup.add(outRadioButton);
        buttonGroup.add(sellRadioButton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        ourFileLabel = new javax.swing.JLabel();
        shopinFileLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ourFileTextField = new javax.swing.JTextField();
        shopinFileTextField = new javax.swing.JTextField("");
        outputLocationTextField = new javax.swing.JTextField();
        ourFileButton = new javax.swing.JButton();
        shopinFileButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        outputLocationButton = new javax.swing.JButton();
        sapRadioButton = new javax.swing.JRadioButton();
        orderRadioButton = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultMessageTextArea = new javax.swing.JTextArea();
        outRadioButton = new javax.swing.JRadioButton();
        sellRadioButton = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("三彩生成上品数据");
        setName("mainFrame"); // NOI18N

        ourFileLabel.setText("我们的文件");

        shopinFileLabel.setText("上品订单模板");

        jLabel3.setText("生成的文件");

        ourFileTextField.setEnabled(false);

        shopinFileTextField.setEnabled(false);

        ourFileButton.setText("选择文件");
        ourFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ourFileButtonActionPerformed(evt);
            }
        });

        shopinFileButton.setText("选择文件");
        shopinFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shopinFileButtonActionPerformed(evt);
            }
        });

        submitButton.setText("确定");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        resetButton.setText("重置");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        outputLocationButton.setText("选择存储位置");
        outputLocationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputLocationButtonActionPerformed(evt);
            }
        });

        sapRadioButton.setText("生成SAP");
        sapRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sapRadioButtonActionPerformed(evt);
            }
        });

        orderRadioButton.setText("生成订单");
        orderRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderRadioButtonActionPerformed(evt);
            }
        });

        resultMessageTextArea.setColumns(20);
        resultMessageTextArea.setRows(5);
        jScrollPane2.setViewportView(resultMessageTextArea);

        outRadioButton.setText("退货");
        outRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outRadioButtonActionPerformed(evt);
            }
        });

        sellRadioButton.setText("销售小票");
        sellRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(outRadioButton)
                .addGap(18, 18, 18)
                .addComponent(sellRadioButton)
                .addContainerGap(275, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(sapRadioButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(orderRadioButton))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(shopinFileLabel)
                                        .addComponent(ourFileLabel)
                                        .addComponent(jLabel3))
                                    .addGap(22, 22, 22)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(ourFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(ourFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(shopinFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(shopinFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(outputLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(outputLocationButton))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(submitButton)
                                    .addGap(18, 18, 18)
                                    .addComponent(resetButton)))
                            .addGap(40, 40, 40))
                        .addComponent(jScrollPane2))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outRadioButton)
                    .addComponent(sellRadioButton))
                .addContainerGap(455, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sapRadioButton)
                        .addComponent(orderRadioButton))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ourFileLabel)
                        .addComponent(ourFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ourFileButton))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(shopinFileLabel)
                        .addComponent(shopinFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(shopinFileButton))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(outputLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(outputLocationButton))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(submitButton)
                        .addComponent(resetButton))
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("生成", jPanel1);

        DiscountUtil discountUtil = new DiscountUtil();
        discounts = discountUtil.read();
        Object[][] data = discountUtil.getDataArrayFromList(discounts);
        String [] columnNames = {"描述", "折扣", "匹配"};
        DiscountTableModel tableModel = new DiscountTableModel(columnNames, data);
        table.setModel(tableModel);
        table.getModel().addTableModelListener(new DiscountTableListener());
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("折扣", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("库存", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getAppDir() {
        String dir = System.getProperty("user.dir");
        return dir;
    }

    private void chooseFile(JTextField jTextField, int selectionMode) {
        JFileChooser fc = new JFileChooser();//创建文件选择器
        fc.setFileSelectionMode(selectionMode);
        fc.setCurrentDirectory(new File(getAppDir()));
        //fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(MainFrame.this);//打开文件选择器
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();///This is where a real application would open the file.                 
            jTextField.setText(file.getAbsolutePath());
        } else {
            jTextField.setText("");
        }
    }

    private void chooseFile(JTextField jTextField, int selectionMode, FileFilter fileFilter) {
        JFileChooser fc = new JFileChooser();//创建文件选择器
        fc.setFileSelectionMode(selectionMode);
        fc.setFileFilter(fileFilter);
        fc.setCurrentDirectory(new File(getAppDir()));
        //fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fc.showOpenDialog(MainFrame.this);//打开文件选择器
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();///This is where a real application would open the file.                 
            jTextField.setText(file.getAbsolutePath());
        } else {
            jTextField.setText("");
        }
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        this.ourFileTextField.setText("");
        this.shopinFileTextField.setText("");
        this.outputLocationTextField.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    private void ourFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ourFileButtonActionPerformed
        chooseFile(ourFileTextField, JFileChooser.FILES_ONLY, new ExcelFileFilter());

    }//GEN-LAST:event_ourFileButtonActionPerformed

    private void shopinFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopinFileButtonActionPerformed
        chooseFile(shopinFileTextField, JFileChooser.FILES_ONLY, new ExcelFileFilter());
    }//GEN-LAST:event_shopinFileButtonActionPerformed

    private void outputLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputLocationButtonActionPerformed
        chooseFile(outputLocationTextField, JFileChooser.DIRECTORIES_ONLY);
    }//GEN-LAST:event_outputLocationButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // 生成需要上传的excel文件
//        resultMessageTextArea.setText("");
        ResultMSG resultMSG = new ResultMSG();
        if (isSell) {
            resultMSG = Controler.doSell(shopinFileTextField.getText(), outputLocationTextField.getText());
        } else if (isOutOrder) {
            resultMSG = Controler.doOut(ourFileTextField.getText(), shopinFileTextField.getText(), outputLocationTextField.getText());
        } else {
            resultMSG = Controler.merge(ourFileTextField.getText(), shopinFileTextField.getText(), outputLocationTextField.getText(), isOrder, discounts);
        }
        String finalMessage = resultMSG.getReadMessage() + NEXT_LINE + resultMSG.getWriteMessage() + NEXT_LINE + resultMSG.getErrorMessage() + NEXT_LINE;
        this.resultMessageTextArea.append(finalMessage);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void sapRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sapRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isOutOrder = false;
        this.isOrder = false;
        this.isSell = false;
        this.shopinFileLabel.setText("上品SAP模板");
    }//GEN-LAST:event_sapRadioButtonActionPerformed

    private void orderRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isOutOrder = false;
        this.isOrder = true;
        this.isSell = false;
        this.shopinFileLabel.setText("上品订单文件");
    }//GEN-LAST:event_orderRadioButtonActionPerformed

    private void outRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isOutOrder = true;
        this.isOrder = false;
        this.isSell = false;
        this.shopinFileLabel.setText("上品订单文件");
    }//GEN-LAST:event_outRadioButtonActionPerformed

    private void sellRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isOutOrder = false;
        this.isOrder = false;
        this.isSell = true;
        this.shopinFileLabel.setText("上品销售文件");
    }//GEN-LAST:event_sellRadioButtonActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton orderRadioButton;
    private javax.swing.JButton ourFileButton;
    private javax.swing.JLabel ourFileLabel;
    private javax.swing.JTextField ourFileTextField;
    private javax.swing.JRadioButton outRadioButton;
    private javax.swing.JButton outputLocationButton;
    private javax.swing.JTextField outputLocationTextField;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextArea resultMessageTextArea;
    private javax.swing.JRadioButton sapRadioButton;
    private javax.swing.JRadioButton sellRadioButton;
    private javax.swing.JButton shopinFileButton;
    private javax.swing.JLabel shopinFileLabel;
    private javax.swing.JTextField shopinFileTextField;
    private javax.swing.JButton submitButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
