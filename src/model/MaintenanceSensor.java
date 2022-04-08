/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.AverageSpeedMonitoring;
import events.MaintenanceMonitoring;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class MaintenanceSensor extends Thread {

    private boolean ServiceCommand;
    private boolean oil;
    private boolean airfilter;
    private boolean major;
    private Vehicle veh;
    private double olddistance;

    public MaintenanceSensor(Vehicle veh) {
        this.veh = veh;
        ServiceCommand = false;
        oil = false;
        airfilter = false;
        major = false;
        olddistance = 0;
    }

    public boolean getServiceCommand() {
        return ServiceCommand;
    }

    public void setServiceCommand(boolean ServiceCommand) {
        this.ServiceCommand = ServiceCommand;
        oil = false;
        airfilter = false;
        major = false;
    }

    public boolean isOil() {
        return oil;
    }

    public void setOil(boolean oil) {
        this.oil = oil;
    }

    public boolean isAirfilter() {
        return airfilter;
    }

    public void setAirfilter(boolean airfilter) {
        this.airfilter = airfilter;
    }

    public boolean isMajor() {
        return major;
    }

    public void setMajor(boolean major) {
        this.major = major;
    }

    public boolean RequiredMaintenance(double distance) {

        if ((distance % 5000) == (double)0) {
            this.oil = true;
            this.ServiceCommand = true;
        } if ((distance % 10000) == (double)0) {
            this.airfilter = true;
            this.ServiceCommand = true;
        } if ((distance % 15000) == (double)0) {
            this.major = true;
            this.ServiceCommand = true;
        }
        config.sendEvent(new MaintenanceMonitoring(ServiceCommand));
        return ServiceCommand;
    }

    @Override
    public void run() {
        while (true) {
            boolean req = veh.getMaintanence().RequiredMaintenance(veh.getDistance());
            if(olddistance != veh.getDistance()){

                olddistance = veh.getDistance();

            if (req) {
                
                

                if (oil == true) {

                    ImageIcon img = new ImageIcon("oil.png");
                    veh.getGui().getOilLabel().setIcon(img);
                }
                if (airfilter == true) {

                    ImageIcon img = new ImageIcon("airfilter.png");
                    veh.getGui().getAirfilter().setIcon(img);
                }
                if (major == true) {
                    ImageIcon img = new ImageIcon("major.png");
                    veh.getGui().getMajor().setIcon(img);
                }
            }
            }
            try {
//config.sendEvent(new MaintenanceMonitoring((int)veh.getDistance()));
                this.sleep(10000);
            } catch (InterruptedException ex) {

            }
        }
    }
}
