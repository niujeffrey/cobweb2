package org.cobweb.cobweb2.core;


/**
 * Basic properties of an Agent
 */
public abstract class Agent implements Updatable {

	private boolean alive = true;

	protected LocationDirection position;

	/**
	 * Energy the Agent can use to do things and can gain doing other things
	 */
	private int energy;

	public void die() {
		assert (isAlive());
		if (!isAlive())
			return;

		alive = false;
	}

	protected void setEnergy(int value) {
		energy = value;
	}

	public int getEnergy() {
		return energy;
	}

	public boolean enoughEnergy(int required) {
		return getEnergy() >= required;
	}

	/**
	 * Changes the agent's energy level.
	 * @param delta Energy change delta, positive means agent gains energy, negative means it loses
	 * @param cause Why the energy changed.
	 */
	public void changeEnergy(int delta, Cause cause) {
		energy += delta;
	}

	/**
	 * @return the location this Agent occupies.
	 */
	public LocationDirection getPosition() {
		return position;
	}

	public boolean isAlive() {
		return alive;
	}

	public abstract int getType();

	protected abstract Agent createChildAsexual(LocationDirection location);

	@Override
	public void update() {

	}
}
