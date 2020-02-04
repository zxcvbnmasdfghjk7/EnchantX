package io.github.eddiediamondfire.cne.Listener.EnchantmentEvents;

import io.github.eddiediamondfire.cne.CoolDowns.CoolDownManager;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Weapons implements Listener{
    public CoolDownManager cd = new CoolDownManager();
    // Swords
    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Player player = (Player) e.getDamager();

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSION)){
            long timeleft = System.currentTimeMillis() - cd.getCooldown(player.getUniqueId());
            if(TimeUnit.MILLISECONDS.toSeconds(timeleft) >= CoolDownManager.EXPLOSION_COOLDOWN){
                if(CEnchantment.getLevel(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSION) == 1){
                    player.sendMessage(ChatColor.GREEN + "You have used the Explosion Enchant Ability!");
                    player.getWorld().createExplosion(entity.getLocation(),1.0F, false, false);
                }
                cd.setCooldown(player.getUniqueId(), System.currentTimeMillis());
            }else{
                player.sendMessage(ChatColor.RED + "You are under cooldown for " + ChatColor.RED.toString() + (TimeUnit.MILLISECONDS.toSeconds(timeleft) - CoolDownManager.EXPLOSION_COOLDOWN) + ChatColor.RED + " seconds left");
            }
        }
    }
 }