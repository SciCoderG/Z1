package de.zcience.zengine.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Physicssystem updating the box2d world. Should have a set Intervall to ensure
 * PhysicsCalculations are always the same
 * 
 * @author David_000
 *
 */
public class PhysicsSystem extends IteratingSystem {

	private World world;
	
	

	public PhysicsSystem(Vector2 gravity, boolean doSleep, double dt) {
		super(Family.all(PhysicsComponent.class).get());
		
		Box2D.init();
		this.world = new World(gravity, doSleep);
	}

	@Override
	public void update(float deltaTime) {

		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
}
