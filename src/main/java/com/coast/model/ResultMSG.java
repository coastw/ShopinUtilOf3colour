/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.model;

/**
 *
 * @author Coast
 */
public class ResultMSG {

    private String readMessage;
    private String writeMessage;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReadMessage() {
        return readMessage;
    }

    public void setReadMessage(String readMessage) {
        this.readMessage = readMessage;
    }

    public String getWriteMessage() {
        return writeMessage;
    }

    public void setWriteMessage(String writeMessage) {
        this.writeMessage = writeMessage;
    }
    
    
}
