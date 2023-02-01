package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.NPC;
import game.state.State;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState{
    private List<Position> targets;

    public Wander() {
        super();
        targets = new ArrayList<>();
    }

    @Override
    protected AITransition initTransition() {
        return new AITransition("stand", (state, currentNPC) -> arrived(currentNPC));
    }

    @Override
    public void update(State state, NPC npc) {
        if(targets.isEmpty()) {
            targets.add(state.getRandomPosition());
        }
        NPCController controller = (NPCController) npc.getController();
        controller.moveToTarget(targets.get(0), npc.getPosition());

        if(arrived(npc)) {
            controller.stop();
        }
    }

    private boolean arrived(NPC npc) {
        return npc.getPosition().isInRangeOf(targets.get(0));
    }
}
