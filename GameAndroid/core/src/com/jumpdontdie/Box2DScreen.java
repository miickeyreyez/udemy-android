package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
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
    private Body phil, suelo, hercules;
    private Fixture philFixture, sueloFixture, herculesFixture;
    private boolean debeSaltar, herculesSaltando, herculesVivo = true;

    @Override
    public void show() {
        //Gravedad y que duerma, cuando no esté ejecutándose no haga nada
        world = new World(new Vector2(0,-10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32f,18);
        camera.translate(0,1);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor")) ||
                        (fixtureA.getUserData().equals("floor") && fixtureB.getUserData().equals("player")))
                {
                    if(Gdx.input.justTouched()) {
                        debeSaltar = true;
                    }
                    herculesSaltando = false;
                }

                if((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("spike")) ||
                        (fixtureA.getUserData().equals("spike") && fixtureB.getUserData().equals("player")))
                {
                    herculesVivo = false;
                }

                /*if(fixtureA == herculesFixture && fixtureB == sueloFixture)
                if(fixtureA == sueloFixture && fixtureB == herculesFixture) {
                    herculesSaltando = false;
                }*/
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if(fixtureA == herculesFixture && fixtureB == sueloFixture) {
                    if(Gdx.input.justTouched()) {
                        debeSaltar = true;
                    }
                    herculesSaltando = true;
                }

                if(fixtureA == sueloFixture && fixtureB == herculesFixture) {
                    herculesSaltando = true;
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        //BodyDef philDef = createPhilBodyDef();
        hercules = world.createBody(createHerculesBodyDef());
        phil = world.createBody(createPhilBodyDef(8f));
        suelo = world.createBody(createSueloBodyDef());

        /*PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);
        philFixture = phil.createFixture(shape, 1);
        shape.dispose();*/

        PolygonShape herculesShape = new PolygonShape();
        herculesShape.setAsBox(0.5f,0.5f);
        herculesFixture = hercules.createFixture(herculesShape, 1);
        herculesShape.dispose();

        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500,1);
        sueloFixture = suelo.createFixture(sueloShape,1);
        sueloShape.dispose();

        philFixture = createPhilFixture(phil);

        herculesFixture.setUserData("player");
        sueloFixture.setUserData("floor");
        philFixture.setUserData("spike");
    }

    private BodyDef createHerculesBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,0);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createPhilBodyDef(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x,0.5f);
        //def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createSueloBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        //def.type = BodyDef.BodyType.StaticBody;
        return def;
    }

    private Fixture createPhilFixture(Body philBody) {
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        Fixture fix = phil.createFixture(shape,1);
        shape.dispose();
        return fix;
    }

    @Override
    public void dispose() {
        hercules.destroyFixture(herculesFixture);
        suelo.destroyFixture(sueloFixture);
        phil.destroyFixture(philFixture);
        world.destroyBody(phil);
        world.destroyBody(hercules);
        world.destroyBody(suelo);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(debeSaltar) {
            debeSaltar = false;
            saltar();
        }

        if(Gdx.input.justTouched() && !herculesSaltando) {
            debeSaltar = true;
        }

        if(herculesVivo) {
            float velocidadY = hercules.getLinearVelocity().y;
            hercules.setLinearVelocity(8,velocidadY);
        }

        world.step(delta,6,2);
        camera.update();
        renderer.render(world,camera.combined);
    }

    private void saltar() {
        Vector2 position = hercules.getPosition();
        hercules.applyLinearImpulse(0,20,position.x,position.y,true);
    }
}
