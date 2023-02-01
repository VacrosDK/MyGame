package game;

import core.Size;
import display.Display;
import game.state.GameState;
import game.state.State;
import input.Input;

public class Game {

    public static final int SPRITE_SIZE = 64;

    private final Display display;
    private Input input;
    private final State state;

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(input, new Size(width, height));
    }

    public void update() {
        state.update();
    }

    public void render() {
        display.render(state);
    }

}
