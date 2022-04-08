/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import model.CruiseControlCommand;

/**
 *
 * @author DELL
 */
public class CruiseControlMonitoring {

    private final int savedspeed;
    private final CruiseControlCommand command;

    public CruiseControlMonitoring(int savedspeed, CruiseControlCommand command) {
        this.savedspeed = savedspeed;
        this.command = command;
    }

    public int getSavedspeed() {
        return savedspeed;
    }

    public CruiseControlCommand getCommand() {
        return command;
    }

}
