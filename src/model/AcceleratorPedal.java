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
import esper.config;
import events.*;
import java.util.ArrayList;
import view.GUI;

/**
 *
 * @author DELL
 */
public class AcceleratorPedal extends Thread {

    private int acceleration;
    private Vehicle veh;
    private int power;

    public int getPower() {
        return power;
    }

    public AcceleratorPedal(Vehicle veh) {
        this.veh = veh;
        acceleration = 0;
    }

    public int getAccelerationn() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getAcceleration(int power) {
        switch (power) {
            case 1: {
                this.acceleration = 1;
            }
            case 2: {
                this.acceleration = 2;
            }
            case 3: {
                this.acceleration = 3;
            }
            case 4: {
                this.acceleration = 4;
            }
            case 5: {
                this.acceleration = 5;
            }
            case 6: {
                this.acceleration = 6;
            }
            case 7: {
                this.acceleration = 7;
            }
            case 8: {
                this.acceleration = 8;
            }
            default:
                this.acceleration = 0;

        }
        veh.setCurrentSpeed(acceleration);
        return acceleration;

    }

    @Override
    public void run() {
        while (true) {
            if (acceleration == 0) {
                if (veh.getCurrentSpeed() > 0) {
                    veh.setCurrentSpeed(veh.getCurrentSpeed() - 1);
                }
                //veh.getGui().getCurrentSpeed().setText(Integer.toString(veh.getCurrentSpeed()));
            } else if (acceleration > 0) {
                if (veh.getCurrentSpeed() < 200) {
                    veh.setCurrentSpeed(veh.getCurrentSpeed() + acceleration);
                } else if (veh.getCurrentSpeed() > 200) {
                    veh.setCurrentSpeed(veh.getCurrentSpeed() - 2);
                }
            } else if (acceleration == -1) {
                if (veh.getCurrentSpeed() > 0) {
                    veh.setCurrentSpeed(veh.getCurrentSpeed() - 5);
                } else if (veh.getCurrentSpeed() < 0) {
                    veh.setCurrentSpeed(0);
                }
            }

            veh.getGui().getCurrentSpeed().setText(Integer.toString(veh.getCurrentSpeed()));
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Calibrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            //config.sendEvent(new SpeedEvent(acceleration));
        }
    }
}
