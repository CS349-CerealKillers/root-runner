package com.cerealkillers.rootrunner.GameObjects;

/**
 * Created by Benjamin Daschel on 5/31/15.
 */
public interface IControl {

    public enum Direction{
        UP,
        RIGHT,
        DOWN,
        LEFT,
        NONE
    }

    public void onMove(Direction direction);
}
