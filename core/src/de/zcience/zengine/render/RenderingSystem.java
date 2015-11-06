package de.zcience.zengine.render;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.zcience.ZApplication;
import de.zcience.zengine.level.LevelLoader;
import de.zcience.zengine.utils.Constants;

public class RenderingSystem extends IteratingSystem
{

    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private LevelLoader levelLoader;

    private Stage stage;

    private ZApplication app;

    private CameraController camController;

    @SuppressWarnings("unchecked")
    public RenderingSystem(ZApplication app, LevelLoader levelLoader, CameraController camController)
    {
        super(Family.all().get());
        this.app = app;
        this.levelLoader = levelLoader;
        this.camController = camController;

        // resizing the stage viewport will happen automatically, because
        // gamescreen and renderingsystem is using the same viewport
        this.stage = new Stage(camController.getViewport(), app.getBatch());

        Skin skin = app.getAssetManager().get("ui/uiskin.json");

    }

    @Override
    public void addedToEngine(Engine engine)
    {
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.levelLoader.getCurrentMap(), Constants.B2D_UNITS_PER_PIXEL, this.app.getBatch());
        super.addedToEngine(engine);
    }

    @Override
    public void update(float deltaTime)
    {
        camController.update(deltaTime);
        tiledMapRenderer.setView(camController.getCamera());
        tiledMapRenderer.render(); // TODO: Get the layer order right!
        super.update(deltaTime);
        // stage.draw();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        // TODO Auto-generated method stub

    }

    public OrthogonalTiledMapRenderer getTiledMapRenderer()
    {
        return tiledMapRenderer;
    }

    public void setTiledMapRenderer(OrthogonalTiledMapRenderer tiledMapRenderer)
    {
        this.tiledMapRenderer = tiledMapRenderer;
    }

    public OrthographicCamera getCamera()
    {
        return camController.getCamera();
    }

    /**
     * Needed, if you want to render the new map instead of the old
     * 
     * @param map
     */
    public void setMap(TiledMap map)
    {
        tiledMapRenderer.setMap(map);
    }

}
