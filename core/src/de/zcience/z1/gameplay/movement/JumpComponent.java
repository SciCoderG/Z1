package de.zcience.z1.gameplay.movement;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class JumpComponent implements Component, Poolable {

	private float maxImpulse = 1.0f;
	private int groundContacts = 0;

	@Override
	public void reset() {
		maxImpulse = 1.0f;
		groundContacts = 0;
	}

	public void addGroundContact() {
		this.groundContacts++;
	}

	public void subGroundContact() {
		this.groundContacts--;
	}

	public int getGroundContacts() {
		return groundContacts;
	}

	public float getMaxImpulse() {
		return maxImpulse;
	}

	public void setMaxImpulse(float maxImpulse) {
		this.maxImpulse = maxImpulse;
	}

}
