package com.company;

import java.awt.*;

public class rectPrism implements renderable{
    private double[][] points;
    private final Color[] color;
    private final static int[][] indicesOfTriangles = {{0,1,2},{0,2,3},{4,0,3},{4,3,7},{5,4,7},{5,7,6},{1,5,6},{1,6,2},{4,5,1},{4,1,0},{2,6,7},{2,7,3}};
    rectPrism(double[][] points, Color[] color){
        this.points = points;
        this.color = color;
    }
    @Override
    public void render(gui g) {
        int[][] projectedPoints = new int[points.length][3];
        for (int i = 0; i < points.length; i++) {
            projectedPoints[i] = Main.projectVertex(points[i]);
        }
        for (int i = 0; i < indicesOfTriangles.length; i++) {
            g.drawWireframeTriangle(new wireframeTriangle(indicesOfTriangles[i], projectedPoints, color[i/2]));
        }
    }
    public void translate(double[] translationVector){
        for (int i = 0; i < points.length; i++) {
            points[i] = Main.addVectors(points[i], translationVector);
        }
    }
}
