package com.company;

import java.awt.*;
import java.util.Arrays;

public class Triangle {
    public double[][] points;
    public Color color;
    public double[] xLeft;
    public double[] xRight;
    public double[] hLeft;
    public double[] hRight;
    Triangle(double[][] points, Color color){
        //Sort the points such that y0 <= y1 <= y2
        if(points[1][1] < points[0][1]){
            Main.swapPoints(points[1], points[0]);
        }
        if(points[2][1] < points[0][1]){
            Main.swapPoints(points[2], points[0]);
        }
        if(points[2][1] < points[1][1]){
            Main.swapPoints(points[1], points[2]);
        }
        this.points = points;
        this.color = color;
        //Compute the x coordinates and h-values of the triangle edges
        double[] x01 = Main.lerp((int) points[0][1],points[0][0], (int) points[1][1],points[1][0]);
        double[] h01 = Main.lerp((int) points[0][1],points[0][2], (int) points[1][1],points[1][2]);
        double[] x12 = Main.lerp((int) points[1][1],points[1][0], (int) points[2][1],points[2][0]);
        double[] h12 = Main.lerp((int) points[1][1],points[1][2], (int) points[2][1],points[2][2]);
        double[] x02 = Main.lerp((int) points[0][1],points[0][0], (int) points[2][1],points[2][0]);
        double[] h02 = Main.lerp((int) points[0][1],points[0][2], (int) points[2][1],points[2][2]);
        //Concat short sides
        double[] x012 = Main.concatenate(Arrays.copyOfRange(x01,0,x01.length-1), x12);
        double[] h012 = Main.concatenate(Arrays.copyOfRange(h01,0,h01.length-1), h12);
        //Determine which is left and which is right
        int m = (int) Math.floor(x02.length/2d);
        if (x02[m]<x012[m]){
            xLeft = x02;
            hLeft = h02;
            xRight = x012;
            hRight = h012;
        }
        else{
            xLeft=x012;
            hLeft=h012;
            xRight=x02;
            hRight=h02;
        }
    }
}
