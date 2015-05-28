package com.cerealkillers.rootrunner.GameObjects.Characters;

import com.cerealkillers.rootrunner.GameObjects.GameObject;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public abstract class GameCharacter extends GameObject
{
    private int health;
    public GameCharacter(int id, int health)
    {
        super(id);
        this.health = health;
    }

    /**
     *
     * @param change the amount of change in health. Nehative values decrement, positive values increment.
     */
    public void setHealth(int change)
    {
        this.health = health + change;
        //TODO Implement protections against invalid health values.
    }
}
