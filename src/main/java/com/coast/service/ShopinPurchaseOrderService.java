/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.service;

import com.coast.model.ResultMSG;
import java.io.File;

/**
 *
 * @author Coast
 */
public interface ShopinPurchaseOrderService {
    /**
     * 从给予的DRP文件(Excel格式)生成一个或多个订单文件(Excel格式)，
     * 并将没有SAP的款生成一个或多个上品主数据文件(Excel)，
     * 生成数量的多少根据给予的DRP文件中的品牌数量决定。
     * 其中的过程以文本形式记录在ResultMSG类的对象中。
     * @param sourceFile
     * @param destDir
     * @param s_shopinPurchaseOrderTemplateFile 三彩上品导出的数据文件
     * @param k_shopinPurchaseOrderTemplateFile 桑卡上品导出的数据文件
     * @param resultMSG 
     */
    void generatePurchaseOrder(File drpSourceFile, File destDir, File s_shopinPurchaseOrderTemplateFile,File k_shopinPurchaseOrderTemplateFile, ResultMSG resultMSG);
}
