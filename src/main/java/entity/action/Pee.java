package entity.action;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Pee extends Action {

    private int lifeSpanInSeconds;

    public Pee() {
        this.lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND * 2;
    }

    @Override
    public void update(State state, MovingEntity entity) {
        lifeSpanInSeconds--;
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds <= 0;
    }

    @Override
    public String getAnimationName() {
        return "pee";
    }
}
