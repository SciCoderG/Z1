package de.zcience.zengine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class LevelLoader {

	private TiledMap currentMap;
	private AssetManager assetManager;

	public LevelLoader(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("maps/test1.tmx", TiledMap.class);
		assetManager.finishLoading();
	}

	/**
	 * Load Map into Assetmanager and set it as current map
	 * 
	 * @param string
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
	}

	public TiledMap getCurrentMap() {
		if (currentMap == null) {
			loadMap("maps/test1.tmx");
		}
		return currentMap;
	}

}
