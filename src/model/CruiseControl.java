/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.CruiseControlMonitoring;
import events.SpeedEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CruiseControl extends Thread {

    private Vehicle vehicle;
    private int Savedspeed;
    private CruiseControlCommand command;
    private static CruiseControl cruisecontrol;

    public CruiseControl(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void deleteinstance() {
        cruisecontrol = null;
    }

//    public void setSavedSpeed(CruiseControlCommand command, int speed) {
//
//        if (command == CruiseControlCommand.ACTIVATE) {
//            this.Savedspeed = speed;
//
//        } else if (command == CruiseControlCommand.DEACTIVATE) {
//            deleteinstance();
//
//        } else if (command == CruiseControlCommand.RESUME) {
//            System.out.println("Returned to speed" + this.Savedspeed);
//
//        }
//
//    }
    public int getSavedspeed() {
        return Savedspeed;
    }

    public void setSavedspeed(int Savedspeed) {
        this.Savedspeed = Savedspeed;
    }

    public boolean pause() {
        vehicle.getGui().getAcivate().setEnabled(false);

        return true;
    }

    public boolean resumecc() {
        vehicle.getGui().getResume().setEnabled(true);
        return true;
    }

    @Override
    public void run() {
        while (true) {
            if (vehicle.getCruisecontrolcommand().equals(CruiseControlCommand.ACTIVATE)) {
                vehicle.getGui().getjTextField1().setText("Cruise Control ON SPEED: " + this.Savedspeed);
                if (this.Savedspeed > vehicle.getCurrentSpeed()) {
                    config.sendEvent(new SpeedEvent(1));
                } else if (this.Savedspeed < vehicle.getCurrentSpeed()) {
                    config.sendEvent(new SpeedEvent(-1));
                } else if (this.Savedspeed == vehicle.getCurrentSpeed()) {
                    config.sendEvent(new SpeedEvent(0));
                }
            } else if (vehicle.getCruisecontrolcommand().equals(CruiseControlCommand.DEACTIVATE)){
                vehicle.s = 0;
                vehicle.getGui().getjTextField1().setText("Cruise Control Deactivated, Speed: " + this.Savedspeed);
            }
            else if(vehicle.getCruisecontrolcommand().equals(CruiseControlCommand.RESUME)){
                vehicle.getGui().getjTextField1().setText("Cruise control Resumed, Speed is: " + this.Savedspeed);
                if (this.Savedspeed > vehicle.getCurrentSpeed()) {
                    config.sendEvent(new SpeedEvent(1));
                } else if (this.Savedspeed < vehicle.getCurrentSpeed()) {
                    config.sendEvent(new SpeedEvent(-1));
                } else if (this.Savedspeed == vehicle.getCurrentSpeed()) {
                    config.sendEvent(new SpeedEvent(0));
                }
            }

            try {
                this.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(CruiseControl.class.getName()).log(Level.SEVERE, null, ex);
            }
//    if (pause()==true){
//    config.sendEvent(new CruiseControlMonitoring(0));
//    }else{
//    
            //config.sendEvent(new CruiseControlMonitoring(this.Savedspeed, this.command));
        }

//}
    }

}
