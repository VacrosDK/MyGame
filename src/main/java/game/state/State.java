package game.state;

import core.Size;
import display.Camera;
import entity.GameObject;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected SpriteLibrary spriteLibrary;
    protected List<GameObject> gameObjectList;
    protected Input input;
    protected Camera camera;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjectList = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
    }

    public void update() {
        gameObjectList.forEach(GameObject::update);
        camera.update(this);
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }
}
