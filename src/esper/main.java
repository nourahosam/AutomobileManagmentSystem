/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esper;

import java.util.ArrayList;
import model.CruiseControlCommand;
import model.Vehicle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
public class main {

    public static void main(String[] args) {

        // Disable logging
        Logger.getRootLogger().setLevel(Level.OFF);

        // Register events
        config.registerEvents();

        final Vehicle veh = new Vehicle();

        //Sherif and Yousef ------------------------------------------
        config.createStatement("select state from VehicleRunning")
                .setSubscriber(new Object() {
                    public void update(boolean state) {
                        veh.setRunning(state);
                    }
                });
        config.createStatement("select speed from AverageSpeedMonitoring")
                .setSubscriber(new Object() {
                    public void update(int speed) {
                        if (speed == 0) {
                        } else {
                            veh.setArrSpeed(speed);
                        }
                    }
                });
        config.createStatement("select command from AverageSpeedMonitoring")
                .setSubscriber(new Object() {
                    public void update(String command) {
                        if (command.equals("START_TRIP")) {
                            veh.MonitorAverageSpeed(command);
                        } else if (command.equals("AVERAGE_SPEED")) {
                            veh.MonitorAverageSpeed(command);
                        }
                    }
                });
        config.createStatement("select command from Calibrating")
                .setSubscriber(new Object() {
                    public void update(String command) {
                        if (command.equals("START_CALIBRATE")) {
                            veh.StartCalibration("START_CALIBRATE", 0);
                        } else if (command.equals("STOP_CALIBRATE")) {
                            veh.StartCalibration("STOP_CALIBRATE", Integer.parseInt(veh.getGui().getCalibrationDistance().getText()));
                        }
                    }
                });
        config.createStatement("select distance from DistanceEvent")
                .setSubscriber(new Object() {
                    public void update(double distance) {
                        veh.setDistance(distance);
                    }
                });
//        config.createStatement("select pulse from DriveShaftMesuring")
//                .setSubscriber(new Object() {
//                    public void update(int pulse) {
//                        System.out.println("Pulses " + veh.getDriveshaftsensor().calculatePulse(pulse,Integer.parseInt(veh.getGui().getCalibrationDistance().getText())));
//                    }
//                });

        //Noura -----------------------
        config.createStatement("select speed from SpeedEvent")
                .setSubscriber(new Object() {
                    public void update(int speed) {
                        veh.getAccpedal().setAcceleration(speed);
                        //veh.getCurrentSpeed();

                    }
                });
//        config.createStatement("select distance from MaintenanceMonitoring")
//                .setSubscriber(new Object() {
//                    public void update(int distance) {
//                        
//                        veh.getMaintanence().RequiredMaintenance(distance);
//                        //veh.getCurrentSpeed();
//                        System.out.println(distance+"main");
//                    }
//                });
        config.createStatement("select command from MaintenanceMonitoring")
                .setSubscriber(new Object() {
                    public void update(boolean command) {

                        veh.getMaintanence().setServiceCommand(command);
                        //veh.getCurrentSpeed();

                    }
                });

        //Shrouk
        config.createStatement("select savedspeed from CruiseControlMonitoring")
                .setSubscriber(new Object() {
                    public void update(int speed) throws InterruptedException {
                        veh.getCruisecontrol().setSavedspeed(speed);
                    }

                });

        config.createStatement("select command from CruiseControlMonitoring")
                .setSubscriber(new Object() {
                    public void update(CruiseControlCommand command) throws InterruptedException {
                        veh.setCruisecontrolcommand(command);
                        veh.StartCruiseControl();
                    }
                });
        //Christine
        config.createStatement("select fuelconsumption from FuelConsumptionMonitoring")
                .setSubscriber(new Object() {
                    public void update(int fuelconsumption) {
                        veh.FuelConsumptionMonitoring();
                    }
                });
    }
}
