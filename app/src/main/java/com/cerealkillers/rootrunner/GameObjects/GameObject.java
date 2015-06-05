package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.Game;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public abstract class GameObject <S extends Sprite>
{
    private S mSprite;
    private List<Tag> mTags;
    private String mName;

    public GameObject(S sprite)
    {
        mSprite = sprite;
    }

    public S getSprite(){
        return mSprite;
    }

    public void addTag(Tag tag){
        if(tag != null){
            initTags();
            if(! mTags.contains(tag)){
                mTags.add(tag);
            }
        }
    }

    public List<Tag> getTags(){
        initTags();
        return mTags;
    }

    public void removeTag(Tag tag){
        if(tag != null){
            initTags();
            mTags.remove(tag);
        }
    }

    /**
     * Get a tag by the key
     * @param key key to search tags by
     * @return tag or null if tag not found
     */
    public Tag getTag(String key){
        if(key != null){
            initTags();
            for(Tag tag: mTags){
                if(tag.key.equals(key)){
                    return tag;
                }
            }
        }
        return null;
    }

    private void initTags(){
        if(mTags == null){
            mTags = new ArrayList<>();
        }
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
