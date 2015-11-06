package de.zcience.zengine.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import de.zcience.zengine.utils.Constants;
import de.zcience.zengine.utils.ZComponentMapper;

/**
 * Physicssystem updating the box2d world. Should have a set Intervall to ensure
 * PhysicsCalculations are always the same
 * 
 * @author David_000
 *
 */
public class PhysicsSystem extends IteratingSystem
{

    private World world;

    private float accumulator = 0.0f;

    @SuppressWarnings("unchecked")
    public PhysicsSystem()
    {
        super(Family.all(Box2DComponent.class, PhysicsComponent.class).get());

        Box2D.init();
        this.world = new World(Constants.B2D_GRAVITY, true);
    }

    @Override
    public void update(float deltaTime)
    {

        float frameTime = deltaTime;
        if (frameTime > 0.25f)
        {
            frameTime = 0.25f; // Avoid spiral of death by limiting the max of
                               // frameTime
        }
        accumulator += frameTime;

        // Run integration, until we are one integration in front of present
        // time
        while (accumulator >= Constants.PHYSICS_TIMESTEP * 2)
        {
            world.step(Constants.PHYSICS_TIMESTEP, Constants.B2D_VELOCITY_ITERATIONS, Constants.B2D_POSITION_ITERATIONS);
            accumulator -= Constants.PHYSICS_TIMESTEP;
        }
        // update the RenderingState to the step before present
        if (frameTime >= Constants.PHYSICS_TIMESTEP * 2)
        {
            this.integrate();
        }
        // integrate one more time to get to present
        world.step(Constants.PHYSICS_TIMESTEP, Constants.B2D_VELOCITY_ITERATIONS, Constants.B2D_POSITION_ITERATIONS);

        float alpha = accumulator / Constants.PHYSICS_TIMESTEP;
        assert (alpha <= 1.0f); // TODO: Only in Debug
        this.interpolate(alpha);
    }

    /**
     * Integrates the PhysicsComponents by a full Timestep, by just copying the
     * values from the box2d bodies without interpolating
     */
    private void integrate()
    {
        for (int i = 0; i < getEntities().size(); ++i)
        {
            Entity entity = getEntities().get(i);
            // should never be null because of family specified in constructor
            Box2DComponent box2D = ZComponentMapper.box2D.get(entity);
            PhysicsComponent physics = ZComponentMapper.physics.get(entity);

            physics.update(box2D.getBody().getPosition(), box2D.getBody().getAngle());
        }
    }

    /**
     * Interpolate between last state and current physicsstate by alpha
     * 
     * @param alpha
     */
    private void interpolate(float alpha)
    {
        for (int i = 0; i < getEntities().size(); ++i)
        {
            Entity entity = getEntities().get(i);
            // should never be null because of family specified in constructor
            Box2DComponent box2D = ZComponentMapper.box2D.get(entity);
            PhysicsComponent physics = ZComponentMapper.physics.get(entity);

            physics.setAngle(MathUtils.lerpAngle(physics.getAngle(), box2D.getBody().getAngle(), alpha));
            physics.setAngularVelocity(MathUtils.lerpAngle(physics.getAngularVelocity(), box2D.getBody().getAngularVelocity(), alpha));

            Vector2 tempVec = new Vector2(); // only one object creation per
                                             // loop iteration
            tempVec.x = MathUtils.lerp(physics.getLinearVelocity().x, box2D.getBody().getLinearVelocity().x, alpha);
            tempVec.y = MathUtils.lerp(physics.getLinearVelocity().y, box2D.getBody().getLinearVelocity().y, alpha);
            physics.setLinearVelocity(tempVec);

            tempVec.x = MathUtils.lerp(physics.getPosition().x, box2D.getBody().getPosition().x, alpha);
            tempVec.y = MathUtils.lerp(physics.getPosition().y, box2D.getBody().getPosition().y, alpha);
            physics.setPosition(tempVec);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        // TODO Auto-generated method stub

    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

}
