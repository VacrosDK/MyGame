package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import input.Input;
import map.GameMap;

public class GameState extends State{

    public GameState(Input input, Size windowSize) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        initCharacters();
    }

    private void initCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjectList.add(player);
        camera.focusOn(player);

        initNPCs(250);
    }

    private void initNPCs(int amountOfNPCs) {
        for (int i = 0; i < amountOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            gameObjectList.add(npc);
        }
    }
}
