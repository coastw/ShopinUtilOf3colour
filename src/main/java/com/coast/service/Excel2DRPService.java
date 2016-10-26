/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.service;

import com.coast.model.DRPProduct;
import com.coast.model.ResultMSG;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface Excel2DRPService {
    List<DRPProduct> readExcel(String ourExcelFilePath, ResultMSG resultMSG);
    void write(List<DRPProduct> products, String outPutFilePath, ResultMSG resultMSG,String ourExcelFilePath);
}
