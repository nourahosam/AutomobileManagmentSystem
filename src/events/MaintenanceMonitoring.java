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
public class MaintenanceMonitoring {
    
    private boolean command;

public MaintenanceMonitoring(boolean command){
        this.command = command;
    }

    public boolean isCommand() {
        return command;
    }
}
