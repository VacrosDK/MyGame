package ai;

import ai.state.AIState;
import ai.state.Stand;
import ai.state.Wander;
import entity.NPC;
import game.state.State;

public class AIManager {

    private AIState currentAIState;

    public AIManager() {
        transitionTo("stand");
    }

    public void update(State state, NPC npc) {
        currentAIState.update(state, npc);

        if(currentAIState.shouldTransition(state, npc)) {
            transitionTo(currentAIState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
        switch(nextState) {
            case "wander":
                currentAIState = new Wander();
                return;
            case "stand":
            default:
                currentAIState = new Stand();
        }
    }
}
