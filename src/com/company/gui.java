package com.company;

import javax.swing.*;
import java.awt.*;

public class gui {
    private final JFrame jFrame;
    private final Canvas canvas;
    private Graphics2D g;
    public keyListener listener = new keyListener();
    public gui(){
        jFrame = new JFrame("Raytracer");
        canvas = new Canvas();
        jFrame.add(canvas);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
        canvas.addKeyListener(listener);
        jFrame.requestFocus();
        canvas.createBufferStrategy(2);
        g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, 500, 500);
    }
    public void update(){
        canvas.getBufferStrategy().show();
        canvas.update(g);
        g.dispose();
        g = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, 500, 500);
    }
    public void putPixel(int x, int y, Color color){
        g.setColor(color);
        g.drawRect(x+250,-1*y+250,1,1);
    }
    public void drawLine(int[] pointA, int[] pointB){
        g.drawLine(pointA[0]+250, -1*pointA[1]+250,pointB[0]+250, -1*pointB[1]+250);
    }
    public void drawShadedTriangle(Triangle triangle){
        //Draw the horizontal segments
        for (int y = (int) Math.floor(triangle.points[0][1]); y <= triangle.points[2][1]; y++) {
            int y_index = (int) (y - triangle.points[0][1]);
            int x_l = (int) triangle.xLeft[y_index];
            int x_r = (int) triangle.xRight[y_index];
            double[] h_segment = Main.lerp(x_l, triangle.hLeft[y_index], x_r, triangle.hRight[y_index]);
            for (int x = x_l; x <= x_r; x++) {
                double[] extracted = Main.decomposeColor(triangle.color);
                extracted = Main.scaleVector(extracted, h_segment[x-x_l]);
                Color shadedColor = Main.reconstructColor(extracted);
                putPixel(x,y,shadedColor);
            }
        }
    }
}
