package entity.effect;

import entity.MovingEntity;
import entity.action.Pee;
import game.GameLoop;
import game.state.State;

public class Stomachache extends Effect {

    private static final double PEE_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10;

    public Stomachache() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public void update(State state, MovingEntity entity) {
        super.update(state, entity);
        if(Math.random() < PEE_RATE) {
            System.out.println("PEE TIME");
            entity.performAction(new Pee());
        }
    }
}
