package com.cerealkillers.rootrunner.GameObjects;

import android.util.Log;

import com.cerealkillers.rootrunner.GameWorld.CommandFacade;
import com.cerealkillers.rootrunner.GameWorld.Map;

import org.andengine.entity.sprite.Sprite;

import java.util.List;

/**
 * Created by Tyler Herrin on 6/5/2015.
 */
public class Ls extends MapTool
{
    /**
     *
     * @param sprite
     */
    public Ls(Sprite sprite)
    {
        super(sprite, "ls", "Lists information about a file.");
    }

    /**
     *
     * @param map
     * @param x
     * @param y
     */
    public void use(Map map, float x, float y)
    {
        Log.d("ls -l", "detected ls tool use");
        String files = getFiles(map);

        CommandFacade.displayMessage(files);
    }

    /**
     *
     * @param map
     * @return
     */
    protected String getFiles(Map map)
    {
        List<MapObject> visibleObjects = map.findByTag("visible");
        String files = "";

        for(MapObject m : visibleObjects)
        {
            Tag locked = m.getTag("locked");
            if(locked != null && locked.value != null)
            {
                files += locked.value + " " + m.getName() + "\n";
            }
        }

        return files;
    }
}
