package com.cerealkillers.rootrunner.GameObjects.Characters;

/**
 * Created by Tyler Herrin on 5/14/2015.
 */
public class Player extends GameCharacter implements SpecialBehavior
{
    private SpecialBehavior special;

    public Player(int id, int health, SpecialBehavior special) {
        super(id, health);
        this.special = special;
    }

    public void useSpecial()
    {
        this.special.useSpecial();
    }
}
