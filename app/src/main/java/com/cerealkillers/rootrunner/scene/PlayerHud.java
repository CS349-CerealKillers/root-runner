package com.cerealkillers.rootrunner.scene;

import android.graphics.Color;
import android.util.Log;

import com.cerealkillers.rootrunner.Game;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.Shape;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;

/**
 * Created by Benjamin Daschel on 6/1/15.
 */
public class PlayerHud extends SceneDecorator<BaseScene>{

    private static final int TERMINAL_HEIGHT = 48;
    private Text terminalText;
    private Rectangle terminal;
    private Font terminalFont;
    private Text messageText;
    private TimerHandler hudTimeout;

    public PlayerHud(BaseScene scene) {
        super(scene);
        createHud(scene);
    }

    private void createHud(BaseScene scene) {
        HUD playerHud = new HUD();

        terminal = new Rectangle(0, 0, scene.boundCamera.getWidth(), TERMINAL_HEIGHT, scene.vertexBufferObjectManager);
        terminal.setColor(org.andengine.util.color.Color.BLACK);
        terminalFont = scene.resourceManager.createFont("clacon.ttf", 32, Color.GREEN);
        terminalText = new Text(8, 8, terminalFont, ">>>", scene.vertexBufferObjectManager);

        terminal.attachChild(terminalText);
        playerHud.attachChild(terminal);
        terminal.setVisible(false);
        scene.boundCamera.setHUD(playerHud);
    }

    public void setMessage(String message){
        if(message != null){
            if(messageText!= null){
                messageText.detachSelf();
            }
            messageText = new Text(terminalText.getWidth() + 8, 8, terminalFont, message, getScene().vertexBufferObjectManager);
            showHud();
            terminal.attachChild(messageText);
            hideHudAfterTime(5);
        }
    }

    public void showHud(){
        terminal.setVisible(true);
    }

    public void hideHud(){
        terminal.setVisible(false);
    }

    private void hideHudAfterTime(float time){
        //Cancel the previous timeout if there is one
        Log.d("PlayerHud", String.format("Hiding hud after %f seconds on Thread %s", time, Thread.currentThread().getName()));
        if(hudTimeout != null){
            getScene().unregisterUpdateHandler(hudTimeout);
        }
        hudTimeout = new TimerHandler(time, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler timerHandler) {
                onHudTimeout();
            }
        });
        getScene().registerUpdateHandler(hudTimeout);
    }

    private void onHudTimeout() {
        Log.d("PlayerHud", String.format("Hiding Hud now on thread %s", Thread.currentThread().getName()));
        hideHud();
        getScene().unregisterUpdateHandler(hudTimeout);
        hudTimeout = null;
    }




}
