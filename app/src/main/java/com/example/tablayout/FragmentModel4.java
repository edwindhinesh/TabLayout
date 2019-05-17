package com.example.tablayout;

public class FragmentModel4 {
    private int images;
    private String names;
    private int dots;

    public FragmentModel4() {
    }

    public FragmentModel4(int images, String names, int dots) {
        this.images = images;
        this.names = names;
        this.dots = dots;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }
}
