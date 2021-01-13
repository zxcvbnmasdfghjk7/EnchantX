package io.github.eddiediamondfire.cne.events;

import io.github.eddiediamondfire.cne.enchantments.core.CEnchantment;
import io.github.eddiediamondfire.cne.CNE;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Weapons implements Listener {
    private final CNE plugin;
    public Weapons(CNE plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player)
        {
            Player player = (Player) event.getDamager();
            Entity entity = event.getEntity();

            if(plugin.getEnchantment().hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE))
            {
                if(entity instanceof Player){
                    double chance = plugin.getEnchantment().getLevel(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE) * .25;
                    double random = Math.random();

                    if(chance > random){
                        plugin.getEnchantment().getCustomEntity().spawnCustomChargedCreeper(player, player.getLocation(), entity);
                        if(plugin.getEnchantment().getLevel(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE) == 1){
                            player.getWorld().createExplosion(entity.getLocation(), 0.01f);
                        }
                    }
                }
                if(plugin.getEnchantment().getLevel(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE) == 1){
                    double chance = plugin.getEnchantment().getLevel(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE) * .25;
                    double random = Math.random();

                    if(chance > random)
                    {
                        plugin.getEnchantment().getCustomEntity().spawnCustomChargedCreeper(player, player.getLocation(), entity);
                    }
                }
            }
        }
    }
}
