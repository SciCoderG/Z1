package de.zcience.zengine.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.utils.ZComponentMapper;

public class CameraController
{
    private Entity target;

    private Viewport viewport;

    private OrthographicCamera camera;

    private TiledMap map;

    private int mapWidth, mapHeight;

    public CameraController(Viewport viewport)
    {
        this.viewport = viewport;
        this.camera = (OrthographicCamera) viewport.getCamera();
    }

    public void update(float deltaTime)
    {
        if (target != null)
        {
            PositionComponent pComp = ZComponentMapper.position.get(target);
            if (pComp != null)
            {
                int top = viewport.getScreenY() + viewport.getScreenHeight();
                int right = viewport.getScreenX() + viewport.getScreenWidth();
                // bottom left
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

    public TiledMap getMap()
    {
        return map;
    }

    public void setMap(TiledMap map)
    {
        this.map = map;

        this.mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        this.mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
    }

}
