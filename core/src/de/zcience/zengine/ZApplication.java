package de.zcience.zengine;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ZApplication extends Game {
	private SpriteBatch batch;
	public BitmapFont font;
	
	
	@Override
	public void create() {
		// Setting LogLevel. For now: Log everything
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();
		font = new BitmapFont();
		// Create an AssetManager, create a Loader Class for Screens to make it all Data-Driven
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	public void render() {
		super.render(); // Important! Needed, because we're extending the gdx
						// Game class
	}
}
