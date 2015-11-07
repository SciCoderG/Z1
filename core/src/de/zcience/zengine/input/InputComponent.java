package de.zcience.zengine.input;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class InputComponent implements Component, Poolable {
	private boolean isActive = true;
	private float maxSpeed = 1.0f;
	private float maxAccel = 1.0f;
	private Vector2 direction = new Vector2();

	public void init(boolean isActive, float maxSpeed, float maxAccel) {
		this.isActive = isActive;
		this.maxSpeed = maxSpeed;
		this.maxAccel = maxAccel;
	}

	@Override
	public void reset() {
		isActive = true;
		maxSpeed = 1.0f;
		direction.setZero();
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float speed) {
		this.maxSpeed = speed;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public float getMaxAccel() {
		return maxAccel;
	}

	public void setMaxAccel(float maxAccel) {
		this.maxAccel = maxAccel;
	}

}
