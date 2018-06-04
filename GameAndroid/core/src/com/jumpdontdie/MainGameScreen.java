package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by INSPIRON on 3/6/2018.
 */

public class MainGameScreen extends ScreenGame {

    private Stage stage;
    private PlayerActor playerActor;
    private PhilActor philActor;
    private Texture texture;
    private Texture texturePhil;

    public MainGameScreen(MainGame game) {
        super(game);
        texture = new Texture("hercules.png");
        texturePhil = new Texture("phil.png");
    }

    @Override
    public void show() {
        stage = new Stage();

        stage.setDebugAll(true);

        playerActor = new PlayerActor(texture);
        philActor = new PhilActor(texturePhil);

        stage.addActor(playerActor);
        stage.addActor(philActor);

        playerActor.setPosition(0,0);
        philActor.setPosition(500,0);

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.5f,0.8f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        comprobarColisiones();
        stage.draw();
    }

    private void comprobarColisiones() {
        if(playerActor.isAlive() && (playerActor.getX() + playerActor.getWidth()) > philActor.getX()) {
            playerActor.setAlive(false);
        }
    }

    @Override
    public void dispose(){
        texture.dispose();
    }
}
