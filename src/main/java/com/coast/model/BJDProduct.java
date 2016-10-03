package com.coast.model;

import java.util.Objects;

/**
 *
 * @author Coast
 */
public class BJDProduct {

    private String snCode;
    private String type;
    private String year;
    private String season;
    private double price;

    public BJDProduct() {
    }

    public BJDProduct(String snCode, String type, String year, String season, double price) {
        this.snCode = snCode;
        this.type = type;
        this.year = year;
        this.season = season;
        this.price = price;
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BJDProduct{" + "snCode=" + snCode + ", type=" + type + ", year=" + year + ", season=" + season + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.snCode);
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
        final BJDProduct other = (BJDProduct) obj;
        if (!Objects.equals(this.snCode, other.snCode)) {
            return false;
        }
        return true;
    }

}
