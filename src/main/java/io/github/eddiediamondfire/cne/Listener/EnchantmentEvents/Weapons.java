package io.github.eddiediamondfire.cne.Listener.EnchantmentEvents;

import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Weapons implements Listener{
    // Swords
    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Player player = (Player) e.getDamager();

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSION)){
            player.sendMessage(ChatColor.GREEN + "You have used the Explosion Enchant Ability!");
            player.getWorld().createExplosion(entity.getLocation(),1.0F, false, false);
        }

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.THUNDERLORD)){
            entity.getWorld().strikeLightning(entity.getLocation());
        }
    }
 }