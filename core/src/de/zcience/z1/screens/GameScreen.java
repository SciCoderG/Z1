package de.zcience.z1.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.ZApplication;
import de.zcience.z1.gameplay.input.InputSystem;
import de.zcience.z1.gameplay.level.LevelLoader;
import de.zcience.z1.gameplay.movement.JumpingSystem;
import de.zcience.z1.gameplay.movement.MovementSystem;
import de.zcience.z1.gameplay.utils.Constants;
import de.zcience.z1.gameplay.utils.EntityCreator;
import de.zcience.zengine.physics.systems.PhysicsSystem;
import de.zcience.zengine.render.CameraController;
import de.zcience.zengine.render.RenderingSystem;

public class GameScreen implements Screen {

	private ZApplication app;

	private PooledEngine engine;

	private GameScreenInputProcessor gInputProcessor;

	private LevelLoader levelLoader;

	private CameraController cameraController;

	private Box2DDebugRenderer debugRenderer;

	/**
	 * TODO: Remove all the unorganized crap into nice, readable and
	 * understandable methods :)
	 * 
	 * @param app
	 */
	public GameScreen(ZApplication app) {
		this.app = app;
		this.engine = new PooledEngine(); // Important - this will only add 100
											// Entities and 100 Components of
											// each
											// type to each pool!

		// Set inputprocessor
		this.gInputProcessor = new GameScreenInputProcessor(app, this);

		// Create Viewport
		ScreenViewport viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(Constants.B2D_UNITS_PER_PIXEL);

		// Create Systems
		InputSystem inputSystem = new InputSystem();
		gInputProcessor.addKeyboardListener(inputSystem);
		engine.addSystem(inputSystem);

		PhysicsSystem pSystem = new PhysicsSystem();
		engine.addSystem(pSystem);

		engine.addSystem(new MovementSystem());

		// Init levelLoader
		// This is important and stupid, but I don't know yet how to avoid it:
		// We have to set the Box2D world for
		// the LevelLoader, after PhysicsSystem was created, but we can't do it
		// after the RenderingSystem is initialised, otherwise the
		// tiledMapRenderer will throw an error...
		// TODO: make this less stupid

		this.levelLoader = new LevelLoader(app.getAssetManager(), engine.getSystem(PhysicsSystem.class).getWorld());

		EntityCreator.setEngine(engine);
		float f = 10.0f;
		Entity player = EntityCreator.createPlayer(f, f);
		EntityCreator.createPlayer(++f, ++f);

		cameraController = new CameraController(viewport);
		cameraController.setTarget(player);
		RenderingSystem rSystem = new RenderingSystem(app, levelLoader, cameraController);
		engine.addSystem(rSystem);

		engine.addSystem(new JumpingSystem());
		// TODO: get rid of debug related stuff
		levelLoader.loadMap("maps/test1.tmx");

		debugRenderer = new Box2DDebugRenderer();

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gInputProcessor);
	}

	@Override
	public void render(float delta) {
		engine.update(delta);
		debugRenderer.render(engine.getSystem(PhysicsSystem.class).getWorld(), cameraController.getCamera().combined);
	}

	@Override
	public void resize(int width, int height) {
		cameraController.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

	public CameraController getCameraController() {
		return cameraController;
	}

	public void setCameraController(CameraController cameraController) {
		this.cameraController = cameraController;
	}

}
