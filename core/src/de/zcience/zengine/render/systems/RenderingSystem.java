package de.zcience.zengine.render.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.zcience.ZApplication;
import de.zcience.z1.gameplay.utils.Constants;
import de.zcience.zengine.render.camera.CameraController;

public class RenderingSystem extends IteratingSystem {

	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private ZApplication app;

	private CameraController camController;

	@SuppressWarnings("unchecked")
	public RenderingSystem(ZApplication app, CameraController camController) {
		super(Family.all().get());
		this.app = app;
		this.camController = camController;

	}

	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
	}

	@Override
	public void update(float deltaTime) {
		camController.update(deltaTime);
		if (null != tiledMapRenderer) {
			if (camController.getCamera() instanceof OrthographicCamera) {
				OrthographicCamera oCam = (OrthographicCamera) camController.getCamera();

				tiledMapRenderer.setView(oCam);
				tiledMapRenderer.render(); // TODO: Get the layer order right!
			} else {
				Gdx.app.error("RenderingSystem",
						"TiledMap can't be rendered, viewport does not provide an OrthographicCamera!");
			}
		}
		super.update(deltaTime);
		// stage.draw();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub

	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	public void setTiledMapRenderer(OrthogonalTiledMapRenderer tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}

	public Camera getCamera() {
		return camController.getCamera();
	}

	/**
	 * Needed, if you want to render the new map instead of the old
	 * 
	 * @param map
	 */
	public void setMap(TiledMap map) {
		this.camController.setMap(map);
		if (null == tiledMapRenderer) {
			tiledMapRenderer = new OrthogonalTiledMapRenderer(map, Constants.B2D_UNITS_PER_PIXEL, app.getBatch());
		} else {
			tiledMapRenderer.setMap(map);
		}

	}

}
