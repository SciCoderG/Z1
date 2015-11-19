package de.zcience.z1.gameplay.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import de.zcience.z1.gameplay.input.InputComponent;
import de.zcience.z1.gameplay.utils.ZComponentMapper;
import de.zcience.zengine.physics.components.Box2DComponent;

public class JumpingSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public JumpingSystem() {
		super(Family.all(JumpComponent.class, Box2DComponent.class, InputComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		JumpComponent jComp = ZComponentMapper.jumping.get(entity);
		Box2DComponent b2D = ZComponentMapper.box2D.get(entity);
		InputComponent iComp = ZComponentMapper.input.get(entity);

		if (iComp.getDirection().y > 0 && jComp.getGroundContacts() > 0) {
			Vector2 impulse = new Vector2(b2D.getBody().getLinearVelocity().x, jComp.getMaxImpulse());
			b2D.getBody().setLinearVelocity(impulse);
		}
	}

}
