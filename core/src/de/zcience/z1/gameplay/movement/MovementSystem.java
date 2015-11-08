package de.zcience.z1.gameplay.movement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import de.zcience.z1.gameplay.input.InputComponent;
import de.zcience.zengine.physics.components.Box2DComponent;
import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.utils.ZComponentMapper;

public class MovementSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Family.all(InputComponent.class).one(Box2DComponent.class, PositionComponent.class).get());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		InputComponent iComp = ZComponentMapper.input.get(entity);
		Box2DComponent box2DComp = ZComponentMapper.box2D.get(entity);

		if (null != box2DComp) {
//			if (box2DComp.getBody().getLinearVelocity().x < iComp.getMaxSpeed()
//					&& box2DComp.getBody().getLinearVelocity().x > -iComp.getMaxSpeed()) {

				float linearVelX = iComp.getDirection().x * iComp.getMaxSpeed();
				float linearVelY = box2DComp.getBody().getLinearVelocity().y;
				box2DComp.getBody().setLinearVelocity(linearVelX, linearVelY);
//				float xImpulse = iComp.getDirection().x * iComp.getMaxAccel() * deltaTime;
//				Vector2 impulse = new Vector2(xImpulse, 0.0f);
//				box2DComp.getBody().applyLinearImpulse(impulse, box2DComp.getBody().getWorldCenter(), true);
//			}
		} else {
			PositionComponent posComp = ZComponentMapper.position.get(entity);
			Vector2 vel = new Vector2(iComp.getDirection());
			vel.scl(iComp.getMaxSpeed());
			posComp.getPosition().add(vel);
		}
	}

}
