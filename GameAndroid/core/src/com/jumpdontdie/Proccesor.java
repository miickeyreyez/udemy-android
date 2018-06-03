package com.jumpdontdie;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by INSPIRON on 3/6/2018.
 */

public class Proccesor extends InputAdapter {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //return super.touchDown(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //return super.touchUp(screenX, screenY, pointer, button);
        return true;
    }
}
