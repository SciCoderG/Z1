package de.zcience.zengine.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.zcience.zengine.physics.PhysicsComponent;
import de.zcience.zengine.utils.ZComponentMapper;

public class CameraController
{
    private Entity target;

    private Viewport viewport;

    private OrthographicCamera camera;

    public CameraController(Viewport viewport)
    {
        this.viewport = viewport;
        this.camera = (OrthographicCamera) viewport.getCamera();
    }
    
    public void update(float deltaTime)
    {
        if(target != null)
        {
            PhysicsComponent pComp = ZComponentMapper.physics.get(target);
            if(pComp != null)
            {
                camera.position.set(pComp.getPosition(), 0.0f);
                camera.update();
            }
        }
    }

    public Entity getTarget()
    {
        return target;
    }

    public void setTarget(Entity target)
    {
        this.target = target;
    }

    public Viewport getViewport()
    {
        return viewport;
    }

    public void setViewport(Viewport viewport)
    {
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.viewport = viewport;
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }

}
