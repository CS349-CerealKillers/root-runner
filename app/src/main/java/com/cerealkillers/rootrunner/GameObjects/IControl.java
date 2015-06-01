package com.cerealkillers.rootrunner.GameObjects;

/**
 * Created by Benjamin Daschel on 5/31/15.
 */
public interface IControl {

    public enum Direction{

        UP (0, 1),
        RIGHT (1, 0),
        DOWN (0, -1),
        LEFT (-1, 0),
        NONE (0, 0);

        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void onMove(Direction direction);
}
