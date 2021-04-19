package io.github.eddiediamondfire.enchantx.enchants.wrapper;

import io.github.eddiediamondfire.enchantx.EnchantX;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public abstract class CustomEnchantWrapper extends Enchantment {

    public CustomEnchantWrapper(String key) {
        super(new NamespacedKey(EnchantX.plugin, key));
    }


}
