package io.github.eddiediamondfire.cne.events;

import io.github.eddiediamondfire.cne.CEnchantment;
import io.github.eddiediamondfire.cne.CNE;
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

public class Weapons implements Listener {
    private final CNE plugin;
    public Weapons(CNE plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        Entity entity = event.getEntity();

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE)){
            if(CEnchantment.getLevel(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSIVE) == 1){
                player.getWorld().createExplosion(entity.getLocation(), 5.0F);
            }
        }
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(CEnchantment.returnEnchantmentName(CEnchantment.EXPLOSIVE, 1));
        item.addUnsafeEnchantment(CEnchantment.EXPLOSIVE, 1);
        meta.setLore(lore);

        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }
}
