package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.NPC;
import entity.Player;
import game.Game;
import input.Input;
import map.GameMap;

import java.util.Arrays;

public class GameState extends State{

    public GameState(Input input, Size windowSize) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        initCharacters();
    }

    private void initCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        NPC npc = new NPC(new NPCController(), spriteLibrary);
        npc.setPosition(new Position(3 * Game.SPRITE_SIZE, 3 * Game.SPRITE_SIZE));
        gameObjectList.addAll(Arrays.asList(player, npc));
        camera.focusOn(npc);
    }
}
