package de.zcience.zengine.physics.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PositionComponent implements Component, Poolable {

	private Vector2 position = new Vector2();
	private float angle = 0.0f;

	@Override
	public void reset() {
		position.setZero();
		this.angle = 0.0f;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

}
