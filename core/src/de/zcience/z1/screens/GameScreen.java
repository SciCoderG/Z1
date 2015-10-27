package de.zcience.z1.screens;

import com.badlogic.gdx.Screen;

import de.zcience.ZApplication;

public class GameScreen implements Screen{

	private ZApplication app;
	
	public GameScreen(ZApplication app) {
		this.app = app;
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		app.getBatch().begin();
		app.getFontUtility().getFont("default").draw(app.getBatch(), "Game Screen", 50, 50);
		app.getBatch().end();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
