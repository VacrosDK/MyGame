package game;

import controller.PlayerController;
import display.Display;
import entity.GameObject;
import entity.Player;
import input.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Display display;
    private List<GameObject> gameObjectList;
    private Input input;

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height, input);
        gameObjectList = new ArrayList<>();
        gameObjectList.add(new Player(new PlayerController(input)));
    }

    public void update() {
        gameObjectList.forEach(GameObject::update);
    }

    public void render() {
        display.render(this);
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }
}
