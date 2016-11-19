/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.service;

import com.coast.model.Discount;
import com.coast.model.Product;
import com.coast.model.ResultMSG;
import java.io.File;
import java.util.List;

/**
 *
 * @author Coast
 */
public interface SAPService {

    void generateSingleBrandSAP(List<Product> products, List<Discount> discounts, File destFile, ResultMSG resultMSG);
}
