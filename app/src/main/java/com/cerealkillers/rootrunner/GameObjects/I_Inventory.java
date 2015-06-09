package com.cerealkillers.rootrunner.GameObjects;

import java.util.List;

/**
 * Created by Benjamin Daschel on 6/3/15.
 */
public interface I_Inventory {

    public interface PlayerInventoryListener{
        public void onInventoryChanged(I_Inventory inventory);
    }

    public List<InventoryItem> getInventory();

    public void addItem(InventoryItem item);

    public void removeItem(InventoryItem item);

    public List<InventoryItem> findByTag(Tag tag);
}
