/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.service;

import com.coast.model.BJDProduct;
import com.coast.model.ResultMSG;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface BJDService {
    List<BJDProduct> readDRPStock(String drpFilePath, ResultMSG resultMSG);
    void write(List<BJDProduct> products, String outPutFilePath, ResultMSG resultMSG);
}
