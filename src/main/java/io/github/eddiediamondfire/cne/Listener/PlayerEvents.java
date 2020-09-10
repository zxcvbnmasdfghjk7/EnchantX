package io.github.eddiediamondfire.cne.Listener;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerEvents implements Listener {
    public CNE plugin = CNE.getInstance();

    public void playerOnJoin(PlayerJoinEvent event){
        Player player  = event.getPlayer();

        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(CEnchantment.returnEnchantmentName(CEnchantment.EXPLOSION, 3));
        item.addUnsafeEnchantment(CEnchantment.EXPLOSION, 3);
        assert itemMeta != null;
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        player.getInventory().addItem(item);
    }
}
