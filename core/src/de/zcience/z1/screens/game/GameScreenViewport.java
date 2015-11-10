package de.zcience.z1.screens.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.z1.gameplay.utils.Constants;
import de.zcience.zengine.render.camera.LimitedSmoothOrthographicCamera;

/**
 * Utility class to automatically add the correct Camera to the ScreenViewport
 * 
 * @author David_000
 *
 */
public class GameScreenViewport extends ScreenViewport {

	public GameScreenViewport() {
		super(new LimitedSmoothOrthographicCamera(Constants.CAMERA_SPRINGCONSTANT, Constants.CAMERA_MASS));
	}

	public GameScreenViewport(Camera camera) {
		super(camera);
	}
}
