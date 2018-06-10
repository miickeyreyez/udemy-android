package com.jumpdontdie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by INSPIRON on 10/6/2018.
 */

public class FloorEntity extends Actor {
    /** The textures we use to display the floor, proving that you can use multiple textures. */
    private Texture floor, overfloor;

    /** The world instance this floor has to live in. */
    private World world;

    /** The bodies for the floor. You see here the main body for the floor, and the left border. */
    private Body body, leftBody;

    /** The fixtures assigned to both bodies. This gives bodies shape. */
    private Fixture fixture, leftFixture;

    /**
     * Create a new floor
     *
     * @param world
     * @param floor
     * @param overfloor
     * @param x  left border for the floor (meters)
     * @param width  how wide the floor is (meters)
     * @param y  top border for the floor (meters)
     */
    public FloorEntity(World world, Texture floor, Texture overfloor, float x, float width, float y) {
        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        // Create the floor body.
        BodyDef def = new BodyDef();                // (1) Provide some definition.
        def.position.set(x + width / 2, y - 0.5f);  // (2) Center the floor in the coordinates given
        body = world.createBody(def);               // (3) Create the floor. Easy.

        // Give it a box shape.
        PolygonShape box = new PolygonShape();      // (1) Create the polygon shape.
        box.setAsBox(width / 2, 0.5f);              // (2) Give it some size.
        fixture = body.createFixture(box, 1);       // (3) Create a fixture.
        fixture.setUserData("floor");               // (4) Set the user data for the fixture.
        box.dispose();                              // (5) Destroy the shape.

        // Now create the left body. This body is spiky, if you hit this body, you die. It is
        // on the left border of the floor and it is only used when you have to jump some stairs.
        // It works the same than the previous one.
        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x, y - 0.55f);
        leftBody = world.createBody(leftDef);

        // As well as the fixture. Remember, use spike user data to make it act like an enemy.
        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.02f, 0.45f);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("spike");
        leftBox.dispose();

        // Now place the actor in the stage by converting the coordinates given in meters to px.
        setSize(width * Constants.PIXELS_IN_METER, Constants.PIXELS_IN_METER);
        setPosition(x * Constants.PIXELS_IN_METER, (y - 1) * Constants.PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Render both textures.
        batch.draw(floor, getX(), getY(), getWidth(), getHeight());
        batch.draw(overfloor, getX(), getY() + 0.9f * getHeight(), getWidth(), 0.1f * getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
