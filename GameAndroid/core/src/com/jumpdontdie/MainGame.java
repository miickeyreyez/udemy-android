package com.jumpdontdie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainGame extends Game {

	private AssetManager manager;

    public AssetManager getManager() {
        return manager;
    }

    @Override
	public void create() {
        manager = new AssetManager();
        manager.load("floor.png",Texture.class);
        manager.load("overfloor.png",Texture.class);
        manager.load("spike.png",Texture.class);
        manager.load("player.png",Texture.class);
        manager.load("gameover.png",Texture.class);
        manager.load("song.ogg",Music.class);
        manager.load("die.ogg",Sound.class);
        manager.load("jump.ogg",Sound.class);
        manager.finishLoading();
		//setScreen(new MainGameScreen(this));
		setScreen(new GameOverScreen(this));
	}
}