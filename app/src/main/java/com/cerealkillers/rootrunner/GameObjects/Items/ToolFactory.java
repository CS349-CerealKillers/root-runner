package com.cerealkillers.rootrunner.GameObjects.Items;

import org.andengine.extension.tmx.TMXObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Benjamin Daschel on 5/24/15.
 */
public class ToolFactory {

    public static Item itemfromTmxObject(TMXObject tmxObject) {

        /*
            Tools only have on constructor that takes an int id.
         */
        Package currentPackage = ToolFactory.class.getPackage();
        String packagePrefix = currentPackage.getName();
        String className = tmxObject.getTMXObjectProperty("class");
        String fullClassName = String.format("%s.%s", packagePrefix, className);
        try {
            Class toolClass = Class.forName(fullClassName);
            Constructor[] constructors = toolClass.getConstructors();

            for(Constructor constructor: constructors){
                Class[] parameterTypes = constructor.getParameterTypes();
                // We are going to assume that it takes an int
                if (parameterTypes.length == 1){
                    return (Tool) constructor.newInstance(tmxObject.getId());
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
