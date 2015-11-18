package de.zcience.zengine.render.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 * Overriding the OrthographicCamera. Needed to be able to use any Orthographic
 * Camera you would like in the CameraController
 * 
 * @author David_000
 *
 */
public class ZOrthographicCamera extends OrthographicCamera {

	public ZOrthographicCamera() {
		super();
	}

	public ZOrthographicCamera(float viewportWidth, float viewportHeight) {
		super(viewportWidth, viewportHeight);
	}

	public void update(Vector2 target, float deltaTime) {
		this.position.set(target.x, target.y, 0.0f);
	}
}
