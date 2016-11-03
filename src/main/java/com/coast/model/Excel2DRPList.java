package com.coast.model;

import java.util.List;

/**
 *
 * @author Coast
 */
public class Excel2DRPList {
    //sheet名 
    private String name;
    //sheet数据
    private List<DRPProduct> products;

    public Excel2DRPList() {
    }

    public Excel2DRPList(String name, List<DRPProduct> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DRPProduct> getProducts() {
        return products;
    }

    public void setProducts(List<DRPProduct> products) {
        this.products = products;
    }
    
    
            
}
