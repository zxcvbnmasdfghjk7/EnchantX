package io.github.eddiediamondfire.cne;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.github.eddiediamondfire.cne.commands.CommandManager;
import io.github.eddiediamondfire.cne.dependencies.Dependency;
import io.github.eddiediamondfire.cne.dependencies.VaultAPI;
import io.github.eddiediamondfire.cne.enchantments.core.CEnchantment;
import io.github.eddiediamondfire.cne.events.Weapons;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CNE extends JavaPlugin {
    private @Getter final CEnchantment enchantment = new CEnchantment(this);
    private @Getter List<Dependency> dependencies;
    private @Getter  final CommandManager commandManager;
    public CNE(){
        dependencies = new ArrayList<>();
        this.commandManager = new CommandManager(this);
        dependencies.add(new VaultAPI(this));
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        getEnchantment().onServerEnable();
        getServer().getPluginManager().registerEvents(new Weapons(this), this);
        getCommand("cne").setExecutor(new CommandManager(this));
        getCommand("cne").setTabCompleter(new CommandManager(this));

        try{
            for(int i = 0; i < dependencies.size(); i++){
                dependencies.get(i).onEnable();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        getEnchantment().onServerDisable();
        try{
            for(int i = 0; i < dependencies.size(); i++){
                dependencies.get(i).onDisable();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
