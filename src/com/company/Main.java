package com.company;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        Triangle triangle = new Triangle(new double[][]{{-200,-250,0.3},{200,50,0.1},{20,250,1.0}}, Color.green);
        gui g = new gui();
        while (true){
            g.drawShadedTriangle(triangle);
            g.update();
        }
    }
    public static double[] decomposeColor(Color A) {
        return new double[]{(double) A.getRed(), (double) A.getGreen(), (double) A.getBlue()};
    }
    public static Color reconstructColor(double[] A) {
        return new Color((int) Math.max(0, Math.min(255, Math.round(A[0]))), (int) Math.max(0, Math.min(255, Math.round(A[1]))), (int) Math.max(0, Math.min(255, Math.round(A[2]))));
    }
    public static double[] scaleVector(double[] v1, double d1) {
        return new double[]{v1[0] * d1,
                v1[1] * d1,
                v1[2] * d1};
    }
    public static void swapPoints(double[] pointA, double[] pointB){
        double[] swapper = pointA.clone();
        System.arraycopy(pointB, 0, pointA, 0, pointB.length);
        System.arraycopy(swapper, 0, pointB, 0, pointB.length);
    }
    public static double[] lerp(int i0, double d0, int i1, double d1){
        assert i1 >= i0;
        assert d1 >= d0;
        if (i0 == i1){
            return new double[]{d0};
        }
        double[] d = new double[Math.abs(i1-i0)+1];
        double a = (d1 - d0)/(i1 - i0);
        double d3 = d0;
        for (int i = i0; i <= i1; i++) {
            d[i-i0] = d3;
            d3=d3+a;
        }
        return d;
    }
    public static <T> T concatenate(T a, T b) {
        if (!a.getClass().isArray() || !b.getClass().isArray()) {
            throw new IllegalArgumentException();
        }

        Class<?> resCompType;
        Class<?> aCompType = a.getClass().getComponentType();
        Class<?> bCompType = b.getClass().getComponentType();

        if (aCompType.isAssignableFrom(bCompType)) {
            resCompType = aCompType;
        } else if (bCompType.isAssignableFrom(aCompType)) {
            resCompType = bCompType;
        } else {
            throw new IllegalArgumentException();
        }

        int aLen = Array.getLength(a);
        int bLen = Array.getLength(b);

        @SuppressWarnings("unchecked")
        T result = (T) Array.newInstance(resCompType, aLen + bLen);
        System.arraycopy(a, 0, result, 0, aLen);
        System.arraycopy(b, 0, result, aLen, bLen);

        return result;
    }
}
