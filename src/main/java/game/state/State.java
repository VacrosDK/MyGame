package game.state;

import controller.PlayerController;
import entity.GameObject;
import entity.Player;
import gfx.SpriteLibrary;
import input.Input;

import java.util.ArrayList;
import java.util.List;

public abstract class State {

    private SpriteLibrary spriteLibrary;
    private List<GameObject> gameObjectList;
    private Input input;

    public State(Input input) {
        gameObjectList = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        gameObjectList.add(new Player(new PlayerController(input), spriteLibrary));
    }

    public void update() {
        gameObjectList.forEach(GameObject::update);
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }
}
