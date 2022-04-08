/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.AverageSpeedMonitoring;
import java.util.ArrayList;
import view.GUI;

/**
 *
 * @author DELL
 */
public class AverageSpeed extends Thread {

    private float averageSpeed;
    private Vehicle veh;

    public AverageSpeed(Vehicle veh) {
        this.veh = veh;
    }

    public float calculateAverageSpeed(ArrayList<Integer> SpeedArr) {
        averageSpeed = 0;
        for (int i = 0; i < SpeedArr.size(); i++) {
            averageSpeed =  averageSpeed + SpeedArr.get(i);
        }
        averageSpeed = averageSpeed/SpeedArr.size();
        return averageSpeed;
    }

    @Override
    public void run() {
        while (true) {
            if(veh.getRunning() == true){
                int a = veh.getCurrentSpeed();
                config.sendEvent(new AverageSpeedMonitoring(a));
            }
            try {
                this.sleep(10000);
            } catch (InterruptedException ex) {

            }

        }

    }

}
