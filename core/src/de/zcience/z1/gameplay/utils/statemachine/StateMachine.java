package de.zcience.z1.gameplay.utils.statemachine;

import java.util.HashMap;

public class StateMachine {
	private HashMap<String, State> states = new HashMap<String, State>();
	private State currentState = null;

	public void addState(String id, State state) {
		states.put(id, state);
	}

	public void removeState(String id) {
		states.remove(id);
	}

	public void switchToState(String id) {
		currentState = states.get(id);
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	public void update(float deltaTime){
		currentState.update(deltaTime);
	}

}
