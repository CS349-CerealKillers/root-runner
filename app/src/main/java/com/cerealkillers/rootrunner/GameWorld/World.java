package com.cerealkillers.rootrunner.GameWorld;

import com.cerealkillers.rootrunner.GameObjects.Player;
import com.cerealkillers.rootrunner.SceneManager;
import com.cerealkillers.rootrunner.command.CommandExecutor;
import com.cerealkillers.rootrunner.command.GameCommand;
import com.cerealkillers.rootrunner.scene.BaseScene;
import com.cerealkillers.rootrunner.scene.ControlScene;
import com.cerealkillers.rootrunner.scene.PlayerHud;

/**
 * Created by Benjamin Daschel on 5/25/15.
 * This class is at the root of all the in game models.
 * The world contains maps, which contain items and characters.
 */
public class World implements CommandExecutor<World>{

    private final SceneManager mSceneManager;
    private Map mCurrentMap;
    private MapLoader mMapLoader;
    private boolean initialized;
    private Player mPlayer;
    private ControlScene mControlScene;
    private PlayerHud mPlayerHud;

    public World(MapLoader mapLoader, SceneManager sceneManager){
        mMapLoader = mapLoader;
        mSceneManager = sceneManager;
    }

    /**
     * Spawns the initial map for the game and populates the scene with game objects.
     * Only needs to be called once per world lifetime.
     */
    public void initialize(){
        synchronized (this){
            if(initialized){
                return;
            }
            initialized = true;
        }

        /*
            TODO: in the future, this should load up the last saved level that the player
            was at. Right now, we will always start the game from the beginning.
         */
        initializeMap("newtmx.tmx");
        CommandFacade.registerCommandExecutor(this);
    }

    private void initializeMap(String mapName){
        BaseScene gameScene = mSceneManager.getCurrentScene();
        mControlScene = new ControlScene(gameScene);
        mPlayerHud = new PlayerHud(gameScene);
        mCurrentMap = mMapLoader.load(mapName, gameScene);
        mCurrentMap.onAttach(this);
        mCurrentMap.spawnPlayer(mPlayer);
        createControls();
    }

    private void unloadMap(){
        mCurrentMap.onDetach();
    }

    private void createControls() {
        mControlScene.bindControls(mPlayer.getPlayerControls());
    }


    public void setPlayer(Player player) {
        mPlayer = player;
    }

    public PlayerHud getPlayerHud(){
        return mPlayerHud;
    }

    public void runCommand(GameCommand<World> command){
        if(command != null){
            command.run(this);
        }
    }
}
