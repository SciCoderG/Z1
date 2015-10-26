package de.zcience.zengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

	private MainMenuController controller;

	private Stage stage;

	// TODO: This is only for testing!
	private BitmapFont testingFont;

	public MainMenuScreen(ZApplication app) {
		this.controller = new MainMenuController();
		this.testingFont = app.getFontUtility().getFont("MainMenuFont");

		this.stage = new Stage(new ScreenViewport(), app.getBatch());
		Gdx.input.setInputProcessor(stage);

		// Create Main Button Field as a table
		// TODO: Load from files
		Table table = new Table();
		table.setFillParent(true); // adjust to parent size
		stage.addActor(table);

		// load a simple white skin
		Skin skin = LoaderUtil.makeSimpleSkin();

		// Start Button
		TextButton start1 = new TextButton("Start", skin);
		start1.pad(20.0f);
		table.add(start1).fill();

		// End Button
		TextButton end = new TextButton("End", skin);
		end.pad(20.0f);
		end.addListener(this.controller);
		table.row();
		table.add(end).fill();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
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
		testingFont.dispose();
		stage.dispose();
	}

}
