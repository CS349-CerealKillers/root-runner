package com.cerealkillers.rootrunner;

/**
 * Created by jharshman on 5/14/15.
 */

import com.cerealkillers.rootrunner.SceneManager.SceneType;

import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;

public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    /* variables */
    private MenuScene menuChildScene;
    private final int MENU_PLAY = 0;
    private final int MENU_OPTIONS = 1;

    private void createMenuChildScene() {

        menuChildScene = new MenuScene(boundCamera);
        menuChildScene.setPosition(10, 140);

        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourceManager.playRegion, vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem optionMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourceManager.optionRegion, vertexBufferObjectManager), 1.2f, 1);

        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(optionMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 10);
        optionMenuItem.setPosition(optionMenuItem.getX(), optionMenuItem.getY() - 110);

        menuChildScene.setOnMenuItemClickListener(this);
        setChildScene(menuChildScene);

    }

    public boolean onMenuItemClicked(MenuScene menuScene, IMenuItem iMenuItem, float menuItemLocalX, float menuItemLocalY) {
        switch(iMenuItem.getID()) {
            case MENU_PLAY: return true;
            case MENU_OPTIONS: return true;
            default: return false;

        }
    }

    @Override
    public void createScene() {
        createBackground();
        createMenuChildScene();
    }

    public void createBackground() {
        attachChild(new Sprite(75, 0, resourceManager.menuBackgroundRegion, vertexBufferObjectManager) {
            @Override
            protected void preDraw(GLState glState, Camera camera) {
                super.preDraw(glState,camera);
                glState.enableDither();
            }
        });

    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }
    @Override
    public SceneType getSceneType() {
        return SceneType.MENU;
    }
    @Override
    public void disposeScene() {

    }
}
