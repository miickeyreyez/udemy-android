package com.udemy.sqlite;

/**
 * Created by angel on 9/10/17.
 */

public class Car
{
    private int id;
    private String marca;
    private String modelo;

    public Car(int id, String marca, String modelo)
    {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}

