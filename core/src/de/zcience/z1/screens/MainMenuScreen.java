package de.zcience.z1.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.ZApplication;
import de.zcience.zengine.ui.ChangeListenerToApp;

public class MainMenuScreen implements Screen {

	private Stage stage;

	private ZApplication app;

	public MainMenuScreen(ZApplication app) {

		this.app = app;
		this.stage = new Stage(new ScreenViewport(), app.getBatch());
		// add stage as inputprocessor

		this.loadAssets();

		// Create Main Button Field as a table
		// TODO: Load from files
		Table table = new Table();
		table.setFillParent(true); // adjust to parent size
		// set the default width and height of the table cells
		table.defaults().width(100.0f).height(50.0f);
		stage.addActor(table);

		// load a simple white skin
		Skin skin = app.getAssetManager().get("ui/uiskin.json");

		// Start Button
		TextButton start1 = new TextButton("Start", skin, "offset");
		start1.addListener(new ChangeListenerToApp(app) {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				this.getApplication().switchToScreen("GameScreen");
			}
		});
		table.add(start1);

		// End Button
		TextButton end = new TextButton("End", skin, "offset");
		end.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		table.row();
		table.add(end);
	}

	private void loadAssets() {
		app.getAssetManager().load("ui/uiskin.json", Skin.class);
		app.getAssetManager().finishLoading();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
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
		app.getAssetManager().finishLoading();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
