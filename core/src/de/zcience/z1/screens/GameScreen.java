package de.zcience.z1.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.zcience.ZApplication;

public class GameScreen implements Screen {

	private ZApplication app;

	private PooledEngine engine;
	private GameScreenInputProcessor gInputProcessor;

	public GameScreen(ZApplication app) {
		this.app = app;
		this.engine = new PooledEngine(); // Important - this will only add 100
											// Entities and 100 Components of
											// each
											// type to each pool!
		// Set inputprocessor
		this.gInputProcessor = new GameScreenInputProcessor(app);

		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(gInputProcessor);
	}

	@Override
	public void render(float delta) {
		engine.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		app.getAssetManager().finishLoading();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		
	}

}
