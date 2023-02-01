package entity.effect;

import entity.MovingEntity;
import game.state.State;

public abstract class Effect {

    private int lifeSpanInUpdates;

    public Effect(int lifeSpan) {
        this.lifeSpanInUpdates = lifeSpan;
    }

    public void update(State state, MovingEntity movingEntity) {
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete() {
        return lifeSpanInUpdates <= 0;
    }

    public void remove() {

    }
}
