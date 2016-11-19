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
    
    private final String NEXT_LINE = System.getProperty("line.separator");

    private String readMessage;
    private String writeMessage;
    private String errorMessage;
    private String finalMessage;

    public ResultMSG() {
        this.readMessage = "";
        this.writeMessage = "";
        this.errorMessage = "没有发成错误";
        this.finalMessage = "";
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }
    
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
    
    //append
    public void appendReadMessage(String readMessage){
        if (!this.readMessage.equals("")) {
            this.readMessage = this.readMessage + NEXT_LINE + readMessage;
        }else{
            this.readMessage = readMessage;
        }
    }
    public void appendWriteMessage(String writeMessage){
        if (!this.writeMessage.equals("")) {
            this.writeMessage = this.writeMessage + NEXT_LINE + writeMessage;
        }else{
            this.writeMessage = writeMessage;
        }
    }
    public void appendErrorMessage(String errorMessage){
        if (!this.errorMessage.equals("")) {
            this.errorMessage = this.errorMessage + NEXT_LINE + errorMessage;
        }else{
            this.errorMessage = errorMessage;
        }
    }
    
}
