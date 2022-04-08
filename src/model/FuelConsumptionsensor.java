/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.FuelConsumptionMonitoring;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class FuelConsumptionsensor extends Thread {

    private double TotalFuel;
    private int CurrentFuel;
    private Vehicle veh;

    
    public FuelConsumptionsensor(Vehicle veh) {
        this.veh = veh;
    }

    public FuelConsumptionsensor(int TotalFuel, int CurrentFuel) {
        this.TotalFuel = TotalFuel;
        this.CurrentFuel = CurrentFuel;
    }

    public double getTotalFuel() {
        return TotalFuel;
    }

    public void CalcConsumption(int CurrentFuel) {
        TotalFuel += CurrentFuel;
    }

    public double calcByDistance(double Distance) {
        return Distance * 0.01;
    }

    @Override
    public void run() {
        while (true) {
            if (veh.getRunning()) {
                if (veh.getCurrentSpeed() > 0) {
                    
                    double x = TotalFuel - calcByDistance(veh.getDriveshaftsensor().calculateDistance(veh.getCurrentSpeed()));
                    TotalFuel = x;
                    veh.getGui().getjTextField3().setText(Double.toString(x));
                }
                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Calibrate.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            config.sendEvent(new FuelConsumptionMonitoring(CurrentFuel));

        }

    }
}
