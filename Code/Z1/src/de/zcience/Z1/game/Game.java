package de.zcience.Z1.game;

import org.lwjgl.opengl.GL11;

import box2dLight.ConeLight;
import box2dLight.RayHandler;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import de.zcience.Z1.audio.MusicManager;
import de.zcience.Z1.audio.SoundManager;
import de.zcience.Z1.game.contactlistener.MyContactListener;
import de.zcience.Z1.game.system.CameraSystem;
import de.zcience.Z1.game.system.DeathSystem;
import de.zcience.Z1.game.system.JumpSystem;
import de.zcience.Z1.game.system.LightSystem;
import de.zcience.Z1.game.system.MovementSystem;
import de.zcience.Z1.game.system.ShootingSystem;
import de.zcience.Z1.game.system.TextureRenderer;
import de.zcience.Z1.game.system.UpdatePositionSystem;
import de.zcience.Z1.game.util.AssetLoader;
import de.zcience.Z1.game.util.DrawUtil;
import de.zcience.Z1.game.util.GameConstants;
import de.zcience.Z1.game.util.MapLoader;
import de.zcience.Z1.input.InputManager;
import de.zcience.Z1.physics.PhysicsSystem;
import de.zcience.Z1.profiling.ProfilerGlobal;
import de.zcience.Z1.tiled.TiledMapRenderingSystem;

public class Game implements ApplicationListener {

	private PooledEngine engine;

	/**
	 * Testing!
	 */
	private Box2DDebugRenderer box2DDebugRenderer;
	private BitmapFont font;
	
	private static boolean doDebugRendering = true;

	


	// add PhysicSystem
	private final PhysicsSystem physicsSystem = new PhysicsSystem(
			GameConstants.BOX2D_VELOCITY_ITERATIONS,
			GameConstants.BOX2D_POSITIONS_ITERATIONS,
			GameConstants.BOX2D_SCALE, GameConstants.PHYSICS_PRIORITY);

	private final TextureRenderer textureRenderer = new TextureRenderer(GameConstants.PHYSICS_PRIORITY-5);

	private final JumpSystem jumpSystem = new JumpSystem(GameConstants.PHYSICS_PRIORITY + 1);

	/** Manager */
	public InputManager inputManager;
	public static SoundManager soundManager;
	public static MusicManager musicManager;

	ConeLight light2;

	@Override
	public void create() {
		// creating the Ashley engine
		engine = new PooledEngine();
		EntityCreator.engine = engine;

		// initialise Box2D
		Box2D.init();

		/* Manager */
		inputManager = new InputManager();
		soundManager = new SoundManager();
		musicManager = new MusicManager();

		/* Systems */
		this.addSystems();

		/* ContactListener */
		EntityCreator.physicsSystem.getWorld().setContactListener(
				new MyContactListener());

		
		/* load all Assets */
		AssetLoader.loadAll();

		/* Create debugRenderer and tiledMapRenderer*/
		// TODO - move this to a class it belongs to. into the rendering!
		box2DDebugRenderer = new Box2DDebugRenderer();

		


		
		font = new BitmapFont();

	}

	public void addSystems() {
		/* Rendering related stuff */
		// CameraSystem
		CameraSystem camSystem = new CameraSystem(GameConstants.CAMERA_PRIORITY);
		EntityCreator.camSystem = camSystem;
		engine.addSystem(camSystem);
		// texture rendering
		engine.addSystem(textureRenderer);
		// tiledmaprendering
		engine.addSystem(new TiledMapRenderingSystem());

		/* Physiks related stuff */
		EntityCreator.physicsSystem = physicsSystem;
		engine.addSystem(physicsSystem);
		physicsSystem.setGravity(new Vector2(0, -30)); 
		// add MovementSystem
		engine.addSystem(new MovementSystem(GameConstants.PHYSICS_PRIORITY + 1));
		// add UpdatePositionSystem
		engine.addSystem(new UpdatePositionSystem(
				GameConstants.PHYSICS_PRIORITY + 2));
		// ShootingSystem
		engine.addSystem(new ShootingSystem(GameConstants.CAMERA_PRIORITY + 1));
		engine.addSystem(jumpSystem);
		engine.addSystem(new LightSystem(GameConstants.PHYSICS_PRIORITY +1));
		
		
		
		/* GamePlay */
		engine.addSystem(new DeathSystem(GameConstants.PHYSICS_PRIORITY + 4));
	}

	@Override
	public void resize(int width, int height) {
		//EntityCreator.camSystem.resizeCameraViewport(width, height);
	}

	@Override
	public void render() {
		
		if(!AssetLoader.getAssetManager().update()){
			System.out.println("Noch nicht alles geladen!");
		}

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

		
//		ProfilerGlobal.startTime();
		engine.update(Gdx.graphics.getDeltaTime());
//		ProfilerGlobal.endTime();
//		ProfilerGlobal.outMax("engine-");
		
		
		if(doDebugRendering){
//			ProfilerGlobal.startTime();
			box2DDebugRenderer.render(EntityCreator.physicsSystem.getWorld(),
					EntityCreator.camSystem.getCamera().combined);
//			ProfilerGlobal.endTime();
//			ProfilerGlobal.outMax("box2dDebug-");
		}
		

		// THIS DOES NOT BELONG HERE. PANIC!!!!! (#notimeleft)
		DrawUtil.batch.begin();
		if (EntityCreator.enemyCounter > 0) {
			font.draw(DrawUtil.batch, "Demon-girls without love: "
					+ EntityCreator.enemyCounter,
					10,
					20);
		} else {
			font.draw(
					DrawUtil.batch,
					"Hey, you really did it! THANKS FOR PLAYING (this really alpha version of a game -.-)",
					10,
					20);
		}
		font.draw(
				DrawUtil.batch,
				"FPS: " + Gdx.graphics.getFramesPerSecond(),
				10,
				40);
		DrawUtil.batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	public static boolean isDoDebugRendering() {
		return doDebugRendering;
	}

	public static void setDoDebugRendering(boolean set) {
		doDebugRendering = set;
	}
	

}
