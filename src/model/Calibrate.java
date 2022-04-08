/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.Calibrating;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Calibrate extends Thread {

    private int counter = 0;
    private Vehicle veh;

    public Calibrate(Vehicle veh) {
        this.veh = veh;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        while (true) {
            if (veh.getRunning()) {
                counter += 1;
                
            }
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Calibrate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
