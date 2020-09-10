package io.github.eddiediamondfire.cne.Ultils;

import org.bukkit.enchantments.Enchantment;

public enum Enchantments {

    LIFESTEAL("lifesteal", CEnchantment.LIFESTEAL),
    EXPLOSION("explosion", CEnchantment.EXPLOSION),
    THUNDERLORD("thunderlord", CEnchantment.THUNDERLORD),
    ONESHOT("oneshot", CEnchantment.ONESHOT),
    VANGUARD("vanguard", CEnchantment.VANGUARD),
    PICKPOCKET("pickpocket", CEnchantment.PICKPOCKET);

    Enchantments(String enchantmentNames, Enchantment enchantment){
        this.enchantment =enchantment;
        this.enchantmentNames = enchantmentNames;
    }
    private final String enchantmentNames;
    private final Enchantment enchantment;



}
