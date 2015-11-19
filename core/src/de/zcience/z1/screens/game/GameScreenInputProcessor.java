package de.zcience.z1.screens.game;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import de.zcience.ZApplication;
import de.zcience.zengine.input.ZKeyboardListener;
import de.zcience.zengine.input.ZMouseListener;

/**
 * This is processor distributes the complete input on the different input
 * listener, like the actual gameplay processor or ui-processor.
 * 
 * @author Zcience
 *
 */
public class GameScreenInputProcessor implements InputProcessor {

	private ZApplication app;

	private ArrayList<ZKeyboardListener> keyboardListener = new ArrayList<ZKeyboardListener>();

	private ArrayList<ZMouseListener> mouseListener = new ArrayList<ZMouseListener>();

	public GameScreenInputProcessor(ZApplication app) {
		this.app = app;
	}

	@Override
	public boolean keyDown(int keycode) {
		boolean handled = false;
		for (ZKeyboardListener l : keyboardListener) {
			handled = handled || l.keyDown(keycode);
		}
		return handled;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean handled = false;
		switch (keycode) {
		case Keys.ESCAPE:
			app.switchToScreen("MenuScreen");
			handled = true;
			break;

		default:
			break;
		}
		for (ZKeyboardListener l : keyboardListener) {
			handled = handled || l.keyUp(keycode);
		}
		return handled;

	}

	@Override
	public boolean keyTyped(char keycode) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean handled = false;
		for (ZMouseListener l : mouseListener) {
			handled = handled || l.touchDown(screenX, screenY, pointer, button);
		}
		return handled;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		boolean handled = false;
		for (ZMouseListener l : mouseListener) {
			handled = handled || l.touchUp(screenX, screenY, pointer, button);
		}
		return handled;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		boolean handled = false;
		for (ZMouseListener l : mouseListener) {
			handled = handled || l.touchDragged(screenX, screenY, pointer);
		}
		return handled;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		boolean handled = false;
		for (ZMouseListener l : mouseListener) {
			handled = handled || l.mouseMoved(screenX, screenY);
		}
		return handled;
	}

	@Override
	public boolean scrolled(int amount) {
		boolean handled = false;
		for (ZMouseListener l : mouseListener) {
			handled = handled || l.scrolled(amount);
		}
		return handled;
	}

	public ArrayList<ZKeyboardListener> getKeyboardListener() {
		return keyboardListener;
	}

	public void setKeyboardListener(ArrayList<ZKeyboardListener> keyboardListener) {
		this.keyboardListener = keyboardListener;
	}

	public ArrayList<ZMouseListener> getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(ArrayList<ZMouseListener> mouseListener) {
		this.mouseListener = mouseListener;
	}

	public void addKeyboardListener(ZKeyboardListener l) {
		keyboardListener.add(l);
	}

	public void removeKeyboardListener(ZKeyboardListener l) {
		keyboardListener.remove(l);
	}

	public void addMouseListener(ZMouseListener l) {
		mouseListener.add(l);
	}

	public void removeMouseListener(ZMouseListener l) {
		mouseListener.remove(l);
	}

	/**
	 * Is called, if the screen is switched
	 */
	public void hide() {
		for(ZKeyboardListener l : keyboardListener){
			l.hide();
		}
		for(ZMouseListener l : mouseListener){
			l.hide();
		}
	}

}
