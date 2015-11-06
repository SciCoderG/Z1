package de.zcience.z1.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import de.zcience.ZApplication;
import de.zcience.zengine.input.ZKeyboardListener;
import de.zcience.zengine.input.ZMouseListener;

/**
 * This is a simpliefied version.
 * 
 * @author Zcience
 *
 */
public class GameScreenInputProcessor implements InputProcessor
{

    private ZApplication app;

    private GameScreen gameScreen;

    private ArrayList<ZKeyboardListener> keyboardListener = new ArrayList<ZKeyboardListener>();

    private ArrayList<ZMouseListener> mouseListener = new ArrayList<ZMouseListener>();

    public GameScreenInputProcessor(ZApplication app, GameScreen gameScreen)
    {
        this.app = app;
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        boolean handled = false;
        for (ZKeyboardListener l : keyboardListener)
        {
            handled = handled || l.keyDown(keycode);
        }
        return handled;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        boolean handled = false;
        switch (keycode)
        {
            case Keys.ESCAPE:
                app.switchToScreen("MenuScreen");
                handled = true;
                break;

            default:
                break;
        }
        for (ZKeyboardListener l : keyboardListener)
        {
            handled = handled || l.keyDown(keycode);
        }
        return handled;

    }

    @Override
    public boolean keyTyped(char keycode)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        boolean handled = false;
        for (ZMouseListener l : mouseListener)
        {
            handled = handled || l.touchDown(screenX, screenY, pointer, button);
        }
        return handled;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        boolean handled = false;
        for (ZMouseListener l : mouseListener)
        {
            handled = handled || l.touchUp(screenX, screenY, pointer, button);
        }
        return handled;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        boolean handled = false;
        for (ZMouseListener l : mouseListener)
        {
            handled = handled || l.touchDragged(screenX, screenY, pointer);
        }
        return handled;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        boolean handled = false;
        for (ZMouseListener l : mouseListener)
        {
            handled = handled || l.mouseMoved(screenX, screenY);
        }
        return handled;
    }

    @Override
    public boolean scrolled(int amount)
    {
        boolean handled = false;
        for (ZMouseListener l : mouseListener)
        {
            handled = handled || l.scrolled(amount);
        }
        return handled;
    }

}
