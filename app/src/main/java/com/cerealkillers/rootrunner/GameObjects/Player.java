package com.cerealkillers.rootrunner.GameObjects;

import com.cerealkillers.rootrunner.GameWorld.Map;
import com.cerealkillers.rootrunner.scene.BaseScene;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;

/**
 * Created by Benjamin Daschel on 5/29/15.
 */
public class Player extends MapObject<AnimatedSprite> {

    private final PhysicsHandler mPhysicsHandler;
    private IControl mPlayerControls;
    private BaseScene myScene;

    public interface PlayerSpawnedListener{
        public void onPlayerSpawned(Player player);
    }

    public Player(int id, AnimatedSprite sprite) {
        super(id, sprite);
        mPlayerControls = new PlayerControl();
        mPhysicsHandler = new PhysicsHandler(sprite);
        sprite.registerUpdateHandler(mPhysicsHandler);
    }

    @Override
    public void onAttachToMap(Map attached) {
        super.onAttachToMap(attached);
        setCollisionDetector(new MapObjectCollisionDetector(this, attached));
        attached.onPlayerSpawned(this);
        myScene = attached.getBaseScene();
    }

    public IControl getPlayerControls(){
        return mPlayerControls;
    }


    private class PlayerControl implements IControl{
        Direction lastDirection = Direction.NONE;
        @Override
        public void onMove(Direction direction) {

            AnimatedSprite playerSprite = getSprite();
            switch (direction){
                case UP:
                    if (lastDirection != Direction.UP) {
                        playerSprite.animate(new long[]{100,100,100},6,8,true);
                    }
                    break;
                case RIGHT:
                    if (lastDirection != Direction.RIGHT) {
                        playerSprite.animate(new long[]{100,100,100},3,5,true);
                    }
                    break;
                case DOWN:
                    if (lastDirection != Direction.DOWN) {
                        playerSprite.animate(new long[]{100,100,100},0,2,true);
                    }
                    break;
                case LEFT:
                    if (lastDirection != Direction.LEFT) {
                        playerSprite.animate(new long[]{100,100,100},9,11,true);
                    }
                    break;
                default:
                    playerSprite.stopAnimation();
            }
            mPhysicsHandler.setVelocity(direction.x*100, direction.y*100);
            lastDirection = direction;
            adjustToSceneBinding(playerSprite);
        }
    }

    public void adjustToSceneBinding(AnimatedSprite player) {
        // Correct the X Boundaries.
        if (player.getX() < 0) {
            player.setX(0);
        } else if (player.getX() + player.getWidth() > myScene.getLayerWidth()) {
            player.setX(myScene.getLayerWidth() - player.getWidth());
        }else if(player.getY() < 0){
            player.setY(0);
        }else if (player.getY() + player.getHeight() > myScene.getLayerHeight()){
            player.setY(myScene.getLayerHeight() - player.getHeight());

        }
    }

}
