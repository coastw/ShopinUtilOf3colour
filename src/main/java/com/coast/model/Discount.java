package com.coast.model;

/**
 *
 * @author Coast
 */
public class Discount {

    private String discribe;
    private String percent;
    private String regex;

    public Discount() {
    }
    
    public Discount(String discribe, String percent, String regex) {
        this.discribe = discribe;
        this.percent = percent;
        this.regex = regex;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String describe) {
        this.discribe = describe;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "Discount{" + "discribe=" + discribe + ", percent=" + percent + ", regex=" + regex + '}';
    }
    
}
