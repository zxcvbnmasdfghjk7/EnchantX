package io.github.eddiediamondfire.enchantx.enchants;

import io.github.eddiediamondfire.enchantx.enchants.wrapper.CustomEnchantWrapper;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Pern extends CustomEnchantWrapper {

    public Pern(){
        super("pern");
    }

    @NotNull
    @Override
    public String getName() {
        return "Pern";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @NotNull
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
    public boolean conflictsWith(@NotNull Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return true;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return null;
    }
}
