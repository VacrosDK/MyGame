package entity;

import controller.Controller;
import core.CollisionBox;
import core.Direction;
import core.Motion;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    public List<Effect> effects;
    private Optional<Action> action;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        //TODO Speed i settings eller config(unlock)
        this.motion = new Motion(5);
        this.direction = Direction.S;
        animationManager = new AnimationManager(spriteLibrary.getUnit("plop"));
        effects = new ArrayList<>();
        action = Optional.empty();
    }

    @Override
    public void update(State state) {
        handleAction(state);
        handleMotion();
        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(motion);


        cleanup();
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    @Override
    public boolean isCollidingWith(GameObject other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    @Override
    public CollisionBox getCollisionBox() {
        return new CollisionBox(
                new Rectangle(
                        position.intX(),
                        position.intY(),
                        size.getWidth(),
                        size.getHeight()
                )
        );
    }

    protected abstract void handleCollision(GameObject other);

    private void handleMotion() {
        if (!action.isPresent()) {
            motion.update(controller);
        } else {
            motion.stop();
        }
    }

    private void handleAction(State state) {
        if (action.isPresent()) {
            action.get().update(state, this);
        }
    }

    private void cleanup() {
        cleanupEffects();
        if (action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    private void cleanupEffects() {
        new ArrayList<>(effects).stream().
                filter(Effect::shouldDelete)
                .forEach(effects::remove);
    }

    private void decideAnimation() {
        if (action.isPresent()) {
            animationManager.playAnimation(action.get().getAnimationName());
        } else if (motion.isMoving()) {
            animationManager.playAnimation("walk");
        } else {
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if (motion.isMoving()) {
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

    public void multiplySpeed(double multiplier) {
        motion.multiply(multiplier);
    }

    public void performAction(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    protected void clearEffects() {
        effects.clear();
    }
}
