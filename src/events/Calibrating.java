/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author DELL
 */
public class Calibrating {
    
    private String command;
    private int TimeCounter;

    public Calibrating(int TimeCounter) {
        this.TimeCounter = TimeCounter;
    }

    public Calibrating(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public int getTimeCounter() {
        return TimeCounter;
    }
    
    
}
