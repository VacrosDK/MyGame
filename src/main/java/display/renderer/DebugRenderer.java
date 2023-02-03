package display.renderer;

import core.CollisionBox;
import display.Camera;
import game.state.State;

import java.awt.*;

public class DebugRenderer {

    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        state.getGameObjectList().stream()
                .filter(camera::isInView)
                .map(object -> object.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.RED);
        graphics.drawRect(
                (int)collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int)collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int)collisionBox.getBounds().getWidth(),
                (int)collisionBox.getBounds().getHeight()
        );
    }

}
