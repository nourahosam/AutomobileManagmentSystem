/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import esper.config;
import events.AverageSpeedMonitoring;
import events.MaintenanceMonitoring;
import events.VehicleRunning;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.GUI;

/**
 *
 * @author DELL
 */
enum CalibrateCommand {
    START_CALIBRATE, STOP_CALIBRATE
}

enum ASCommand {
    START_TRIP, AVERAGE_SPEED
}

public class Vehicle {

    private GUI gui;
    private boolean running = false;
    private CruiseControl cruisecontrol;
    private CalibrateCommand cc;
    private ASCommand averagespeedcommand;
    private int currentSpeed = 0;
    private Calibrate calibration;
    private double Distance = 15000;
    private DriverShaftSensor driveshaftsensor;
    private AverageSpeed averagespeed;
    private ArrayList<Integer> arrSpeed = new ArrayList<>();
    private FuelConsumptionsensor fuel;
    private MaintenanceSensor maintanence;
    private int ElectricalSignal;
    private AcceleratorPedal accpedal;
    private CruiseControlCommand cruisecontrolcommand;

    public Vehicle() {
        gui = new GUI();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        averagespeed = new AverageSpeed(this);
        calibration = new Calibrate(this);
        driveshaftsensor = new DriverShaftSensor(this);
        driveshaftsensor.start();
        accpedal = new AcceleratorPedal(this);
        maintanence = new MaintenanceSensor(this);
        accpedal.start();
        maintanence.start();
        cruisecontrol = new CruiseControl(this);

        fuel = new FuelConsumptionsensor(this);
        fuel.start();
        //config.sendEvent(new MaintenanceMonitoring(false));

    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public AcceleratorPedal getAccpedal() {
        return accpedal;
    }

    public void setAccpedal(AcceleratorPedal accpedal) {
        this.accpedal = accpedal;
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        if (running) {
            gui.getjTextField1().setText("ENGINE IS ON");
        } else {
            gui.getjTextField1().setText("ENGINE IS OFF");

        }
        this.running = running;
    }

    public CruiseControl getCruisecontrol() {
        return cruisecontrol;
    }

    public void setCruisecontrol(CruiseControl cruisecontrol) {
        this.cruisecontrol = cruisecontrol;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int accc) {
        currentSpeed = accc;

    }

    public Calibrate getCalibration() {
        return calibration;
    }

    public void setCalibration(Calibrate calibration) {
        this.calibration = calibration;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double Distance) {
        this.Distance = Distance;
    }

    public DriverShaftSensor getDriveshaftsensor() {
        return driveshaftsensor;
    }

    public void setDriveshaftsensor(DriverShaftSensor driveshaftsensor) {
        this.driveshaftsensor = driveshaftsensor;
    }

    public AverageSpeed getAveragespeed() {
        return averagespeed;
    }

    public void setAveragespeed(AverageSpeed averagespeed) {
        this.averagespeed = averagespeed;
    }

    public FuelConsumptionsensor getFuel() {
        return fuel;
    }

    public void setFuel(FuelConsumptionsensor fuel) {
        this.fuel = fuel;
    }

    public MaintenanceSensor getMaintanence() {
        return maintanence;
    }

    public ArrayList<Integer> getArrSpeed() {
        return arrSpeed;
    }

    public void setArrSpeed(int speed) {
        arrSpeed.add(speed);
    }

    public void setMaintanence(MaintenanceSensor maintanence) {
        this.maintanence = maintanence;
    }

    public int getElectricalSignal() {
        return ElectricalSignal;
    }

    public void setElectricalSignal(int ElectricalSignal) {
        this.ElectricalSignal = ElectricalSignal;
    }

    public CruiseControlCommand getCruisecontrolcommand() {
        return cruisecontrolcommand;
    }

    public void setCruisecontrolcommand(CruiseControlCommand cruisecontrolcommand) {
        this.cruisecontrolcommand = cruisecontrolcommand;
    }
    public int s = 0;

    public void StartCruiseControl() {
        cruisecontrol.start();
        
        if (s == 0) {
            cruisecontrol.setSavedspeed(currentSpeed);
            s = 1;
        }

//        if (gui.getAcivate().getModel().isPressed()) {
//            //gui.getjTextField1().setText("Cruise Control ON SPEED: " + this.cruisecontrol.getSavedspeed());
//            
//        } else if (gui.getDeactivate().isSelected()) {
//            this.accpedal.resume();
//            gui.getAcivate().setEnabled(true);
//            gui.getResume().setEnabled(true);
//            gui.getjTextField1().setText("Cruise Control Deactivated, Speed: " + this.cruisecontrol.getSavedspeed());
//        } else if (gui.getResume().isSelected()) {
//            gui.getAcivate().setEnabled(false);
//            gui.getDeactivate().setEnabled(true);
//            gui.getjTextField1().setText("Cruise control Resumed, Speed is: " + this.cruisecontrol.getSavedspeed());
//        }
    }

    public void releaseaccpedal() {
        accpedal.getAcceleration(-1);
    }

    public void StartCalibration(String command, int distance) {
        if (command.equals("START_CALIBRATE")) {
            cc = CalibrateCommand.START_CALIBRATE;
            calibration.start();
        } else if (command.equals("STOP_CALIBRATE")) {
            cc = CalibrateCommand.STOP_CALIBRATE;
            System.out.println("Speed: " + driveshaftsensor.calcualteSpeed(distance));
            calibration.stop();
        }
    }

    public void MonitorAverageSpeed(String command) {
        if (command.equals("START_TRIP")) {
            averagespeedcommand = ASCommand.START_TRIP;
            averagespeed.start();

        } else if (command.equals("AVERAGE_SPEED")) {
            float n = averagespeed.calculateAverageSpeed(arrSpeed);
            System.out.println(n);
            n = Math.round(n);
            gui.getAverageSpeedDisplay().setText(Float.toString(n));
        }
    }

    public void FuelConsumptionMonitoring() {
        //fuel added from text field , pasrse int  
        //if (this.getGui().getEnter().getModel().isPressed()) {
        int fuel_added = Integer.parseInt(gui.getFuelEntered().getText());
        System.out.print("fuel_added " + fuel_added);
        fuel.CalcConsumption(fuel_added);
        gui.getjTextField2().setText(Double.toString(fuel.getTotalFuel()));
        //}
    }

}
