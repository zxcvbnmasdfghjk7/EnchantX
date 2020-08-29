package io.github.eddiediamondfire.cne.EnchantAbilities;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Weapons implements Listener{
    public HashMap<UUID, Long> explosionEnchantmentCooldown = new HashMap<>();
    public int commonCooldownTime = 10;
    public CNE plugin = JavaPlugin.getPlugin(CNE.class);

    // Swords
    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Player player = (Player) e.getDamager();

        if(CEnchantment.hasEnchantment(player.getInventory().getItemInMainHand(), CEnchantment.EXPLOSION)){
            if(explosionEnchantmentCooldown.containsKey(player.getUniqueId())){
                long secondsleft = ((explosionEnchantmentCooldown.get(player.getUniqueId()) / 1000) + commonCooldownTime) - (System.currentTimeMillis() / 1000);

                if(secondsleft > 0){
                    //TODO
                }
            }else{
                player.sendMessage(ChatColor.GREEN + "You have used the Explosion Enchant Ability!");
                player.getWorld().createExplosion(entity.getLocation(),1.0F, false, false);
                explosionEnchantmentCooldown.put(player.getUniqueId(), System.currentTimeMillis());
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