package de.zcience.zengine.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

import de.zcience.zengine.physics.PhysicsBodyDef;
import de.zcience.zengine.physics.PhysicsComponent;
import de.zcience.zengine.physics.Box2DComponent;
import de.zcience.zengine.physics.PhysicsFixtureDef;
import de.zcience.zengine.physics.PhysicsSystem;

public class EntityCreator
{

    public static short LIGHT = 0x008;

    public static short WORLDOBJECT = 0x002;

    public static short HEARTH = 0x004;

    public static short PLAYER = 0x006;

    private static PooledEngine engine;

    public static Entity createPlayer(float x, float y)
    {
        Entity entity = engine.createEntity();
        PhysicsSystem physicsSystem = engine.getSystem(PhysicsSystem.class);

        /* TextureComponent */
        // TextureComponent textureComponent = engine
        // .createComponent(TextureComponent.class);
        //
        // textureComponent.texture = new
        // TextureRegion(AssetLoader.getAssetManager().get("/images/Amor2.png",
        // Texture.class));
        // textureComponent.width = textureComponent.texture.getRegionWidth();
        // textureComponent.height = textureComponent.texture.getRegionHeight();
        // entity.add(textureComponent);

        PhysicsComponent pComp = engine.createComponent(PhysicsComponent.class);
        entity.add(pComp);

        /*
         * Box2D body
         */
        // float width = textureComponent.width;
        // float height = textureComponent.height;
        float width = 5.0f;
        float height = 5.0f;

        Box2DComponent box2D = engine.createComponent(Box2DComponent.class);
        PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody, physicsSystem).fixedRotation(true).position(x, y).gravityScale(0.0f);

        box2D.init(bodyDef, physicsSystem);

        // Head
        PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height * 0.12f, new Vector2(0, height * 0.25f)).friction(0).category(PLAYER).mask(WORLDOBJECT);

        Fixture fixture = box2D.createFixture(fixtureDef);
        fixture.setUserData(box2D);

        // middle
        fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeBox(width * 0.2f, height * 0.6f, new Vector2(0, -height * 0.1f), 0).friction(0).category(PLAYER).mask(WORLDOBJECT);
        fixture = box2D.createFixture(fixtureDef);
        fixture.setUserData(box2D);

        // bottom
        fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height * 0.12f, new Vector2(0, -height * 0.4f)).friction(0).category(PLAYER).mask(WORLDOBJECT);

        fixture = box2D.createFixture(fixtureDef);
        fixture.setUserData(box2D);

        // jumpsensor
        fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height / 10.0f, new Vector2(0, -height * 0.5f)).sensor(true).category(PLAYER).mask(WORLDOBJECT);

        fixture = box2D.createFixture(fixtureDef);
        fixture.setUserData("Jump");

        entity.add(box2D);

        // InputComponent
        // InputComponent inputC = engine.createComponent(InputComponent.class);
        // inputC.shootTimerMax = 0.1f;
        // entity.add(inputC);

        // PositionComponent
        // PositionComponent positionComponet = engine
        // .createComponent(PositionComponent.class);
        // positionComponet.x = x;
        // positionComponet.y = y;
        // entity.add(positionComponet);

        // MovementComponent
        // MovementComponent movementComponent = engine
        // .createComponent(MovementComponent.class);
        // movementComponent.speed = 4.0f;
        // entity.add(movementComponent);

        // // PlayerComponent
        // entity.add(engine.createComponent(PlayerComponent.class));
        //
        // // JumpComponent
        // entity.add(engine.createComponent(JumpComponent.class));

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
