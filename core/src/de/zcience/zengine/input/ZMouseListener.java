package de.zcience.zengine.input;

public interface ZMouseListener
{
    public boolean touchDown(int screenX, int screenY, int pointer, int button);
    
    public boolean touchUp(int screenX, int screenY, int pointer, int button);

    public boolean touchDragged(int screenX, int screenY, int pointer);

    public boolean mouseMoved(int screenX, int screenY);

    public boolean scrolled(int amount);
}
