package io.github.eddiediamondfire.cne;

import io.github.eddiediamondfire.cne.Commands.MainCMDManager;
import io.github.eddiediamondfire.cne.Listener.PlayerEvents;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import io.github.eddiediamondfire.cne.Listener.EnchantmentEvents.Weapons;
import io.github.eddiediamondfire.cne.Wrapper.CustomEnchantWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CNE extends JavaPlugin {
    public static CNE instance;
    public static CNE getInstance(){
        return instance;
    }

    public static CustomEnchantWrapper customEnchantWrapper;
    private MainCMDManager mainCmdManagerCommand;

    public static String PREFIX = ChatColor.GRAY + "[" + ChatColor.GOLD + "Custom" + ChatColor.YELLOW + " N' " + ChatColor.GOLD + "Enchanting" + ChatColor.GRAY + "]";

    @Override
    public void onEnable() {
        instance = this;
        this.instanceClasses();
        this.loadListeners();
        this.registerEnchantments();
        this.loadConfig();
        mainCmdManagerCommand.loadCommands();

    }

    @Override
    public void onDisable() {
        this.unregisterEnchantment();
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void loadListeners(){
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new CEnchantment(), this);
        pm.registerEvents(new Weapons(), this);
        pm.registerEvents(new PlayerEvents(), this);
    }

    private void instanceClasses(){
        mainCmdManagerCommand = new MainCMDManager();
    }

    private void registerEnchantments(){
        CEnchantment.registerEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.EXPLOSION);
        CEnchantment.registerEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.THUNDERLORD);
        CEnchantment.registerEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.ONESHOT);
        CEnchantment.registerEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.LIFESTEAL);

        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + ChatColor.GREEN + " Enchantments Successfully Enabled");
    }

    private void unregisterEnchantment(){
        CEnchantment.unregisterEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.EXPLOSION);
        CEnchantment.unregisterEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.THUNDERLORD);
        CEnchantment.unregisterEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.ONESHOT);
        CEnchantment.unregisterEnchantments(io.github.eddiediamondfire.cne.Ultils.CEnchantment.LIFESTEAL);

        Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + ChatColor.RED + " Enchantments Successfully Disabled");
    }
}
