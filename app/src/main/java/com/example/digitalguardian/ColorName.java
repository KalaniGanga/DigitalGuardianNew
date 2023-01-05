package com.example.digitalguardian;

public class ColorName {
    public int r, g, b;
    public String name;

    public ColorName(String name, int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.name = name;
    }

    public int computeMSE(int pixR, int pixG, int pixB) {
        return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
                * (pixB - b)) / 3);
    }

    public String getName() {
        return name;
    }
}
