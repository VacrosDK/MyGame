package map;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.SpriteLibrary;

import java.util.Arrays;
import java.util.Random;

public class GameMap {

    private static final int SAFETY_BOUNDS = 2;

    private Tile[][] tiles;

    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][ size.getHeight()];
        initTiles(spriteLibrary);
    }

    private void initTiles(SpriteLibrary spriteLibrary) {
        for(Tile[] row: tiles) {
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public double getWidth() {
        return tiles.length * Game.SPRITE_SIZE;
    }

    public double getHeight() {
        return tiles[0].length * Game.SPRITE_SIZE;
    }

    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;
        return new Position(x, y);
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / Game.SPRITE_SIZE - SAFETY_BOUNDS),
                Math.max(0, camera.getPosition().intY() / Game.SPRITE_SIZE - SAFETY_BOUNDS)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / Game.SPRITE_SIZE + camera.getSize().getWidth() / Game.SPRITE_SIZE + SAFETY_BOUNDS),
                Math.min(tiles.length, camera.getPosition().getY() / Game.SPRITE_SIZE + camera.getSize().getHeight() / Game.SPRITE_SIZE + SAFETY_BOUNDS));
    }
}
