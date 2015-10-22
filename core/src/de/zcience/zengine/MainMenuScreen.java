package de.zcience.zengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen
{

    private final ZApplication game;

    private Viewport viewPort;

    // TODO: This is only for testing!
    private BitmapFont testingFont;

    public MainMenuScreen(ZApplication game)
    {
        this.game = game;

        viewPort = new ScreenViewport();

        testingFont = game.getFontUtility().getFont("MainMenuFont");

    }

    @Override
    public void show()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        testingFont.draw(game.getBatch(), "heyho", 100, 100);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height)
    {
        viewPort.update(width, height);
    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
        testingFont.dispose();
    }

}
