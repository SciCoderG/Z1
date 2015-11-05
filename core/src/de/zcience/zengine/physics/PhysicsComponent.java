package de.zcience.zengine.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Component with the Box2d-body for the entity
 * 
 * @author David_000
 *
 */
public class PhysicsComponent implements Component, Poolable
{

    private Vector2 position, linearVelocity;

    private float angle, angularVelocity;

    private Body body;

    private PhysicsSystem system;

    /**
     * initialises the physics-body
     * 
     * @param bodyDef
     *            Box2D body definition
     * @param system
     *            PhysicsSystem
     * @param entity
     *            Entity to which the Component is added
     */
    public void init(BodyDef bodyDef, PhysicsSystem system)
    {
        this.system = system;
        if (body != null)
        {
            reset();
        }
        body = system.getWorld().createBody(bodyDef);
        this.position = body.getPosition();
        this.linearVelocity = body.getLinearVelocity();
        this.angle = body.getAngle();
        this.angularVelocity = body.getAngularVelocity();
    }

    /**
     * Update the two most important values for rendering
     * 
     * @param newPosition
     *            position of the body
     * @param newAngle
     *            angle of the body
     */
    public void update(Vector2 newPosition, float newAngle)
    {
        this.position = newPosition;
        this.angle = newAngle;
    }

    /**
     * Creates a fixture for the Box2D body and sets the user data automatically
     * to the component
     * 
     * @param fixtureDef
     * @return
     */
    public Fixture createFixture(PhysicsFixtureDef fixtureDef)
    {
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        return fixture;
    }

    /**
     * Get the Box2d body for more information
     * 
     * @return
     */
    public Body getBody()
    {
        return body;
    }

    @Override
    public void reset()
    {
        system.getWorld().destroyBody(body);
        body = null;
        system = null;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getLinearVelocity()
    {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector2 linearVelocity)
    {
        this.linearVelocity = linearVelocity;
    }

    public float getAngle()
    {
        return angle;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
    }

    public float getAngularVelocity()
    {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity)
    {
        this.angularVelocity = angularVelocity;
    }

}
