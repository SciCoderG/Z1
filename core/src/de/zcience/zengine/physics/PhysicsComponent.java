package de.zcience.zengine.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PhysicsComponent implements Component, Poolable
{

    private Vector2 position, linearVelocity;

    private float angle, angularVelocity;

    public PhysicsComponent()
    {
        this.position = new Vector2();
        this.linearVelocity = new Vector2();
    }

    @Override
    public void reset()
    {
        angle = angularVelocity = 0.0f;
        position = linearVelocity = null;
    }

    /**
     * Update the two most important values for rendering
     * 
     * @param newPosition
     *            position of the body
     * @param newAngle
     *            angle of the body
     */
    public void update(Vector2 newPosition, float newAngle)
    {
        this.position = newPosition;
        this.angle = newAngle;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getLinearVelocity()
    {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector2 linearVelocity)
    {
        this.linearVelocity = linearVelocity;
    }

    public float getAngle()
    {
        return angle;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
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
