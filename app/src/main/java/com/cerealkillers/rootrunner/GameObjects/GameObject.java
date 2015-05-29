package com.cerealkillers.rootrunner.GameObjects;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public abstract class GameObject
{
    private int mId;
    private Sprite mSprite;

    public GameObject(int id, Sprite sprite)
    {
        mId = id;
        mSprite = sprite;
    }

    public Sprite getSprite(){
        return mSprite;
    }

}
