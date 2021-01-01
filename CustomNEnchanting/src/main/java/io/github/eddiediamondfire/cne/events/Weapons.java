package io.github.eddiediamondfire.cne.events;

import io.github.eddiediamondfire.cne.CEnchantment;
import io.github.eddiediamondfire.cne.CNE;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Weapons implements Listener {
    private final CNE plugin;
    public Weapons(CNE plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Entity entity = event.getEntity();

        ItemStack playersItemInMainHand = player.getInventory().getItemInMainHand();
        if(CEnchantment.hasEnchantment(playersItemInMainHand, CEnchantment.EXPLOSIVE)){
            if(CEnchantment.getLevel(playersItemInMainHand, CEnchantment.EXPLOSIVE) == 1){
                player.getWorld().createExplosion(entity.getLocation(), 1.0F);
            }
        }
    }
}
