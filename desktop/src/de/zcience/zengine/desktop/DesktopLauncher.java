package de.zcience.zengine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.zcience.ZApplication;

public class DesktopLauncher
{
    public static void main(String[] arg)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "z1";
        config.width = 480;
        config.height = config.width * 9 / 16;
        new LwjglApplication(new ZApplication(), config);
    }
}
