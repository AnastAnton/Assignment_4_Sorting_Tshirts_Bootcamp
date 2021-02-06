/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author George.Pasparakis
 */
public class Timer {
    private long startTime = 0;
    private long endTime = 0;
    
    public Timer() {
        this.start();
    }
    
    public void start() {
        startTime = System.currentTimeMillis();
//        System.out.println("start: " + startTime);
    }
    
    public void stop() {
        endTime = System.currentTimeMillis();
//        System.out.println("end: " + endTime);
    }
    
    public long elapsedTime() {
        return(endTime - startTime);
    }
    
}
