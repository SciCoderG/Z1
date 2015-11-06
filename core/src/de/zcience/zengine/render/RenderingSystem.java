package de.zcience.zengine.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.zcience.zengine.level.LevelLoader;
import de.zcience.zengine.utils.Constants;

public class RenderingSystem extends IteratingSystem {

	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private SpriteBatch spriteBatch;
	private LevelLoader levelLoader;
	private OrthographicCamera camera;

	@SuppressWarnings("unchecked")
	public RenderingSystem(LevelLoader levelLoader, OrthographicCamera camera) {
		super(Family.all().get());
		this.spriteBatch = new SpriteBatch();
		this.camera = camera;
		this.levelLoader = levelLoader;
		tiledMapRenderer = new OrthogonalTiledMapRenderer(levelLoader.getCurrentMap(), Constants.B2D_UNITS_PER_PIXEL,
				this.spriteBatch);
	}

	@Override
	public void update(float deltaTime) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render(); // TODO: Get the layer order right!
		super.update(deltaTime);
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

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	/**
	 * Needed, if you want to render the new map instead of the old
	 * 
	 * @param map
	 */
	public void setMap(TiledMap map) {
		tiledMapRenderer.setMap(map);
	}

}
