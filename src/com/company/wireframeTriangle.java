package com.company;

import java.awt.*;

public class wireframeTriangle {
    public int[][] points = {{0,0}, {0,0}, {0,0}};
    public Color color;
    wireframeTriangle(int[] indices, int[][] points, Color c){
        color = c;
        for (int i = 0; i < this.points.length; i++) {
            this.points[i] = points[indices[i]];
        }
    }
}
