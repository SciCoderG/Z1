package de.zcience.zengine.input;

public interface ZKeyboardListener
{
    public boolean keyDown(int keycode);
    public boolean keyUp(int keycode);
    public void hide();
}
