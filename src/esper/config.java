/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esper;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import events.AverageSpeedMonitoring;
import events.Calibrating;
import events.CruiseControlMonitoring;
import events.DistanceEvent;
import events.DriveShaftMesuring;
import events.FuelConsumptionMonitoring;
import events.MaintenanceMonitoring;
import events.SpeedEvent;
import events.VehicleRunning;

/**
 *
 * @author DELL
 */
public class config {

    private static EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

    public static void registerEvents() {
        engine.getEPAdministrator().getConfiguration().addEventType(AverageSpeedMonitoring.class);
        engine.getEPAdministrator().getConfiguration().addEventType(VehicleRunning.class);
        engine.getEPAdministrator().getConfiguration().addEventType(Calibrating.class);
        engine.getEPAdministrator().getConfiguration().addEventType(DriveShaftMesuring.class);
        engine.getEPAdministrator().getConfiguration().addEventType(DistanceEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(SpeedEvent.class);
        engine.getEPAdministrator().getConfiguration().addEventType(MaintenanceMonitoring.class);
        engine.getEPAdministrator().getConfiguration().addEventType(CruiseControlMonitoring.class);
        engine.getEPAdministrator().getConfiguration().addEventType(FuelConsumptionMonitoring.class);
        System.out.println("Events Successfully Registered.");
    }

    public static EPStatement createStatement(String s) {
        EPStatement result = engine.getEPAdministrator().createEPL(s);
        System.out.println("EPL Statement Successfully Created.");
        return result;
    }

    public static void sendEvent(Object o) {
        engine.getEPRuntime().sendEvent(o);
    }

}
