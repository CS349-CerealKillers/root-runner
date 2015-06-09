package com.cerealkillers.rootrunner.GameWorld;

/**
 * Created by jharshman on 6/8/15.
 */
public class PlayerContainer {

    private static PlayerContainer ourInstance = new PlayerContainer();

    public static PlayerContainer getInstance() {
        return ourInstance;
    }

    private PlayerContainer() {

    }

}
