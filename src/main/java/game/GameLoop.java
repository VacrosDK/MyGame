package game;

public class GameLoop implements Runnable{

    public static final int UPDATES_PER_SECOND = 60;
    private final Game game;

    private boolean isRunning;
    //TODO option i settings menu
    private final double updateRate = 1.0d/UPDATES_PER_SECOND;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        isRunning = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while(isRunning) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime-lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;


            if(accumulator >= updateRate) {
                while(accumulator > updateRate) {
                    update();
                    accumulator -= updateRate;
                }
            }
            render();
            printStats();
        }
    }

    private void printStats() {
        if(System.currentTimeMillis() > nextStatTime) {
            System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void update() {
        game.update();
        ups++;
    }

    private void render() {
        game.render();
        fps++;
    }
}
