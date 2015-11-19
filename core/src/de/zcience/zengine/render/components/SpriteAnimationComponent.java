package de.zcience.zengine.render.components;

import java.util.HashMap;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

import de.zcience.zengine.render.utils.ZAnimation;

/**
 * Sprite Animation Component for storing a spritesheet animation.
 * 
 * @author David_000
 *
 */
public class SpriteAnimationComponent implements Component, Poolable {

	private HashMap<String, ZAnimation> animations;
	private String currentAnimationState;

	public SpriteAnimationComponent() {
		animations = new HashMap<String, ZAnimation>();
	}

	/**
	 * Returns the value to which the specified key is mapped, or null if
	 * animations contains no mapping for the key
	 * 
	 * @param key
	 * @return
	 */
	public ZAnimation getAnimation(String key) {
		return animations.get(key);
	}

	/**
	 * 
	 * @param key
	 * @param animation
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key. (A null return can also indicate that the map
	 *         previously associated null with key.)
	 */
	public ZAnimation setAnimation(String key, ZAnimation animation) {
		currentAnimationState = key;
		return animations.put(key, animation);
	}

	public ZAnimation getCurrentAnimation() {
		return animations.get(currentAnimationState);
	}

	public String getCurrentAnimationState() {
		return currentAnimationState;
	}

	public void setCurrentAnimationState(String currentAnimationState) {
		this.currentAnimationState = currentAnimationState;
	}

	@Override
	public void reset() {
		animations.clear();
	}

}
