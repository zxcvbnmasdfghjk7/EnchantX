package io.github.eddiediamondfire.cne.ItemHandler;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomItem {

    private Material material;
    private ItemStack itemStack;
    private final List<String> itemLore = new ArrayList<String>();
    private ItemFlag flag;
    private ItemMeta meta;

    public abstract Material getMaterial();


}
