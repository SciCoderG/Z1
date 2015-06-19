package de.zcience.Z1.game.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import de.zcience.Z1.game.components.PlayerComponent;
import de.zcience.Z1.game.components.PositionComponent;
import de.zcience.Z1.game.util.CompMappers;
import de.zcience.Z1.game.util.GameConstants;

/**
 * CameraSystem. Follows the entity with the most recently added PlayerComponent
 * 
 * @author David
 *
 */
public class CameraSystem extends EntitySystem implements EntityListener {

	/**
	 * target that should be followed by the CameraSystem
	 */
	private Entity target;

	private OrthographicCamera camera;
	
	public Vector2 viewpoint = new Vector2();

	public CameraSystem(int priority) {
		super(priority);
		camera = new OrthographicCamera();
		resizeCameraViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}

	@Override
	public void update(float deltaTime) {
		if (target != null) {
			PositionComponent posComp = CompMappers.position.get(target);
			if (posComp != null) {
//				float x;
//				float y;
//				
//				int mapWidth = MapLoader.mapWidth * GameConstants.getTileSizeX();
//				int mapHeight = MapLoader.mapHeight * GameConstants.getTileSizeY();
//				
//				float cameraWidthHalf = (float) (EntityCreator.camSystem.getCamera().viewportWidth * 0.5 * GameConstants.BOX2D_SCALE);
//				float cameraHeightHalf = (float) (EntityCreator.camSystem.getCamera().viewportHeight * 0.5 * GameConstants.BOX2D_SCALE);
//				
//				if(posComp.x < cameraWidthHalf) {
//					x = cameraWidthHalf;
//				}
//				else if(posComp.x > mapWidth - cameraWidthHalf) {
//					x = mapWidth - cameraWidthHalf;
//					
//				}
//				else {
//					x = posComp.x;
//				}
//				if(posComp.y < cameraHeightHalf) {
//					y = cameraHeightHalf;
//				}
//				else if(posComp.y > mapHeight - cameraHeightHalf) {
//					y = mapHeight - cameraHeightHalf;
//				}
//				else {
//					y = posComp.y;
//				}
//				this.viewpoint.x = x;
//				this.viewpoint.y = y;
//				
				
				setCameraPosition(posComp.x, posComp.y);
				
			}
		}
		camera.update();
	}

	@Override
	public void addedToEngine(Engine engine) {
		engine.addEntityListener(
				Family.all(PlayerComponent.class, PositionComponent.class)
						.get(), this);
	}

	@Override
	public void entityAdded(Entity entity) {
		if (target == null) {
			target = entity;
			camera.update(true);
		}
	}

	@Override
	public void entityRemoved(Entity entity) {
		target = null;
	}

	/* CameraControls */
	/**
	 * will be automatically downscaled!
	 * 
	 * @param width
	 * @param height
	 */
	public void resizeCameraViewport(int width, int height) {
		camera.setToOrtho(GameConstants.YDOWN, width
				/ GameConstants.BOX2D_SCALE, height / GameConstants.BOX2D_SCALE);
		camera.update(true);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCameraPosition(float x, float y) {
		camera.position.x = x / GameConstants.BOX2D_SCALE;
		camera.position.y = y / GameConstants.BOX2D_SCALE;
		
		//camera.translate(x, y);
		camera.update(true);
	}

	public Matrix4 getCombinedMatrix() {
		return camera.combined;
	}

}
