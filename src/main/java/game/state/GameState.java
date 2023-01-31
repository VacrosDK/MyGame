package game.state;

import controller.PlayerController;
import core.Size;
import entity.Player;
import input.Input;
import map.GameMap;

public class GameState extends State{

    public GameState(Input input, Size windowSize) {
        super(windowSize, input);
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjectList.add(player);
        gameMap = new GameMap(new Size(100, 100), spriteLibrary);
        camera.focusOn(player);
    }
}
