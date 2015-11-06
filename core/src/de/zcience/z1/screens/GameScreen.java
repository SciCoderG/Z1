package de.zcience.z1.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
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


	private OrthographicCamera camera;

	private LevelLoader levelLoader;
	private Box2DDebugRenderer debugRenderer;

	public GameScreen(ZApplication app) {
		this.app = app;
		this.engine = new PooledEngine(); // Important - this will only add 100
											// Entities and 100 Components of
											// each
											// type to each pool!

		// Set inputprocessor
		this.gInputProcessor = new GameScreenInputProcessor(app, this);

		// Create Viewport
		this.camera = new OrthographicCamera();
		this.viewport = new ScreenViewport(camera);
		this.viewport.setUnitsPerPixel(Constants.B2D_UNITS_PER_PIXEL);

		// Create Systems
		PhysicsSystem pSystem = new PhysicsSystem();
		engine.addSystem(pSystem);

		// Init levelLoader
		// This is important and stupid, but I don't know yet how to avoid it:
		// We have to set the Box2D world for
		// the LevelLoader, after PhysicsSystem was created, but we can't do it
		// after the RenderingSystem is initialised, otherwise the
		// tiledMapRenderer will throw an error...
		// TODO: make this less stupid

		this.levelLoader = new LevelLoader(app.getAssetManager(), pSystem.getWorld());

		RenderingSystem rSystem = new RenderingSystem(app, levelLoader, this.viewport);
		engine.addSystem(rSystem);

		// TODO: get rid of debug related stuff
		levelLoader.loadMap("maps/test1.tmx");
		
		debugRenderer = new Box2DDebugRenderer();
		
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(5, 5);

		// Create our body in the world using our body definition
		Body body = pSystem.getWorld().createBody(bodyDef);
		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(5f);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);

		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		circle.dispose();
		Array<Body> bodies = new Array<Body>();
		pSystem.getWorld().getBodies(bodies);
		for( Body b : bodies)
		{
			Gdx.app.log("Body: ", "X: " + b.getPosition().x + " Y: " + b.getPosition().y + " Active: "  + b.isActive());
		}
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gInputProcessor);
	}

	@Override
	public void render(float delta) {

		engine.update(delta);
		debugRenderer.render(engine.getSystem(PhysicsSystem.class).getWorld(), camera.combined);
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
