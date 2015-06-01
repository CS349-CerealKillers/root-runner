package com.cerealkillers.rootrunner.scene;

import org.andengine.entity.scene.Scene;

/**
 * Created by Benjamin Daschel on 6/1/15.
 */
public abstract class SceneDecorator<SceneType extends Scene> {

    private final SceneType mDecoratedScene;

    public SceneDecorator(SceneType scene){
        mDecoratedScene = scene;
    }

    public SceneType getScene(){
        return mDecoratedScene;
    }
}
