package com.udemy.models;

/**
 * Created by angel on 8/10/17.
 */

public class Jugador
{
    public String name;
    public int avatar;

    public Jugador(String name, int avatar)
    {
        this.name = name;
        this.avatar = avatar;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
