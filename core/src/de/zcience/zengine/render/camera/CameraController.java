package de.zcience.zengine.render.camera;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.utils.ZComponentMapper;

public class CameraController {
	private Entity target;

	private Viewport viewport;

	private Camera camera;

	private TiledMap map;

	public CameraController(Viewport viewport) {
		this.viewport = viewport;
		this.camera = viewport.getCamera();
	}

	public void update(float deltaTime) {
		if (target != null) {
			PositionComponent pComp = ZComponentMapper.position.get(target);

			if (pComp != null) {
				// bottom left
				if (camera instanceof LimitedSmoothOrthographicCamera) {
					LimitedSmoothOrthographicCamera limOrthCam = (LimitedSmoothOrthographicCamera) camera;
					limOrthCam.integrate(pComp.getPosition(), deltaTime);
				} else {
					camera.position.set(pComp.getPosition().x, pComp.getPosition().y, 0.0f);
				}

				camera.update();

			}
		}
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * Use a GameScreenViewport to automatically use a
	 * LimitedSmoothOrthographicCamera!
	 * 
	 * @param viewport
	 */
	public void setViewport(Viewport viewport) {
		this.camera = viewport.getCamera();
		this.viewport = viewport;
	}

	public Camera getCamera() {
		return camera;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
		if (camera instanceof LimitedSmoothOrthographicCamera) {
			LimitedSmoothOrthographicCamera limOrthCam = (LimitedSmoothOrthographicCamera) camera;
			limOrthCam.setMap(map);
		}
	}

}
