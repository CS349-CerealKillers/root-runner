package com.cerealkillers.rootrunner.scene;

/**
 * Created by jharshman on 5/15/15.
 */

import org.andengine.entity.scene.background.Background;
import org.andengine.util.color.Color;
import org.andengine.entity.text.Text;
import com.cerealkillers.rootrunner.SceneManager.SceneType;

public class LoadingScene extends BaseScene {

    @Override
    public void createScene() {
        setBackground(new Background(Color.WHITE));
        attachChild(new Text(400, 240, resourceManager.getMenuFont(), "Loading...", vertexBufferObjectManager));
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.LOAD;
    }

    @Override
    public void disposeScene() {

    }

}
