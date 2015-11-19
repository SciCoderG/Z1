package de.zcience.zengine.render.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.zcience.ZApplication;
import de.zcience.z1.gameplay.utils.Constants;
import de.zcience.z1.gameplay.utils.ZComponentMapper;
import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.render.camera.CameraController;
import de.zcience.zengine.render.camera.ZOrthographicCamera;
import de.zcience.zengine.render.components.TextureComponent;

/**
 * Rendering the tiledmap and textures, updating the camera
 * 
 * @author David_000
 *
 */
public class RenderingSystem extends IteratingSystem {

	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private ZApplication app;

	private CameraController camController;

	@SuppressWarnings("unchecked")
	public RenderingSystem(ZApplication app, CameraController camController) {
		super(Family.all(TextureComponent.class, PositionComponent.class).get());
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
		ZOrthographicCamera oCam = camController.getCamera();

		if (null != tiledMapRenderer) {

			tiledMapRenderer.setView(oCam);
			tiledMapRenderer.render(); // TODO: Get the layer order right!
		}
		app.getBatch().setProjectionMatrix(oCam.combined);
		app.getBatch().begin();
		super.update(deltaTime);
		app.getBatch().end();
		// stage.draw();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TextureComponent tex = ZComponentMapper.texture.get(entity);
		PositionComponent pos = ZComponentMapper.position.get(entity);

		float texPosX = pos.getPosition().x - tex.getWidth() * 0.5f;
		float texPosY = pos.getPosition().y - tex.getHeight() * 0.5f;
		app.getBatch().draw(tex.getTexture(), texPosX, texPosY, tex.getWidth(), tex.getHeight());
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
