package com.jumpdontdie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by INSPIRON on 3/6/2018.
 */

public class PhilActor extends Actor {

    private TextureRegion region;
    private Texture phil;

    public PhilActor(Texture phil) {
        this.phil = phil;
        setSize(150,250);
    }

    @Override
    public void act(float delta) {
        //super.act(delta);
        setX(getX() - 250 * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch,parentAlpha);
        batch.draw(phil,getX(),getY(),150,250);
    }
}
