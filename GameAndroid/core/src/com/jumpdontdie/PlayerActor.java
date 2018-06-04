package com.jumpdontdie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by INSPIRON on 3/6/2018.
 */

public class PlayerActor extends Actor {

    private Texture jugador;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private boolean alive;

    public PlayerActor(Texture jugador) {
        this.alive = true;
        this.jugador = jugador;
        setSize(150,250);
    }

    @Override
    public void act(float delta) {
        //super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch,parentAlpha);
        batch.draw(jugador,getX(),getY(),150,250);
    }
}
