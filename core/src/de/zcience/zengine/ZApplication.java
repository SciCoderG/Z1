package de.zcience.zengine;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.zcience.zengine.fonts.FontUtility;

public class ZApplication extends Game
{
    private SpriteBatch batch;

    private FontUtility fontUtility;

    @Override
    public void create()
    {
        // Setting LogLevel. For now: Log everything
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        
        // set clear color
        Gdx.gl.glClearColor(0, 0, 1.0f, 1.0f);

        
        batch = new SpriteBatch();
        fontUtility = new FontUtility();
        // Create an AssetManager, create a Loader Class for Screens to make it
        // all Data-Driven
        this.setScreen(new MainMenuScreen(this));

    }

    public SpriteBatch getBatch()
    {
        return batch;
    }

    public void setBatch(SpriteBatch batch)
    {
        this.batch = batch;
    }

    public FontUtility getFontUtility()
    {
        return fontUtility;
    }

    public void setFontUtility(FontUtility fontUtility)
    {
        this.fontUtility = fontUtility;
    }

    @Override
    public void render()
    {
        super.render(); // Important! Needed, because we're extending the gdx
                        // Game class
    }
    
    @Override
    public void dispose()
    { 
        super.dispose();
        fontUtility.dispose();
    }
}
