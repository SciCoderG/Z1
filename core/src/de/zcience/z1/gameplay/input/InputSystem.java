package de.zcience.z1.gameplay.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import de.zcience.z1.gameplay.utils.ZComponentMapper;
import de.zcience.zengine.input.ZKeyboardListener;
import de.zcience.zengine.utils.Directions;

/**
 * Use this System to actually give input orders to a Input controlled Entity
 * TODO: Change this for multiple players
 * 
 * @author Zcience
 *
 */
public class InputSystem extends IteratingSystem implements ZKeyboardListener {

	private Vector2 direction = new Vector2();

	@SuppressWarnings("unchecked")
	public InputSystem() {
		super(Family.all(InputComponent.class).get());
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		InputComponent iComp = ZComponentMapper.input.get(entity);
		if (iComp.isActive()) {
			iComp.getDirection().set(this.direction);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		boolean handled = true;
		switch (keycode) {
		case Keys.A:
			this.direction.add(Directions.LEFT);
			break;
		case Keys.D:
			this.direction.add(Directions.RIGHT);
			break;
		case Keys.W:
			this.direction.add(Directions.UP);
			break;
		case Keys.S:
			this.direction.add(Directions.DOWN);
			break;
		case Keys.SPACE:
			break;
		default:
			handled = false;
			break;
		}
		return handled;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean handled = true;
		switch (keycode) {
		case Keys.A:
			this.direction.sub(Directions.LEFT);
			break;
		case Keys.D:
			this.direction.sub(Directions.RIGHT);
			break;
		case Keys.W:
			this.direction.sub(Directions.UP);
			break;
		case Keys.S:
			this.direction.sub(Directions.DOWN);
			break;
		case Keys.SPACE:
			break;
		default:
			handled = false;
			break;
		}
		return handled;
	}

	/**
	 * Needed for example, if you go into MainMenuScreen while pressing left or
	 * right and release in the MainMenuScreen. If you don't reset input, the
	 * keyUp will not be called -> after reentering the GameScreen you will walk
	 * in one direction
	 */
	public void resetInput() {
		direction.setZero();
	}

	@Override
	public void hide() {
		resetInput();
	}

}
