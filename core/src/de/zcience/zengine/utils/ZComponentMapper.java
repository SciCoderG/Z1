package de.zcience.zengine.utils;

import com.badlogic.ashley.core.ComponentMapper;

import de.zcience.zengine.physics.Box2DComponent;
import de.zcience.zengine.physics.PhysicsComponent;

/**
 * Every Component should be registered here, to be able to easily access a
 * Component of a given entity in O(1).
 * 
 * @author David_000
 *
 */
public class ZComponentMapper
{
    public static final ComponentMapper<Box2DComponent> box2D = ComponentMapper.getFor(Box2DComponent.class);

    public static final ComponentMapper<PhysicsComponent> physics = ComponentMapper.getFor(PhysicsComponent.class);

}
