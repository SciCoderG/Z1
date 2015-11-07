package de.zcience.zengine.physics.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class VelocityComponent implements Component, Poolable {

	private Vector2 linearVelocity = new Vector2();
	private float angularVelocity = 0.0f;

	@Override
	public void reset() {
		linearVelocity.setZero();
		angularVelocity = 0.0f;
	}
	
	 public Vector2 getLinearVelocity()
	    {
	        return linearVelocity;
	    }

	    public void setLinearVelocity(Vector2 linearVelocity)
	    {
	        this.linearVelocity = linearVelocity;
	    }
	    
	    public float getAngularVelocity()
	    {
	        return angularVelocity;
	    }

	    public void setAngularVelocity(float angularVelocity)
	    {
	        this.angularVelocity = angularVelocity;
	    }

}
