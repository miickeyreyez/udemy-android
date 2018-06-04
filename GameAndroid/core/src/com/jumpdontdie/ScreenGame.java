package com.jumpdontdie;

import com.badlogic.gdx.Screen;

/**
 * Created by INSPIRON on 3/6/2018.
 */

public abstract class ScreenGame implements Screen {

    protected MainGame game;

    public ScreenGame(MainGame game)
    {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
