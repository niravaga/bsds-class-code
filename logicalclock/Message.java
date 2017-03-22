/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package logicalclock;

import java.util.Vector;

/**
 *
 * @author Ian Gortan
 */
public class Message {
    private int pid;
    private Vector<Integer> clocks;
    private String messageID;

    
    /**
     * Constructor
     * @param pid
     * @param clocks
     * @param message 
     */
    public Message (int pid, Vector<Integer> clocks, String message) {
        this.pid = pid;
        this.clocks = clocks;
        this.messageID = message;
    }
    /**
     *  Copy constructor
     */
    public Message (Message aMessage) {
        this.pid = aMessage.getPid();
        this.clocks = (Vector<Integer>) aMessage.getClock().clone();
        this.messageID = aMessage.getMessageID();
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the clock
     */
    public Vector<Integer> getClock() {
        return clocks;
    }

    /**
     * @param clocks the clock to set
     */
    public void setClock(Vector<Integer> clocks) {
        this.clocks = clocks;
    }

    /**
     * @return the messageID
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * @param messageID the messageID to set
     */
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
    
}
