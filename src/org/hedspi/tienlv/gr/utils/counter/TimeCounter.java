/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.utils.counter;

/**
 *
 * @author Lev Tien
 */
public class TimeCounter {

    private long startTime;
    private long pickTime;

    public TimeCounter() {
        startTime = System.currentTimeMillis();
        pickTime = startTime;
    }

    // gap time from lastest pick time point
    // khoảng thời gian tính từ lúc pick time gần nhất
    public long getTimeGap() {
        long temp = pickTime;
        pickTime = System.currentTimeMillis();
        return pickTime - temp;
    }

    // gap time from begining
    // khoảng tgian tính từ lúc bắt đầu
    public long getWholeTimeGap() {
        pickTime = System.currentTimeMillis();
        return pickTime - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getPickTime() {
        return pickTime;
    }

    public void setPickTime(long pickTime) {
        this.pickTime = pickTime;
    }

    
}
