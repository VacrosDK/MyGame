package game.state;

import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected SpriteLibrary spriteLibrary;
    protected List<GameObject> gameObjectList;
    protected Input input;
    protected Camera camera;
    private Time time;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjectList = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
        time = new Time();
    }

    public void update() {
        sortObjectsByPosition();
        gameObjectList.forEach(gameObject -> gameObject.update(this));
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        gameObjectList.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
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

    public Time getTime() {
        return time;
    }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }
}
