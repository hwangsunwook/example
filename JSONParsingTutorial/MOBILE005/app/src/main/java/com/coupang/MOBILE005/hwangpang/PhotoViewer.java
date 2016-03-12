package com.coupang.MOBILE005.hwangpang;

/**
 * Created by hwang on 2016-02-04.
 */
public class PhotoViewer {

    private String name;
    private String height;
    private String width;
    private String image;
    private String day;

    public PhotoViewer() {
        // TODO Auto-generated constructor stub
    }
    public PhotoViewer(String name, String width, String height, String image , String day) {
        super();
        this.name = name;
        this.width = width;
        this.height = height;
        this.image = image;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return width;
    }

    public void setCountry(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDob() {
        return day;
    }

    public void setDob(String day) {
        this.day = day;
    }

}
