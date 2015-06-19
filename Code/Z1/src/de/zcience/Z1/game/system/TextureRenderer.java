package de.zcience.Z1.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.zcience.Z1.game.EntityCreator;
import de.zcience.Z1.game.components.MovementComponent;
import de.zcience.Z1.game.components.PositionComponent;
import de.zcience.Z1.game.components.TextureComponent;
import de.zcience.Z1.game.util.CompMappers;
import de.zcience.Z1.game.util.DrawUtil;
import de.zcience.Z1.game.util.GameConstants;

public class TextureRenderer extends IteratingProfilingSystem {

	public TextureRenderer(int priority) {
		super(
				Family.all(TextureComponent.class, PositionComponent.class)
						.get(), priority);
		profiler.setMessage("texture-");
//		setProfiling(true);
	}

	@Override
	public void update(float deltaTime) {
		DrawUtil.batch.begin();
		super.update(deltaTime);
		DrawUtil.batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TextureComponent texComp = CompMappers.texture.get(entity);

		// if needed, flip the texture
		MovementComponent moveComp = CompMappers.movement.get(entity);
		if (moveComp != null) {
			if (moveComp.velocity.x < 0 && !texComp.texture.isFlipX()) {
				texComp.texture.flip(true, false);
			} else if (moveComp.velocity.x > 0 && texComp.texture.isFlipX()) {
				texComp.texture.flip(true, false);
			}
		}
		
		PositionComponent position = CompMappers.position.get(entity);
		OrthographicCamera camera = EntityCreator.camSystem.getCamera();

		float transX = camera.position.x - (camera.viewportWidth / 2);
		float transY = camera.position.y - (camera.viewportHeight / 2);
		transX *= GameConstants.BOX2D_SCALE;
		transY *= GameConstants.BOX2D_SCALE;

		transX = position.x - transX - (texComp.width / 2);
		transY = position.y - transY - (texComp.height / 2);

		DrawUtil.batch.draw(texComp.texture, transX, transY, texComp.width,
				texComp.height);
	}
}
