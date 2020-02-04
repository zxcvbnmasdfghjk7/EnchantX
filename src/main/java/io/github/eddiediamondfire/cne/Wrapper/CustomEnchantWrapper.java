package io.github.eddiediamondfire.cne.Wrapper;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public abstract class CustomEnchantWrapper extends Enchantment implements Keyed {

    public CustomEnchantWrapper(String key){
        super(NamespacedKey.minecraft(key));
    }
}
