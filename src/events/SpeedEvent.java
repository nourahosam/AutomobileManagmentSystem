/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author DELL
 */
public class SpeedEvent {
   
    private int speed;


    public SpeedEvent(int speed) {
        this.speed = speed;
        //System.out.println(speed + "help");
    }

    public int getSpeed() {
        return speed;
    }

    
}
