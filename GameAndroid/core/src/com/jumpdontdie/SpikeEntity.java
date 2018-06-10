package com.jumpdontdie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by INSPIRON on 10/6/2018.
 */

public class SpikeEntity extends Actor{
    /** Spike texture. */
    private Texture texture;

    /** The world this spike is in. */
    private World world;

    /** The body assigned to this spike. */
    private Body body;

    /** The fixture assigned to this spike. */
    private Fixture fixture;

    /**
     * Create some spike
     *
     * @param world
     * @param texture
     * @param x  horizontal position for the center of the spike (meters)
     * @param y  vertical position for the base of the spike (meters)
     */
    public SpikeEntity(World world, Texture texture, float x, float y) {
        this.world = world;
        this.texture = texture;

        // Create the body.
        BodyDef def = new BodyDef();                // (1) Give it some definition.
        def.position.set(x, y + 0.5f);              // (2) Position the body on the world.
        body = world.createBody(def);               // (3) Create the body.

        // Now give it a shape.
        PolygonShape box = new PolygonShape();      // (1) We will make a polygon.
        Vector2[] vertices = new Vector2[3];        // (2) However vertices will be manually added.
        vertices[0] = new Vector2(-0.5f, -0.5f);    // (3) Add the vertices for a triangle.
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.5f);
        box.set(vertices);                          // (4) And put them in the shape.
        fixture = body.createFixture(box, 1);       // (5) Create the fixture.
        fixture.setUserData("spike");               // (6) And set the user data to enemy.
        box.dispose();                              // (7) Destroy the shape when you don't need it.

        // Position the actor in the screen by converting the meters to pixels.
        setPosition((x - 0.5f) * Constants.PIXELS_IN_METER, y * Constants.PIXELS_IN_METER);
        setSize(Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
