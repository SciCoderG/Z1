package de.zcience.zengine.render.camera;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.utils.ZComponentMapper;

public class CameraController {
	private Entity target;

	private Viewport viewport;

	private ZOrthographicCamera camera;

	private TiledMap map;

	public CameraController(Viewport viewport) {
		this.viewport = viewport;
		try {
			this.camera = (ZOrthographicCamera) viewport.getCamera();
		} catch (ClassCastException e) {
			Gdx.app.error("CameraController", "Can't cast given camera to ZOrthographicCamera." + e.getMessage());
		}
	}

	public void update(float deltaTime) {
		if (target != null) {
			PositionComponent pComp = ZComponentMapper.position.get(target);

			if (pComp != null) {
				camera.update(pComp.getPosition(), deltaTime);
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
		this.camera = (ZOrthographicCamera) viewport.getCamera();
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
