package de.zcience.zengine.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

import de.zcience.zengine.input.InputComponent;
import de.zcience.zengine.physics.components.Box2DComponent;
import de.zcience.zengine.physics.components.PositionComponent;
import de.zcience.zengine.physics.systems.PhysicsSystem;
import de.zcience.zengine.physics.utils.PhysicsBodyDef;
import de.zcience.zengine.physics.utils.PhysicsFixtureDef;

public class EntityCreator
{

    public static short LIGHT = 0x008;

    public static short WORLDOBJECT = 0x002;

    public static short PLAYER = 0x006;

    private static PooledEngine engine;

    public static Entity createPlayer(float x, float y)
    {
        Entity entity = engine.createEntity();
        PhysicsSystem physicsSystem = engine.getSystem(PhysicsSystem.class);

        PositionComponent pComp = engine.createComponent(PositionComponent.class);
        entity.add(pComp);

        InputComponent iComp = engine.createComponent(InputComponent.class);
        iComp.init(true, 4f, 20f);
        entity.add(iComp);
        /*
         * Box2D body
         */
        float width = 1.0f;
        float height = 1.0f;

        Box2DComponent box2D = engine.createComponent(Box2DComponent.class);
        PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody, physicsSystem).fixedRotation(true).position(x, y);

        box2D.init(bodyDef, physicsSystem, entity);

        // Head
        PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height * 0.12f, new Vector2(0, height * 0.25f)).friction(0);

        box2D.createFixture(fixtureDef);

        // middle
        fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeBox(width * 0.2f, height * 0.6f, new Vector2(0, -height * 0.1f), 0).friction(0);
        box2D.createFixture(fixtureDef);

        // bottom
        fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height * 0.12f, new Vector2(0, -height * 0.4f));

        box2D.createFixture(fixtureDef);

        // jumpsensor
        fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height / 10.0f, new Vector2(0, -height * 0.5f)).sensor(true);

        Fixture fixture = box2D.createFixture(fixtureDef);
        fixture.setUserData("Jump");

        entity.add(box2D);

        // LightComponent
        // LightComponent lightCompo = engine
        // .createComponent(LightComponent.class);
        // lightCompo.light = new PointLight(LightSystem.rayHandler, 256,
        // new Color(0.3f, 0.3f, 0.3f, 1f), 9, x, y);

        // lightCompo.light.attachToBody(physicsBody.getBody());
        //
        // entity.add(lightCompo);

        engine.addEntity(entity);
        return entity;
    }

    public static PooledEngine getEngine()
    {
        return engine;
    }

    public static void setEngine(PooledEngine engine)
    {
        EntityCreator.engine = engine;
    }
}
