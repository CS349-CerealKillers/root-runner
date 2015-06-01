package com.cerealkillers.rootrunner.scene;

import android.opengl.GLES20;

import com.cerealkillers.rootrunner.Game;
import com.cerealkillers.rootrunner.GameObjects.IControl;
import com.cerealkillers.rootrunner.ResourceManager;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Benjamin Daschel on 6/1/15.
 */
public class ControlScene extends SceneDecorator<BaseScene>{

    private DigitalOnScreenControl digitalOnScreenControl;
    private IControl mControlListener;

    public ControlScene(BaseScene scene){
        super(scene);
        initDOSC();
    }

    public void bindControls(IControl controls){
        mControlListener = controls;
    }

    private void updateControls(IControl.Direction direction){
        if (mControlListener != null){
            mControlListener.onMove(direction);
        }
    }

    /* initialize the digital on screen controls */
    private void initDOSC() {
        BoundCamera boundCamera = getScene().boundCamera;
        ResourceManager resourceManager = getScene().resourceManager;
        VertexBufferObjectManager vertexBufferObjectManager = getScene().vertexBufferObjectManager;
        digitalOnScreenControl = new DigitalOnScreenControl(boundCamera.getWidth() - (resourceManager.onScreenControlBaseRegion.getWidth() + 40),
                boundCamera.getHeight() - resourceManager.onScreenControlBaseRegion.getHeight(),
                boundCamera, resourceManager.onScreenControlBaseRegion, resourceManager.onScreenControlKnobRegion, 0.1f,
                vertexBufferObjectManager, new BaseOnScreenControl.IOnScreenControlListener() {
            @Override
            public void onControlChange(BaseOnScreenControl baseOnScreenControl, float v, float v2) {
                IControl.Direction playerDirection = null;
                if(v2 == 1) {
                    playerDirection = IControl.Direction.UP;
                } else if(v2 == -1) {
                    playerDirection = IControl.Direction.DOWN;
                } else if(v == -1) {
                    playerDirection = IControl.Direction.LEFT;
                } else if(v == 1) {
                    playerDirection = IControl.Direction.RIGHT;
                } else {
                    playerDirection = IControl.Direction.NONE;
                }

                updateControls(playerDirection);
            }
        });

        this.digitalOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        this.digitalOnScreenControl.getControlBase().setAlpha(0.5f);
        this.digitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
        this.digitalOnScreenControl.getControlBase().setScale(1.25f);
        this.digitalOnScreenControl.getControlKnob().setScale(1.25f);
        this.digitalOnScreenControl.refreshControlKnobPosition();

        getScene().setChildScene(digitalOnScreenControl);
    }
}
