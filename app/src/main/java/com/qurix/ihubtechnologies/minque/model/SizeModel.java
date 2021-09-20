package com.qurix.ihubtechnologies.minque.model;

import java.io.Serializable;

public class SizeModel implements Serializable {
    int actualX;
    int actualY;
    int actualHight;
    int actualwidth;

    public SizeModel(int actualX, int actualY, int actualHight, int actualwidth) {
        this.actualX = actualX;
        this.actualY = actualY;
        this.actualHight = actualHight;
        this.actualwidth = actualwidth;
    }

    public int getActualX() {
        return actualX;
    }

    public void setActualX(int actualX) {
        this.actualX = actualX;
    }

    public int getActualY() {
        return actualY;
    }

    public void setActualY(int actualY) {
        this.actualY = actualY;
    }

    public int getActualHight() {
        return actualHight;
    }

    public void setActualHight(int actualHight) {
        this.actualHight = actualHight;
    }

    public int getActualwidth() {
        return actualwidth;
    }

    public void setActualwidth(int actualwidth) {
        this.actualwidth = actualwidth;
    }

    @Override
    public String toString() {
        return "SizeModel{" +
                "actualX=" + actualX +
                ", actualY=" + actualY +
                ", actualHight=" + actualHight +
                ", actualwidth=" + actualwidth +
                '}';
    }
}
