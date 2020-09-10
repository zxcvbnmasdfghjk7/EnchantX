package io.github.eddiediamondfire.cne;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.ChatColor;

public class DependencyChecker {

    public static StateFlag EXPLOSION_ENCHANTMENT_FLAG;

    public CNE plugin = CNE.getInstance();

    protected void loadDependency() {
        this.loadWorldGuardDependency();
    }

    private void loadWorldGuardDependency(){
        if(!plugin.getServer().getPluginManager().isPluginEnabled("Worldguard")){
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Worldguard is not installed, disabling Worldguard comparability");

        }
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try{
            StateFlag enchantment_explosion_flag = new StateFlag("explosion-enchant", true);
            registry.register(enchantment_explosion_flag);
        }catch (FlagConflictException e){
            Flag<?> existing = registry.get("explosion-enchant");
            if(existing instanceof StateFlag){
                EXPLOSION_ENCHANTMENT_FLAG = (StateFlag) existing;
            }else{
                // TODO
            }
        }
    }






}
