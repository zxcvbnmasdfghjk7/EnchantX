package io.github.eddiediamondfire.cne.enchantments;

import io.github.eddiediamondfire.cne.wrapper.Enchant;
import io.github.eddiediamondfire.cne.wrapper.CustomEnchantWrapper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Explosive extends CustomEnchantWrapper implements Enchant {

    public Explosive(){
        super("explosive");
    }
    @Override
    public String getName() {
        return "Explosive";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
