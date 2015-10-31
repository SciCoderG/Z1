package de.zcience.z1.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import de.zcience.ZApplication;

public class GameScreenInputProcessor implements InputProcessor
{

    private ZApplication app;

    public GameScreenInputProcessor(ZApplication app)
    {
        this.app = app;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        boolean caught = false;
        switch (keycode)
        {
            case Keys.ESCAPE:
                app.switchToScreen("MenuScreen");
                caught = true;
                break;

            default:
                break;
        }
        return caught;
    }

    @Override
    public boolean keyTyped(char keycode)
    {
       return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
