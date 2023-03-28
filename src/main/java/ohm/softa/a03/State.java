package ohm.softa.a03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class State {
    protected static final Logger logger = LogManager.getLogger();
    private int time = 0;
    private final int duration;

    protected State(int duration) {
        this.duration = duration;
    }

    public final State tick(Cat cat) {
        logger.info("tick()");
        time++;

        if (getTime() == getDuration()) {
            return successor(cat);
        }

        return this;
    }

    public abstract State successor(Cat cat);

    public int getTime() {
        return time;
    }
    public int getDuration() {
        return duration;
    }
}
