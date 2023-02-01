package entity.effect;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Speedster extends Effect{

    private double speedMultiplier;

    public Speedster() {
        super(GameLoop.UPDATES_PER_SECOND * 5);
        speedMultiplier = 2.5;
    }

    @Override
    public void update(State state, MovingEntity movingEntity) {
        super.update(state, movingEntity);

        movingEntity.multiplySpeed(speedMultiplier);
    }

}
