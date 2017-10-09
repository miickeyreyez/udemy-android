package com.udemy.models;

/**
 * Created by angel on 8/10/17.
 */

public class Fruta
{
    private String name;
    private String description;
    private int imgBackground;
    private int imgIcon;
    private int quantity;

    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_QUANTITY = 0;

    public Fruta()
    {

    }

    public Fruta(String name, String description, int imgBackground, int imgIcon, int quantity)
    {
        this.name = name;
        this.description = description;
        this.imgBackground = imgBackground;
        this.imgIcon = imgIcon;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgBackground() {
        return imgBackground;
    }

    public void setImgBackground(int imgBackground) {
        this.imgBackground = imgBackground;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity)
    {
        if(this.quantity < LIMIT_QUANTITY)
        {
            this.quantity = this.quantity + quantity;
        }
    }

    public void resetQuantity()
    {
        this.quantity = RESET_QUANTITY;
    }
}
