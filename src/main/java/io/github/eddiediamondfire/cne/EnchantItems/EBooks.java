package io.github.eddiediamondfire.cne.EnchantItems;

import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EBooks {

    public ItemStack giveEnchantmentBooks_SIMPLE(Player player, Enchantment enchantment, int level){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.GRAY + "Simple Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
        return item;
    }

    public ItemStack giveEnchantmentBooks_COMMON(Player player, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.GREEN + "Common Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
        return item;
    }

    public void giveEnchantmentBooks_UNCOMMON(Player player, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.DARK_GREEN + "Uncommon Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
    }

    public void giveEnchantmentBooks_RARE(Player player, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.DARK_BLUE + "Rare Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
    }

    public void giveEnchantmentBooks_EPIC(Player player, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.DARK_PURPLE + "Epic Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
    }

    public void giveEnchantmentBooks_LEGENDARY(Player player, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.GOLD + "Legendary Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
    }

    public void giveEnchantmentBooks_GODLY(Player player, Enchantment enchantment, int level) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.DARK_RED + "Godly Enchantment Book");
        lore.add(ChatColor.RESET + "" + ChatColor.GRAY + CEnchantment.returnEnchantmentName(enchantment, level));
        item.addUnsafeEnchantment(enchantment, level);

        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItem(1, item);
    }
}
