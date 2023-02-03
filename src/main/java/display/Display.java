package display;

import display.renderer.DebugRenderer;
import display.renderer.Renderer;
import game.state.State;
import input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private final Canvas canvas;
    private DebugRenderer debugRenderer;
    private final display.renderer.Renderer renderer;

    public Display(int width, int height, Input input) {
        setTitle("My game.Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        debugRenderer = new DebugRenderer();
        this.renderer = new Renderer();

        canvas = new Canvas();
        canvas.setSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        addKeyListener(input);
        pack();

        canvas.createBufferStrategy(2);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(State state, boolean debugMode) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderer.render(state, graphics);
        if(debugMode) {
            debugRenderer.render(state, graphics);
        }
        graphics.dispose();
        bufferStrategy.show();
    }

}
