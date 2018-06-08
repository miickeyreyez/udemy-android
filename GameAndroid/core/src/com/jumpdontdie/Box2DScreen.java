package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by INSPIRON on 7/6/2018.
 */

public class Box2DScreen extends MainGameScreen {
    public Box2DScreen(MainGame game) {
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Body phil;
    private Fixture philFixture;

    @Override
    public void show() {
        //Gravedad y que duerma, cuando no esté ejecutándose no haga nada
        world = new World(new Vector2(0,-10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32,18);
        BodyDef philDef = createPhilBodyDef();
        phil = world.createBody(philDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);
        phil.createFixture(shape, 1);
        shape.dispose();
    }

    private BodyDef createPhilBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    @Override
    public void dispose() {
        world.destroyBody(phil);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }
}
