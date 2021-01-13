package io.github.eddiediamondfire.cne.dependencies;

import io.github.eddiediamondfire.cne.CNE;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultAPI implements Dependency{
    private CNE plugin;
    public static Economy economy;
    public VaultAPI(CNE plugin){
        this.plugin = plugin;
    }


    @Override
    public void onEnable() {

        /*
        if(plugin.getServer().getPluginManager().getPlugin("Vault") == null){
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vault is not found, disabling plugin");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
        if(!setupEconomyVault()){
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Vault is not found, disabling plugin");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }

         */
    }

    @Override
    public void onDisable() {

    }

    private boolean setupEconomyVault(){
        RegisteredServiceProvider<Economy> economyRegisteredServiceProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if(economyRegisteredServiceProvider != economy){
            economy = economyRegisteredServiceProvider.getProvider();
        }
        return (economy != null);
    }
}
