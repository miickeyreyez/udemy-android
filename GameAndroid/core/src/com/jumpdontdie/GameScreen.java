package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by INSPIRON on 9/6/2018.
 */

public class GameScreen extends  ScreenGame {

    private Stage stage;
    private World world;
    private PlayerEntity player;

    public GameScreen(MainGame game) {
        super(game);
        stage = new Stage(new FitViewport(640,360));
        world = new World(new Vector2(0,-10),true);
    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("player.png");
        player = new PlayerEntity(world, playerTexture, new Vector2(1,2));
        stage.addActor(player);
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4F,0.5F,0.8F,1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        world.step(delta,6,2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
