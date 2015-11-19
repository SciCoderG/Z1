package de.zcience.zengine.render.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.zcience.z1.gameplay.utils.ZComponentMapper;
import de.zcience.zengine.render.components.SpriteAnimationComponent;
import de.zcience.zengine.render.components.TextureComponent;
import de.zcience.zengine.render.utils.ZAnimation;

public class AnimationUpdateSystem extends IteratingSystem {

	@SuppressWarnings("unchecked")
	public AnimationUpdateSystem() {
		super(Family.all(SpriteAnimationComponent.class, TextureComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		SpriteAnimationComponent spriteAnim = ZComponentMapper.spriteAnimation.get(entity);
		TextureComponent tex = ZComponentMapper.texture.get(entity);

		spriteAnim.getCurrentAnimation().updateAnimation(deltaTime);
		ZAnimation animation = spriteAnim.getCurrentAnimation();
		tex.setTexture(animation.getKeyFrame());
	}

}
