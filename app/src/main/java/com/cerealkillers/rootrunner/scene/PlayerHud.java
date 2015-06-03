package com.cerealkillers.rootrunner.scene;

import android.util.Log;

import com.cerealkillers.rootrunner.Game;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.Shape;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.color.Color;

/**
 * Created by Benjamin Daschel on 6/1/15.
 */
public class PlayerHud extends SceneDecorator<BaseScene>{

    private static final int TERMINAL_HEIGHT = 48;
    private static final int STEALTH_BAR_HEIGHT = 48;
    private static final int STEALTH_BAR_WIDTH = 136;
    private static final int STEALTH_BAR_INCREMENT_WIDTH = 16;
    private static final int STEALTH_BAR_INCREMENT_PADDING = 8;
    private Text terminalText;
    private Rectangle terminal;
    private Font terminalFont;
    private Text messageText;
    private TimerHandler hudTimeout;
    private Entity stealthbar;
    private Text stealthBarText;

    public PlayerHud(BaseScene scene) {
        super(scene);
        createHud(scene);
    }

    private void createHud(BaseScene scene) {
        HUD playerHud = new HUD();

        //set up message terminal
        terminal = new Rectangle(0, 0, scene.boundCamera.getWidth(), TERMINAL_HEIGHT, scene.vertexBufferObjectManager);
        terminal.setColor(org.andengine.util.color.Color.BLACK);
        terminalFont = scene.resourceManager.createFont("clacon.ttf", 32, Color.GREEN.getABGRPackedInt());
        terminalText = new Text(8, 8, terminalFont, ">>>", scene.vertexBufferObjectManager);

        terminal.attachChild(terminalText);
        playerHud.attachChild(terminal);
        terminal.setVisible(false);

        //set up stealth bar
        float stealthBarYPos = scene.boundCamera.getHeight() - STEALTH_BAR_HEIGHT - 8;
        stealthbar = new Rectangle(0, stealthBarYPos ,STEALTH_BAR_WIDTH , STEALTH_BAR_HEIGHT, scene.vertexBufferObjectManager);
        stealthbar.setColor(Color.TRANSPARENT);
        Font stealthBarFont = scene.resourceManager.createFont("clacon.ttf", 24, Color.GREEN.getABGRPackedInt());
        stealthBarText = new Text(8, 0, stealthBarFont, "Stealth", scene.vertexBufferObjectManager);
        stealthbar.attachChild(stealthBarText);
        playerHud.attachChild(stealthbar);

        //todo remove this line
        updatePlayerStealth(5);

        //finish setup process
        scene.boundCamera.setHUD(playerHud);
    }

    public void setMessage(String message){
        if(message != null){
            if(messageText!= null){
                messageText.detachSelf();
            }
            TextOptions options = new TextOptions(AutoWrap.WORDS, terminal.getWidth() - terminalText.getWidth() - 8);
            messageText = new Text(terminalText.getWidth() + 8, 8, terminalFont, message, options, getScene().vertexBufferObjectManager);
            showHud();
            adjustTerminalHeight(messageText.getHeight());
            terminal.attachChild(messageText);
            hideHudAfterTime(5);
        }
    }

    private void adjustTerminalHeight(float height) {
        terminal.setHeight(height + 16);
    }

    public void showHud(){
        terminal.setVisible(true);
    }

    public void hideHud(){
        terminal.setVisible(false);
    }

    public void updatePlayerStealth(int stealth){
        if(stealth > 5){
            stealth = 5;
        }
        Color color = org.andengine.util.color.Color.GREEN;
        if(stealth <= 1){
            color = Color.RED;
        }else if(stealth < 4 ){
            color = Color.YELLOW;
        }
        stealthbar.detachChildren();
        for (int i = 0; i < stealth; i++){
            float barPosition = (STEALTH_BAR_INCREMENT_PADDING * i) + (STEALTH_BAR_INCREMENT_WIDTH * i) + STEALTH_BAR_INCREMENT_PADDING;
            Rectangle bar = new Rectangle(barPosition, STEALTH_BAR_HEIGHT - 16, STEALTH_BAR_INCREMENT_WIDTH, 16, getScene().vertexBufferObjectManager);
            bar.setColor(color);
            stealthbar.attachChild(bar);
        }
        stealthbar.attachChild(stealthBarText);
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
