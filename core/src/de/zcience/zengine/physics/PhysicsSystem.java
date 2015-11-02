package de.zcience.zengine.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;

/**
 * Physicssystem updating the box2d world. Should have a set Intervall to ensure
 * PhysicsCalculations are always the same
 * 
 * @author David_000
 *
 */
public class PhysicsSystem extends IntervalIteratingSystem {

	public PhysicsSystem(Family family, float interval) {
		super(family, interval);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity) {
		// TODO Auto-generated method stub

	}

}
