/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.view;

import com.coast.controler.Controler;
import com.coast.model.Discount;
import com.coast.model.ResultMSG;
import com.coast.service.ShopinPurchaseOrderService;
import com.coast.service.impl.ShopinPurchaseOrderServiceImpl;
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
    private boolean isBJD = false;
    private boolean isExcel2drp = false;
    private boolean isMergeExcel2DRP = true;

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
        //2017-03-31 杭州公司将所有三彩改标为桑卡
        //jPanel3TextField2.setText(getAppDir() + File.separator + "S.xls");
        jPanel3TextField2.setText(getAppDir() + File.separator + "K.xls");
        jPanel3TextField3.setText(getAppDir() + File.separator + "K.xls");
        jPanel3TextField4.setText(getAppDir());
        sapRadioButton.setSelected(true);
        buttonGroup.add(sapRadioButton);
        buttonGroup.add(orderRadioButton);
        buttonGroup.add(outRadioButton);
        buttonGroup.add(sellRadioButton);
        buttonGroup.add(bjdRadioButton);
        buttonGroup.add(excel2drpRadioButton);
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
        bjdRadioButton = new javax.swing.JRadioButton();
        excel2drpRadioButton = new javax.swing.JRadioButton();
        mergeExcel2DRPCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3TextField1 = new javax.swing.JTextField();
        jPanel3TextField2 = new javax.swing.JTextField();
        jPanel3TextField3 = new javax.swing.JTextField();
        jPanel3TextField4 = new javax.swing.JTextField();
        jPanel3Button1 = new javax.swing.JButton();
        jPanel3Button2 = new javax.swing.JButton();
        jPanel3Button3 = new javax.swing.JButton();
        jPanel3Button4 = new javax.swing.JButton();
        jPanel3ButtonSubmit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3TextArea = new javax.swing.JTextArea();
        jpanel3ButtonReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("三彩生成上品数据");
        setMinimumSize(new java.awt.Dimension(640, 480));
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

        bjdRadioButton.setText("报价单");
        bjdRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bjdRadioButtonActionPerformed(evt);
            }
        });

        excel2drpRadioButton.setText("Excel>>DRP");
        excel2drpRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excel2drpRadioButtonActionPerformed(evt);
            }
        });

        mergeExcel2DRPCheckBox.setSelected(true);
        mergeExcel2DRPCheckBox.setText("合并输出");
        mergeExcel2DRPCheckBox.setToolTipText("将Excel中所有Sheet中的数据合并输出");
        mergeExcel2DRPCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mergeExcel2DRPCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mergeExcel2DRPCheckBox)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(outRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(sellRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(bjdRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(excel2drpRadioButton)))
                .addGap(0, 159, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jSeparator1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(shopinFileLabel)
                                    .addComponent(ourFileLabel)
                                    .addComponent(jLabel3))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(shopinFileTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                    .addComponent(ourFileTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(outputLocationTextField))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(shopinFileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ourFileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(outputLocationButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(submitButton)
                                .addGap(18, 18, 18)
                                .addComponent(resetButton)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(sapRadioButton)
                    .addGap(18, 18, 18)
                    .addComponent(orderRadioButton)
                    .addContainerGap(540, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outRadioButton)
                    .addComponent(sellRadioButton)
                    .addComponent(bjdRadioButton)
                    .addComponent(excel2drpRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mergeExcel2DRPCheckBox)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sapRadioButton)
                        .addComponent(orderRadioButton))
                    .addContainerGap(511, Short.MAX_VALUE)))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("折扣", jPanel2);

        jLabel1.setText("DRP导出的文件");

        jLabel2.setText("上品三彩数据文件");

        jLabel4.setText("上品桑卡数据文件");

        jLabel5.setText("输出目录");

        jPanel3TextField1.setEnabled(false);

        jPanel3TextField2.setEnabled(false);

        jPanel3TextField3.setEnabled(false);

        jPanel3TextField4.setEnabled(false);

        jPanel3Button1.setText("选择文件");
        jPanel3Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPanel3Button1ActionPerformed(evt);
            }
        });

        jPanel3Button2.setText("选择文件");
        jPanel3Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPanel3Button2ActionPerformed(evt);
            }
        });

        jPanel3Button3.setText("选择文件");
        jPanel3Button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPanel3Button3ActionPerformed(evt);
            }
        });

        jPanel3Button4.setText("选择目录");
        jPanel3Button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPanel3Button4ActionPerformed(evt);
            }
        });

        jPanel3ButtonSubmit.setText(" 确 定 ");
        jPanel3ButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPanel3ButtonSubmitActionPerformed(evt);
            }
        });

        jPanel3TextArea.setColumns(20);
        jPanel3TextArea.setRows(5);
        jScrollPane3.setViewportView(jPanel3TextArea);

        jpanel3ButtonReset.setText(" 重 置 ");
        jpanel3ButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpanel3ButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel3ButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpanel3ButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3TextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                            .addComponent(jPanel3TextField2)
                            .addComponent(jPanel3TextField3)
                            .addComponent(jPanel3TextField4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3Button1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3Button2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3Button3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3Button4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(22, 22, 22))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPanel3TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3Button1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPanel3TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3Button2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jPanel3TextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3Button3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPanel3TextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3Button4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPanel3ButtonSubmit)
                    .addComponent(jpanel3ButtonReset))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("智能订单", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sellRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isExcel2drp = false;
        this.isOutOrder = false;
        this.isOrder = false;
        this.isSell = true;
        this.shopinFileLabel.setText("上品销售文件");
        this.resultMessageTextArea.append("===生成销售小票===" + NEXT_LINE);
    }//GEN-LAST:event_sellRadioButtonActionPerformed

    private void outRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isExcel2drp = false;
        this.isOutOrder = true;
        this.isOrder = false;
        this.isSell = false;
        this.shopinFileLabel.setText("上品订单文件");
        this.resultMessageTextArea.append("===生成上品退货订单===" + NEXT_LINE);
    }//GEN-LAST:event_outRadioButtonActionPerformed

    private void orderRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isExcel2drp = false;
        this.isOutOrder = false;
        this.isOrder = true;
        this.isSell = false;
        this.shopinFileLabel.setText("上品订单文件");
        this.resultMessageTextArea.append("===生成上品订单===" + NEXT_LINE);
    }//GEN-LAST:event_orderRadioButtonActionPerformed

    private void sapRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sapRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isExcel2drp = false;
        this.isOutOrder = false;
        this.isOrder = false;
        this.isSell = false;
        this.shopinFileLabel.setText("上品SAP模板");
        this.resultMessageTextArea.append("===生成上品SAP===" + NEXT_LINE);
    }//GEN-LAST:event_sapRadioButtonActionPerformed

    private void outputLocationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputLocationButtonActionPerformed
        chooseFile(outputLocationTextField, JFileChooser.DIRECTORIES_ONLY);
    }//GEN-LAST:event_outputLocationButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        this.ourFileTextField.setText("");
        this.shopinFileTextField.setText("");
        this.outputLocationTextField.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // 生成需要上传的excel文件
        //        resultMessageTextArea.setText("");
        ResultMSG resultMSG = new ResultMSG();
        if (isSell) {
            resultMSG = Controler.doSell(shopinFileTextField.getText(), outputLocationTextField.getText());
        } else if (isOutOrder) {
            resultMSG = Controler.doOut(ourFileTextField.getText(), shopinFileTextField.getText(), outputLocationTextField.getText());
        } else if (isBJD) {
            resultMSG = Controler.generateBJD(ourFileTextField.getText(), outputLocationTextField.getText());
        } else if (isExcel2drp) {
            resultMSG = Controler.generateExcel2DRP(ourFileTextField.getText(), outputLocationTextField.getText(), isMergeExcel2DRP);
        } else {
            resultMSG = Controler.merge(ourFileTextField.getText(), shopinFileTextField.getText(), outputLocationTextField.getText(), isOrder, discounts);
        }
        String finalMessage = resultMSG.getReadMessage() + NEXT_LINE + resultMSG.getWriteMessage() + NEXT_LINE + resultMSG.getErrorMessage() + NEXT_LINE;
        this.resultMessageTextArea.append(finalMessage);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void shopinFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopinFileButtonActionPerformed
        chooseFile(shopinFileTextField, JFileChooser.FILES_ONLY, new ExcelFileFilter());
    }//GEN-LAST:event_shopinFileButtonActionPerformed

    private void ourFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ourFileButtonActionPerformed
        chooseFile(ourFileTextField, JFileChooser.FILES_ONLY, new ExcelFileFilter());
    }//GEN-LAST:event_ourFileButtonActionPerformed

    //BJD
    private void bjdRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bjdRadioButtonActionPerformed
        //报价单
        this.isBJD = true;
        this.isOrder = false;
        this.isOutOrder = false;
        this.isSell = false;
        this.isExcel2drp = false;
        this.resultMessageTextArea.append("===注意:将DRP中的库存数据显示为横排再导出,方可生成报价单===" + NEXT_LINE);
    }//GEN-LAST:event_bjdRadioButtonActionPerformed

    private void excel2drpRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excel2drpRadioButtonActionPerformed
        // TODO add your handling code here:
        this.isBJD = false;
        this.isOrder = false;
        this.isOutOrder = false;
        this.isSell = false;
        this.isExcel2drp = true;
        this.resultMessageTextArea.append("===将扫描到Excel中的数据合并汇总为可以导入到DRP的格式===" + NEXT_LINE);
    }//GEN-LAST:event_excel2drpRadioButtonActionPerformed

    private void mergeExcel2DRPCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mergeExcel2DRPCheckBoxActionPerformed
        // TODO add your handling code here:
        this.isMergeExcel2DRP = this.mergeExcel2DRPCheckBox.isSelected();
        this.resultMessageTextArea.append("===合并输出:" + this.isMergeExcel2DRP + "===" + NEXT_LINE);
    }//GEN-LAST:event_mergeExcel2DRPCheckBoxActionPerformed

    /*
    Panel3 start
     */
    private void jPanel3Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPanel3Button1ActionPerformed
        chooseFile(jPanel3TextField1, JFileChooser.FILES_ONLY, new ExcelFileFilter());
    }//GEN-LAST:event_jPanel3Button1ActionPerformed

    private void jPanel3Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPanel3Button2ActionPerformed
        chooseFile(jPanel3TextField2, JFileChooser.FILES_ONLY, new ExcelFileFilter());
    }//GEN-LAST:event_jPanel3Button2ActionPerformed

    private void jPanel3Button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPanel3Button3ActionPerformed
        chooseFile(jPanel3TextField3, JFileChooser.FILES_ONLY, new ExcelFileFilter());
    }//GEN-LAST:event_jPanel3Button3ActionPerformed

    private void jPanel3Button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPanel3Button4ActionPerformed
        chooseFile(jPanel3TextField4, JFileChooser.DIRECTORIES_ONLY);
    }//GEN-LAST:event_jPanel3Button4ActionPerformed

    private void jPanel3ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPanel3ButtonSubmitActionPerformed
        // TODO add your handling code here:
        jPanel3TextArea.append("===开始智能创建订单===" + NEXT_LINE);
        ShopinPurchaseOrderService orderService = new ShopinPurchaseOrderServiceImpl();
        File drpSourceFile = new File(jPanel3TextField1.getText());
        File destDir = new File(jPanel3TextField4.getText());
        File s_shopinPurchaseOrderTemplateFile = new File(jPanel3TextField2.getText());
        File k_shopinPurchaseOrderTemplateFile = new File(jPanel3TextField3.getText());
        ResultMSG resultMSG = new ResultMSG();
        orderService.generatePurchaseOrder(drpSourceFile, destDir, s_shopinPurchaseOrderTemplateFile, k_shopinPurchaseOrderTemplateFile, resultMSG);
        resultMSG.setFinalMessage("===智能订单处理完成===" + NEXT_LINE + NEXT_LINE);
        jPanel3TextArea.append(resultMSG.getReadMessage() + NEXT_LINE + resultMSG.getWriteMessage() + NEXT_LINE + resultMSG.getErrorMessage() + NEXT_LINE + resultMSG.getFinalMessage());
    }//GEN-LAST:event_jPanel3ButtonSubmitActionPerformed

    private void jpanel3ButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpanel3ButtonResetActionPerformed
        // TODO add your handling code here:
        jPanel3TextField2.setText(getAppDir() + File.separator + "S.xls");
        jPanel3TextField3.setText(getAppDir() + File.separator + "K.xls");
        jPanel3TextField4.setText(getAppDir());
        jPanel3TextArea.setText("");
    }//GEN-LAST:event_jpanel3ButtonResetActionPerformed

    /*
    Panel3 end
     */
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
    private javax.swing.JRadioButton bjdRadioButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton excel2drpRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jPanel3Button1;
    private javax.swing.JButton jPanel3Button2;
    private javax.swing.JButton jPanel3Button3;
    private javax.swing.JButton jPanel3Button4;
    private javax.swing.JButton jPanel3ButtonSubmit;
    private javax.swing.JTextArea jPanel3TextArea;
    private javax.swing.JTextField jPanel3TextField1;
    private javax.swing.JTextField jPanel3TextField2;
    private javax.swing.JTextField jPanel3TextField3;
    private javax.swing.JTextField jPanel3TextField4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jpanel3ButtonReset;
    private javax.swing.JCheckBox mergeExcel2DRPCheckBox;
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
