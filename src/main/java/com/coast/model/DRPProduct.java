package com.coast.model;

import java.util.Objects;

/**
 *
 * @author Coast
 */
public class DRPProduct {
    private String snCode;
    private int amount;

    public DRPProduct() {
    }

    public DRPProduct(String snCode, int amount) {
        this.snCode = snCode;
        this.amount = amount;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.snCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DRPProduct other = (DRPProduct) obj;
        if (!Objects.equals(this.snCode, other.snCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DRPProduct{" + "snCode=" + snCode + ", amount=" + amount + '}';
    }
    
}
