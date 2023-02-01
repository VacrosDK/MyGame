package ai.state;

import ai.AITransition;
import entity.NPC;
import game.state.State;

public class Stand extends AIState{
    private int updatesAlive;

    @Override
    protected AITransition initTransition() {
        return new AITransition("wander", ((state, currentNPC) -> updatesAlive >= state.getTime().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(State state, NPC npc) {
        updatesAlive++;
    }
}
