package de.zcience.z1.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import de.zcience.ZApplication;
import de.zcience.zengine.physics.PhysicsBodyDef;
import de.zcience.zengine.physics.PhysicsComponent;
import de.zcience.zengine.physics.PhysicsFixtureDef;
import de.zcience.zengine.physics.PhysicsSystem;

public class GameScreen implements Screen
{

    private ZApplication app;

    private PooledEngine engine;

    private GameScreenInputProcessor gInputProcessor;

    private ScreenViewport viewport;

    public Box2DDebugRenderer debugRenderer;

    public GameScreen(ZApplication app)
    {
        this.app = app;
        this.engine = new PooledEngine(); // Important - this will only add 100
                                          // Entities and 100 Components of
                                          // each
                                          // type to each pool!
        // Set inputprocessor
        this.gInputProcessor = new GameScreenInputProcessor(app);

        // Create Viewport
        this.viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(0.1f);

        debugRenderer = new Box2DDebugRenderer();

        // Create Systems
        PhysicsSystem pSystem = new PhysicsSystem();
        engine.addSystem(pSystem);

        // Create Plattform and ball
        Entity plattform = engine.createEntity();
        PhysicsComponent pComp1 = engine.createComponent(PhysicsComponent.class);
        plattform.add(pComp1);
        plattform.add(pComp1);
        engine.addEntity(plattform);

        /*
         * PhysicsBody
         */
        PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.StaticBody, pSystem).fixedRotation(true).position(1, 1);
        pComp1.init(bodyDef, pSystem);

        PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(pSystem).shapeBox(30, 10);
        pComp1.createFixture(fixtureDef);

    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(gInputProcessor);
    }

    @Override
    public void render(float delta)
    {
        debugRenderer.render(engine.getSystem(PhysicsSystem.class).getWorld(), viewport.getCamera().combined);
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        app.getAssetManager().finishLoading();
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose()
    {

    }

}
