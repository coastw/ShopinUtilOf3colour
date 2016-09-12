/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.service;

import com.coast.model.Product;
import com.coast.model.ResultMSG;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface SellService {
    List<Product> getProductsFromShopinSellExcel(String filePath, ResultMSG resultMSG);

    void write(List<Product> products, String outPutFilePath, ResultMSG resultMSG);
}
