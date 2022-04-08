/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class AverageSpeedMonitoring {
    
    private int speed = 0;
    private String command;

    public AverageSpeedMonitoring(int speed) {
        this.speed = speed;
    }

    public AverageSpeedMonitoring(String command) {
        this.command = command;
    }

    public int getSpeed() {
        return speed;
    }
    
    public String getCommand() {
        return command;
    }

    
}
