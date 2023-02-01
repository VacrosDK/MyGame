package entity;

import controller.Controller;
import core.Direction;
import core.Motion;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;

public abstract class MovingEntity extends GameObject{

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        //TODO Speed i settings eller config(unlock)
        this.motion = new Motion(5);
        this.direction = Direction.S;
        animationManager = new AnimationManager(spriteLibrary.getUnit("plop"));
    }

    @Override
    public void update(State state) {
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animationManager.update(direction);
    }

    private void decideAnimation() {
        if(motion.isMoving()) {
            animationManager.playAnimation("walk");
        }else {
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if(motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
        }
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public Object getController() {
        return controller;
    }
}
