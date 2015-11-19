package de.zcience.z1.screens.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.zengine.render.camera.ZOrthographicCamera;

/**
 * Utility class to automatically add the correct Camera to the ScreenViewport
 * 
 * @author David_000
 *
 */
public class GameScreenViewport extends ScreenViewport {

	public GameScreenViewport() {
		super(new ZOrthographicCamera());
	}

	public GameScreenViewport(Camera camera) {
		super(camera);
	}
}
