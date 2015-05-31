package com.cerealkillers.rootrunner.GameObjects;

/**
 * Created by Benjamin Daschel on 5/30/15.
 */
public class Tag {

    public final String key;
    public final String value;

    public Tag(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass().equals(this.getClass())){
            Tag otherTag = (Tag) o;
            if (this.key != null && otherTag.key != null){
                return this.key.equals(otherTag.key);
            }
        }
        return false;
    }

}
