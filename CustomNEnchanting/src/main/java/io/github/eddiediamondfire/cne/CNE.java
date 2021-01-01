package io.github.eddiediamondfire.cne;

import io.github.eddiediamondfire.cne.events.Weapons;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CNE extends JavaPlugin {
    private final CEnchantment enchantment = new CEnchantment(this);
    public CNE(){

    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Weapons(this), this);
        getEnchantment().onServerEnable();
    }

    @Override
    public void onDisable() {
        getEnchantment().onServerDisable();
    }

    public CEnchantment getEnchantment() {
        return enchantment;
    }
}
