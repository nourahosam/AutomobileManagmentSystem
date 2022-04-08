/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.DistanceEvent;
import events.DriveShaftMesuring;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DriverShaftSensor extends Thread{
    
    private int pulse = 20;
    private Vehicle veh;

    public DriverShaftSensor(Vehicle veh) {
        this.veh = veh;
    }
    
    public double calculateDistance(int currentSpeed){
        double x = (double)((currentSpeed)/60)/60;
        return x;
    }
    

    public double calcualteSpeed(int defienedDistance){
        double time = (double)(veh.getCalibration().getCounter())/3600;
        double x = (defienedDistance)/time;
        return x;
    }
    
    public double calculatePulse(int totalPulse, int distance){
        double x = (double)totalPulse * (distance);
        return x;
    }
    
    @Override
    public void run() {
        while (true) {
            if(veh.getRunning()){
                if(veh.getCurrentSpeed() > 0){
                    double x = veh.getDistance() + calculateDistance(veh.getCurrentSpeed());
                    veh.getGui().getDistance().setText(Double.toString(Math.round(x * 100.0)/100.0));
                    config.sendEvent(new DistanceEvent(x));
                    config.sendEvent(new DriveShaftMesuring(veh.getCurrentSpeed() * pulse));
                    veh.getGui().getDriveShaftPulse().setText(Integer.toString(veh.getCurrentSpeed() * pulse));
                }
            }
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DriverShaftSensor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
    
}
