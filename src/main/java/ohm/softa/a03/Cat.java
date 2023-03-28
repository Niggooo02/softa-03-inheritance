package ohm.softa.a03;

import ohm.softa.a03.states.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cat {
	private static final Logger logger = LogManager.getLogger();

	// valid states

	// initially, animals are sleeping
	private State currentState;

	// state durations (set via constructor), ie. the number of ticks in each state
	private final int sleep;
	private final int awake;
	private final int digest;

	private final String name;

	private int time = 0;
	private int timeDigesting = 0;

	public Cat(String name, int sleep, int awake, int digest) {
		this.name = name;
		this.sleep = sleep;
		this.awake = awake;
		this.digest = digest;

		currentState = new SleepingState(sleep);
	}

	public void tick(){
		logger.info("tick()");
		time = time + 1;

		currentState = currentState.tick(this);

		/* switch (currentState) {
			case SLEEPING:
				if (time == sleep) {
					logger.info("Yoan... getting hungry!");
					currentState = HUNGRY;
					time = 0;
				}
				break;
			case HUNGRY:
				if(time == awake){
					logger.info("I've starved for a too long time...good bye...");
					currentState = DEAD;
				}
				break;
			case DIGESTING:
				timeDigesting = timeDigesting + 1;
				if (timeDigesting == digest) {
					logger.info("Getting in a playful mood!");
					currentState = PLAYFUL;
				}
				break;
			case PLAYFUL:
				if (time >= awake) {
					logger.info("Yoan... getting tired!");
					currentState = SLEEPING;
					time = 0;
				}
				break;

			case DEAD:
				break;
			default:
				throw new IllegalStateException("Unknown cat state " + currentState.name());
		}

		logger.info(currentState.name()); */
	}

	/**
	 * This would be a user interaction: feed the cat to change its state!
	 */
	public void feed(){
		if (!isHungry())
			throw new IllegalStateException("Can't stuff a cat...");

		// change state and reset the timer
		currentState = ((HungryState) currentState).feed(this);
	}

	public boolean isAsleep() {
		return currentState instanceof SleepingState;
	}

	public boolean isPlayful() {
		return currentState instanceof PlayfulState;
	}

	public boolean isHungry() {
		return currentState instanceof HungryState;
	}

	public boolean isDigesting() {
		return currentState instanceof DigestingState;
	}

	public boolean isDead() {
		return currentState instanceof DeadState;
	}

	public int getSleep() {
		return sleep;
	}
	public int getAwake() {
		return awake;
	}
	public int getDigest() {
		return digest;
	}

	@Override
	public String toString() {
		return name;
	}

}
