package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.AnimatedSprite;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class Player extends MapObject<AnimatedSprite> {

    private IControl mPlayerControls;

    public interface PlayerSpawnedListener{
        public void onPlayerSpawned(Player player);
    }

    public Player(int id, AnimatedSprite sprite) {
        super(id, sprite);
        mPlayerControls = new PlayerControl();
    }

    @Override
    public void onAttachToMap(Map attached) {
        super.onAttachToMap(attached);
        setCollisionDetector(new MapObjectCollisionDetector(this, attached));
        attached.onPlayerSpawned(this);
    }

}
