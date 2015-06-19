package de.zcience.Z1.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.zcience.Z1.game.components.PositionComponent;
import de.zcience.Z1.game.util.CompMappers;
import de.zcience.Z1.physics.PhysicsBodyComponent;

public class UpdatePositionSystem extends IteratingSystem{

    public UpdatePositionSystem(int priority) {
        super(Family.all(PositionComponent.class, PhysicsBodyComponent.class).get(), priority);
       
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = CompMappers.position.get(entity);
        PhysicsBodyComponent physicsBody = CompMappers.physicsBody.get(entity);
        
        position.x = physicsBody.getPosition().x;
        position.y = physicsBody.getPosition().y;
    }
}
