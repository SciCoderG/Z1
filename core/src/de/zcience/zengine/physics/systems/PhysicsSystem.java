package de.zcience.zengine.physics.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import de.zcience.zengine.physics.components.Box2DComponent;
import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.physics.components.VelocityComponent;
import de.zcience.zengine.utils.Constants;
import de.zcience.zengine.utils.ZComponentMapper;

/**
 * Physicssystem updating the box2d world. Should have a set Intervall to ensure
 * PhysicsCalculations are always the same
 * 
 * @author David_000
 *
 */
public class PhysicsSystem extends IteratingSystem {

	private World world;

	private float accumulator = 0.0f;

	@SuppressWarnings("unchecked")
	public PhysicsSystem() {
		super(Family.all(Box2DComponent.class).one(PositionComponent.class, VelocityComponent.class).get());

		Box2D.init();
		this.world = new World(Constants.B2D_GRAVITY, true);
	}

	@Override
	public void update(float deltaTime) {

		float frameTime = deltaTime;
		if (frameTime > 0.25f) {
			frameTime = 0.25f; // Avoid spiral of death by limiting the max of
								// frameTime
		}
		accumulator += frameTime;

		// Run integration, until we are one integration in front of present
		// time
		while (accumulator >= Constants.PHYSICS_TIMESTEP) {
			this.integrate();
			world.step(Constants.PHYSICS_TIMESTEP, Constants.B2D_VELOCITY_ITERATIONS,
					Constants.B2D_POSITION_ITERATIONS);
			accumulator -= Constants.PHYSICS_TIMESTEP;
		}
		float alpha = accumulator / Constants.PHYSICS_TIMESTEP;
		assert (alpha <= 1.0f); // TODO: Only in Debug
		this.interpolate(alpha);
	}

	/**
	 * Integrates the PhysicsComponents by a full Timestep, by just copying the
	 * values from the box2d bodies without interpolating
	 */
	private void integrate() {
		Box2DComponent box2D;
		VelocityComponent velocity;
		PositionComponent position;

		for (int i = 0; i < getEntities().size(); ++i) {
			Entity entity = getEntities().get(i);
			// should never be null because of family specified in constructor
			box2D = ZComponentMapper.box2D.get(entity);

			position = ZComponentMapper.position.get(entity);
			if (null != position) {
				position.setPosition(box2D.getBody().getPosition());
				position.setAngle(box2D.getBody().getAngle());
			}

			velocity = ZComponentMapper.velocity.get(entity);
			if (null != velocity) {
				velocity.setLinearVelocity(box2D.getBody().getLinearVelocity());
				velocity.setAngularVelocity(box2D.getBody().getAngularVelocity());
			}
		}
	}

	/**
	 * Interpolate between last state and current physicsstate by alpha
	 * 
	 * @param alpha
	 */
	private void interpolate(float alpha) {
		Box2DComponent box2D;
		VelocityComponent velocity;
		PositionComponent position;

		for (int i = 0; i < getEntities().size(); ++i) {
			Entity entity = getEntities().get(i);
			// should never be null because of family specified in constructor
			box2D = ZComponentMapper.box2D.get(entity);
			position = ZComponentMapper.position.get(entity);
			velocity = ZComponentMapper.velocity.get(entity);

			if (null != position) {
				float lerpedAngle = MathUtils.lerpAngle(position.getAngle(), box2D.getBody().getAngle(), alpha);
				position.setAngle(lerpedAngle);

				Vector2 lerpedPosition = new Vector2();
				lerpedPosition.x = MathUtils.lerp(position.getPosition().x, box2D.getBody().getPosition().x,
						alpha);
				lerpedPosition.y = MathUtils.lerp(position.getPosition().y, box2D.getBody().getPosition().y,
						alpha);
				position.setPosition(lerpedPosition);
			}
			if (null != velocity) {
				float lerpedAngularVelocity = MathUtils.lerpAngle(velocity.getAngularVelocity(),
						box2D.getBody().getAngularVelocity(), alpha);
				velocity.setAngularVelocity(lerpedAngularVelocity);

				Vector2 lerpedLinearVelocity = new Vector2();
				lerpedLinearVelocity.x = MathUtils.lerp(velocity.getLinearVelocity().x,
						box2D.getBody().getLinearVelocity().x, alpha);
				lerpedLinearVelocity.y = MathUtils.lerp(velocity.getLinearVelocity().y,
						box2D.getBody().getLinearVelocity().y, alpha);
				velocity.setLinearVelocity(lerpedLinearVelocity);
			}
		}
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub

	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
