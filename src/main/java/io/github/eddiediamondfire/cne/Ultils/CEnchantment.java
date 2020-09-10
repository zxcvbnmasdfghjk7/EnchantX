package io.github.eddiediamondfire.cne.Ultils;

import io.github.eddiediamondfire.cne.Enchantments.Weapons.*;
import org.bukkit.ChatColor;
import org.bukkit.Keyed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.*;

public class CEnchantment implements Listener {

    // Enchantments
    // To steal enemys health
    public static Enchantment LIFESTEAL = new LifeSteal();
    // Causes an Explosion when hitting an enemy
    public static Enchantment EXPLOSION = new Explosion();

    // Strikes Lightning on an Enemy
    public static Enchantment THUNDERLORD = new ThunderLord();

    // Has a good chance of one shotting an enemy
    public static Enchantment ONESHOT = new OneShot();

    // Summons Guards when a enemy attacks or a player attacks a enemy
    public static Enchantment VANGUARD = new Vanguard();

    // Pickpockets cash from a Victum
    public static Enchantment PICKPOCKET = new PickPocket();

    @SuppressWarnings("unchecked")
    public static void unregisterEnchantments(Enchantment enchantment) {
        try {
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byKeyField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Keyed, Enchantment> byKey = (HashMap<Keyed, Enchantment>) byKeyField.get(null);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            byName.remove(enchantment.getKey());
            byKey.remove(enchantment.getKey());

            if (byKey.containsKey(enchantment.getKey())) {
                byKey.remove(enchantment.getKey());
            }

            if(byName.containsKey(enchantment.getKey())){
                byName.remove(enchantment.getKey());
            }
        } catch (Exception ignored) {
        }
    }

    public static void registerEnchantments(Enchantment enchantment) {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Enchantment.registerEnchantment(enchantment);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Use ChatColor.Grey + EnchantMechanics.returnEnchantmentName(enchantment, enchantlevel)
    private static final String[] NUMERALS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
    public static String returnEnchantmentName(Enchantment ench, int enchLevel){
        if(enchLevel == 1 && ench.getMaxLevel() == 1){
            return ench.getName();
        }
        if(enchLevel > 10 || enchLevel <= 0){
            return ench.getName() + " enchantment.level." + enchLevel;
        }
        return ench.getName() + " " + NUMERALS[enchLevel- 1];
    }

    // ItemMeta#hasEnchant() may also work for this function
    public static boolean hasEnchantment(ItemStack item, Enchantment enchant){
        if(item.getItemMeta() != null) {
            item.getItemMeta().getEnchants();
            if (item.getItemMeta().getEnchants().size() > 0) {
                for (Iterator<Map.Entry<Enchantment, Integer>> it = item.getItemMeta().getEnchants().entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<Enchantment, Integer> e = it.next();
                    if (e.getKey().equals(enchant)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // ItemMeta#getEnchantLevel() may also work for this function
    public static int getLevel(ItemStack item, Enchantment enchant){
        if(item.getItemMeta() != null) {
            item.getItemMeta().getEnchants();
            if (item.getItemMeta().getEnchants().size() > 0) {
                for (Iterator<Map.Entry<Enchantment, Integer>> it = item.getItemMeta().getEnchants().entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<Enchantment, Integer> e = it.next();
                    if (e.getKey().equals(enchant)) {
                        return e.getValue();
                    }
                }
            }
        }
        return 0;
    }

    public void applyEnchantmentToItem(Player player, Enchantment enchantment, int level, ChatColor colour){
        try{
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();

            item.addEnchantment(enchantment, level);
            lore.add(colour + CEnchantment.returnEnchantmentName(enchantment, level));
            meta.setLore(lore);
            item.setItemMeta(meta);
        }catch (NumberFormatException e){
        }catch (Exception e){
        }
    }

}
