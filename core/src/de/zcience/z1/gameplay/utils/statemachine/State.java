package de.zcience.z1.gameplay.utils.statemachine;

public abstract class State {
	protected StateMachine machine;

	public State(StateMachine machine) {
		this.machine = machine;
	}

	public abstract void enter();

	public abstract void update(float deltaTime);

	public abstract void leave();

	public StateMachine getMachine() {
		return machine;
	}

	public void setMachine(StateMachine machine) {
		this.machine = machine;
	}

}
