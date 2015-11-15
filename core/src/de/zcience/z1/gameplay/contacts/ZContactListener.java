package de.zcience.z1.gameplay.contacts;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.zcience.z1.gameplay.movement.JumpComponent;

public class ZContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		JumpComponent jump = getJumpComponent(fixtureA, fixtureB);
		if (null != jump) {
			jump.addGroundContact();
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		JumpComponent jump = getJumpComponent(fixtureA, fixtureB);
		if (null != jump) {
			jump.subGroundContact();
		}
	}

	/**
	 * Get the JumpComponent of the Entity with a jump Sensor (UserData =
	 * "Jump") or null
	 * 
	 * @return
	 */
	private JumpComponent getJumpComponent(Fixture fixtureA, Fixture fixtureB) {
		Entity entity = null;

		if (null != fixtureA.getUserData() && fixtureA.getUserData().equals("Jump")) {
			entity = (Entity) fixtureA.getBody().getUserData();
		} else if (null != fixtureB.getUserData() && fixtureB.getUserData().equals("Jump")) {
			entity = (Entity) fixtureB.getBody().getUserData();
		} else {
			return null;
		}
		JumpComponent jump = null;
		if (null != entity) {
			jump = entity.getComponent(JumpComponent.class);
		}
		return jump;
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
