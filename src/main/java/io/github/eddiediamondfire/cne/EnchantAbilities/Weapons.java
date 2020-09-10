package io.github.eddiediamondfire.cne.EnchantAbilities;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.CooldownManager;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class Weapons implements Listener{
    public CNE plugin = JavaPlugin.getPlugin(CNE.class);
    // Swords
    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Player player = (Player) e.getDamager();

        UUID playerUUID = player.getUniqueId();
        PlayerInventory playerInventory = player.getInventory();
        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSION)){
            long EXPLOSION_COOLDOWN = 12;
            long timeLeft = (System.currentTimeMillis() - CooldownManager.getCooldown(player.getUniqueId()));
            if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= EXPLOSION_COOLDOWN){
                if(CEnchantment.getLevel(playerInventory.getItemInMainHand(), CEnchantment.EXPLOSION) == 1){
                    player.getWorld().createExplosion(entity.getLocation(), 1.0F, false, false);
                }
                if(CEnchantment.getLevel(playerInventory.getItemInMainHand(), CEnchantment.EXPLOSION) == 2){
                    player.getWorld().createExplosion(entity.getLocation(), 3.0F, false, false);
                }
                if(CEnchantment.getLevel(playerInventory.getItemInMainHand(), CEnchantment.EXPLOSION) == 3){
                    player.getWorld().createExplosion(entity.getLocation(), 5.0F, false, false);

                }
                CooldownManager.setCooldowns(playerUUID, System.currentTimeMillis());
            }else{
                player.sendMessage("");
            }
        }

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.THUNDERLORD)){
            entity.getWorld().strikeLightning(entity.getLocation());
        }

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.LIFESTEAL)){
        }

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.ONESHOT)){
        }

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.VANGUARD)){
        }


    }
 }