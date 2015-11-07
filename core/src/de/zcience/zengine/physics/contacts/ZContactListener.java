package de.zcience.zengine.physics.contacts;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.zcience.zengine.physics.components.Box2DComponent;

public class ZContactListener implements ContactListener
{

    @Override
    public void beginContact(Contact contact)
    {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Box2DComponent b2D = null;
        if (fixtureA.getUserData().equals("Jump"))
        {
            b2D = (Box2DComponent) fixtureA.getBody().getUserData();
        }
        else if (fixtureB.getUserData().equals("Jump"))
        {
            b2D = (Box2DComponent) fixtureB.getBody().getUserData();
        }
        if(null!= b2D)
        {
            
        }
    }

    @Override
    public void endContact(Contact contact)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {
        // TODO Auto-generated method stub

    }

}
