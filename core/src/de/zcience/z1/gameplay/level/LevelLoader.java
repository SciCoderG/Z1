package de.zcience.z1.gameplay.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.zcience.z1.gameplay.utils.Constants;
import de.zcience.zengine.physics.utils.MapBodyManager;

public class LevelLoader {

	private TiledMap currentMap;
	private World currentWorld;
	private AssetManager assetManager;
	private MapBodyManager mapBodyManager;

	public LevelLoader(AssetManager assetManager, World currentWorld) {
		this.assetManager = assetManager;
		this.currentWorld = currentWorld;
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("maps/test1.tmx", TiledMap.class);
		assetManager.load("packedImages/Z1.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		
		mapBodyManager = new MapBodyManager(currentWorld, Constants.B2D_UNITS_PER_PIXEL,
				Gdx.files.internal("maps/materials.json"), Gdx.app.getLogLevel());
	}

	/**
	 * Load Map into Assetmanager and set it as current map
	 * 
	 * @param string
	 * @param world
	 *            Box2D world for physics
	 */
	public void loadMap(String string) {

		try {
			currentMap = assetManager.get(string);
		} catch (GdxRuntimeException e) {
			try {
				assetManager.load(string, TiledMap.class);
				currentMap = assetManager.get(string);
			} catch (GdxRuntimeException e2) {
				Gdx.app.log("LevelLoader", "Couldn't load the given map, loading default map.");
				currentMap = assetManager.get("maps/test1.tmx");
			}
		}
		if (mapBodyManager == null) {
			mapBodyManager = new MapBodyManager(currentWorld, Constants.B2D_UNITS_PER_PIXEL,
					Gdx.files.internal("maps/materials.json"), Gdx.app.getLogLevel());
		}
//		synchronized (this) {
//			while (mapBodyManager == null)
//				try {
//					this.wait(1L);
//				} catch (InterruptedException e) {
//					break;
//				}
//		}
		mapBodyManager.createPhysics(currentMap);

	}

	public TiledMap getCurrentMap() {
		if (currentMap == null) {
			loadMap("maps/test1.tmx");
		}
		return currentMap;
	}

	public World getCurrentWorld() {
		return currentWorld;
	}

	public void setCurrentWorld(World currentWorld) {
		this.currentWorld = currentWorld;
	}

}
