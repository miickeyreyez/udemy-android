package com.jumpdontdie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture phil;
	int width;
	int height;
	private float widthImage, heightImage;
	private TextureRegion regionPhil;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("hercules.png");
		phil = new Texture("phil.png");

		//regionPhil = new TextureRegion(phil,0, 5, 0 ,5);

		Proccesor p = new Proccesor();
		Gdx.input.setInputProcessor(p);
	}

	@Override
	public void render () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		widthImage = img.getWidth();
		heightImage = img.getHeight();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, width - 150, height - 250,150,250);
		batch.draw(img, 0, 0,150,250);
		batch.draw(phil, 100, 0, 100,100);
		batch.end();

		if(Gdx.input.justTouched()){

		}

		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){

		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		phil.dispose();
	}
}
