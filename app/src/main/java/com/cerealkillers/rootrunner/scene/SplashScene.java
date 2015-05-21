package com.cerealkillers.rootrunner.scene;

/**
 * Created by jharshman on 5/14/15.
 */
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.util.GLState;
import org.andengine.entity.sprite.Sprite;
import com.cerealkillers.rootrunner.SceneManager.SceneType;

public class SplashScene extends BaseScene {

    /* variables */
    private Sprite splash;

    @Override
    public void createScene() {
        splash = new Sprite(0, 0, resourceManager.splashTextureRegion, vertexBufferObjectManager) {
            @Override
            protected void preDraw(GLState glState, Camera camera) {
                super.preDraw(glState, camera);
                glState.enableDither();
            }
        };
        splash.setScale(1.5f);
        splash.setPosition(350,200);
        attachChild(splash);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SPLASH;
    }

    @Override
    public void disposeScene() {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }
}
