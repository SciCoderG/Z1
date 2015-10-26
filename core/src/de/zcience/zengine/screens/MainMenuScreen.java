package de.zcience.zengine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.zengine.ZApplication;
import de.zcience.zengine.utils.LoaderUtil;

public class MainMenuScreen implements Screen
{

    private Stage stage;

    public MainMenuScreen(ZApplication app)
    {

        this.stage = new Stage(new ScreenViewport(), app.getBatch());
        Gdx.input.setInputProcessor(stage);

        // Create Main Button Field as a table
        // TODO: Load from files
        Table table = new Table();
        table.setFillParent(true); // adjust to parent size
        // set the default width and height of the table cells
        table.defaults().width(100.0f).height(50.0f);

        stage.addActor(table);

        // load a simple white skin
        Skin skin = LoaderUtil.makeSimpleSkin();

        // Start Button
        TextButton start1 = new TextButton("Start", skin);
        start1.addListener(new ChangeListener()
        {

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("Start");

            }
        });
        table.add(start1);

        // End Button
        TextButton end = new TextButton("End", skin);
        end.addListener(new ChangeListener()
        {

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.exit();
            }
        });
        table.row();
        table.add(end);

    }

    @Override
    public void show()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta)
    {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }

}
