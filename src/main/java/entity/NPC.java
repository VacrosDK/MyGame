package entity;

import ai.AIManager;
import controller.Controller;
import entity.action.Action;
import entity.action.Cough;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.util.Optional;

public class NPC extends MovingEntity{
    private AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit("kevin"));
        aiManager = new AIManager();
    }

    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player) {
            motion.stop();
        }
    }

}
