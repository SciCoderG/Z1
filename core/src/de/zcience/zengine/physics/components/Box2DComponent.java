package de.zcience.zengine.physics.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Pool.Poolable;

import de.zcience.zengine.physics.systems.PhysicsSystem;
import de.zcience.zengine.physics.utils.PhysicsFixtureDef;

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

    private Entity entity;
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
    public void init(BodyDef bodyDef, PhysicsSystem system, Entity entity)
    {
        this.system = system;
        this.setEntity(entity);
        if (body != null)
        {
            reset();
        }
        body = system.getWorld().createBody(bodyDef);
        body.setUserData(entity);
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

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

}
