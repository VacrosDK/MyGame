package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private final SpriteSet spriteSet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private int currentFrameTime;
    private final int updatesPerFrame;
    private int frameIndex;
    private int directionIndex;

    public AnimationManager(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        directionIndex = 0;
        this.currentFrameTime = 0;
        playAnimation("stand");
        currentAnimationName = "";
    }

    public Image getSprite() {
        return currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE);
    }

    public void update(Direction direction) {
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();

        if(currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            if(frameIndex >= currentAnimationSheet.getWidth() / Game.SPRITE_SIZE) {
                frameIndex = 0;
            }
        }
    }

    public void playAnimation(String name) {
        if(!name.equals(currentAnimationName)) {
            this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
            currentAnimationName = name;
            frameIndex = 0;
        }
    }
}
