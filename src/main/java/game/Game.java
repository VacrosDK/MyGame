package game;

import core.Size;
import display.Display;
import game.settings.GameSettings;
import game.state.GameState;
import game.state.State;
import input.Input;

public class Game {

    public static final int SPRITE_SIZE = 64;

    private final Display display;
    private Input input;
    private final State state;
    private GameSettings settings;

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(input, new Size(width, height));
        settings = new GameSettings(true);
    }

    public void update() {
        state.update();
    }

    public void render() {
        display.render(state, settings.isDebugMode());
    }

}
