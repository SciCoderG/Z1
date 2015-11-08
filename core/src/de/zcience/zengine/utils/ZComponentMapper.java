package de.zcience.zengine.utils;

import com.badlogic.ashley.core.ComponentMapper;

import de.zcience.z1.gameplay.input.InputComponent;
import de.zcience.z1.gameplay.movement.JumpComponent;
import de.zcience.zengine.physics.components.Box2DComponent;
import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.physics.components.VelocityComponent;

/**
 * Every Component should be registered here, to be able to easily access a
 * Component of a given entity in O(1).
 * 
 * @author David_000
 *
 */
public class ZComponentMapper {
	public static final ComponentMapper<Box2DComponent> box2D = ComponentMapper.getFor(Box2DComponent.class);

	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);

	public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);

	public static final ComponentMapper<InputComponent> input = ComponentMapper.getFor(InputComponent.class);

	public static final ComponentMapper<JumpComponent> jumping = ComponentMapper.getFor(JumpComponent.class);

}
