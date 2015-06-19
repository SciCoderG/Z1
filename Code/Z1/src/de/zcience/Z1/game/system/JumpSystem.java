package de.zcience.Z1.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

import de.zcience.Z1.game.components.InputComponent;
import de.zcience.Z1.game.components.JumpComponent;
import de.zcience.Z1.physics.PhysicsBodyComponent;

public class JumpSystem extends IteratingProfilingSystem {

	public JumpSystem(int priority) {
		super(Family.all(InputComponent.class, PhysicsBodyComponent.class,
				JumpComponent.class).get(), priority);
		profiler.setMessage("jump-");
		//setProfiling(true);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		if (entity.getComponent(JumpComponent.class).force > 0.0f) {
			entity.getComponent(PhysicsBodyComponent.class)
					.getBody()
					.applyForceToCenter(0.0f,
							entity.getComponent(JumpComponent.class).force,
							true);
			entity.getComponent(JumpComponent.class).force = (entity
					.getComponent(JumpComponent.class).force
					- entity.getComponent(JumpComponent.class).forceDown > 0) ? entity
					.getComponent(JumpComponent.class).force
					- entity.getComponent(JumpComponent.class).forceDown : 0.0f;
		}
		// Jump if true

		if (entity.getComponent(JumpComponent.class).groundContacts > 0
				&& entity.getComponent(InputComponent.class).jump) {
			float force = 900.0f;
			entity.getComponent(JumpComponent.class).force = force;
			entity.getComponent(JumpComponent.class).forceDown = force / 40;
		}
	}
}
