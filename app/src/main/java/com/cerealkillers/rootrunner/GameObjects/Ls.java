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
     * The Ls constructor.
     * @param sprite The associated sprite.
     */
    public Ls(Sprite sprite)
    {
        super(sprite, "ls", "Lists information about a file.");
    }

    /**
     * List all visible files in the current map.
     * @param map The current map.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public void use(Map map, float x, float y)
    {
        Log.d("ls -l", "detected ls tool use");
        String files = getFiles(map);

        CommandFacade.displayMessage(files);
    }

    /**
     * Gets a string of all the visible files.
     * @param map The current map.
     * @return a String of visible files.
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
