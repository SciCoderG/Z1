package de.zcience.zengine.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Component with the Box2d-body for the entity. To properly render the body
 * with the body, you will have to add a PhysicsComponent to that Entity too!
 * 
 * @author David_000
 *
 */
public class Box2DComponent implements Component, Poolable
{

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

}
