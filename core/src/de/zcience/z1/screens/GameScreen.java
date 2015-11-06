package de.zcience.z1.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.ZApplication;
import de.zcience.zengine.level.LevelLoader;
import de.zcience.zengine.physics.PhysicsSystem;
import de.zcience.zengine.render.RenderingSystem;
import de.zcience.zengine.utils.Constants;

public class GameScreen implements Screen {

	private ZApplication app;

	private PooledEngine engine;

	private GameScreenInputProcessor gInputProcessor;

	private ScreenViewport viewport;

	public Box2DDebugRenderer debugRenderer;

	private OrthographicCamera camera;

	private LevelLoader levelLoader;

	public GameScreen(ZApplication app) {
		this.app = app;
		this.engine = new PooledEngine(); // Important - this will only add 100
											// Entities and 100 Components of
											// each
											// type to each pool!

		this.levelLoader = new LevelLoader(app.getAssetManager());
		// Set inputprocessor
		this.gInputProcessor = new GameScreenInputProcessor(app);

		// Create Viewport
		this.camera = new OrthographicCamera();
		this.viewport = new ScreenViewport(camera);
		viewport.setUnitsPerPixel(Constants.B2D_UNITS_PER_PIXEL);

		debugRenderer = new Box2DDebugRenderer();

		// Load the first map
		levelLoader.loadMap("maps/test1.tmx");

		// Create Systems
		PhysicsSystem pSystem = new PhysicsSystem();
		engine.addSystem(pSystem);

		RenderingSystem rSystem = new RenderingSystem(levelLoader, camera);
		engine.addSystem(rSystem);

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gInputProcessor);
	}

	@Override
	public void render(float delta) {
		debugRenderer.render(engine.getSystem(PhysicsSystem.class).getWorld(), viewport.getCamera().combined);
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		app.getAssetManager().finishLoading();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {

	}

	public LevelLoader getLevelLoader() {
		return levelLoader;
	}

	public void setLevelLoader(LevelLoader levelLoader) {
		this.levelLoader = levelLoader;
	}

}
