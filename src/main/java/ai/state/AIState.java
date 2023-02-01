package ai.state;

import ai.AITransition;
import entity.NPC;
import game.state.State;

public abstract class AIState {

    private AITransition transition;

    public AIState() {
        this.transition = initTransition();
    }

    protected abstract AITransition initTransition();
    public abstract void update(State state, NPC npc);

    public boolean shouldTransition(State state, NPC npc) {
        return transition.shouldTransition(state, npc);
    }

    public String getNextState() {
        return transition.getNextState();
    }
}
