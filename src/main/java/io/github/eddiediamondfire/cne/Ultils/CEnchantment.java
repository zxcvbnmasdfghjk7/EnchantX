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

    /**
    * Steal a entity's health and add to the player
     */
    public static Enchantment LIFESTEAL = new LifeSteal();
    /**
    *  Causes an Explosion when applied to an entity
     */
    public static Enchantment EXPLOSION = new Explosion();

    /**
    * To strike lightening on the entity when the method is used
     */
    public static Enchantment THUNDERLORD = new ThunderLord();

    /**
    * Has a good chance of one shotting the entity
     */
    public static Enchantment ONESHOT = new OneShot();

    /**
    * Will summon NPC when a entity attacks the player
     */
    public static Enchantment VANGUARD = new Vanguard();

    /**
    * Steals money from a enemy player and adds the balance to the players bank account. Requires Vault Dependency
     */
    public static Enchantment PICKPOCKET = new PickPocket();

    /**
    * Will unregister the Custom Enchantment registered on the server
     * @param enchantment To register the Customized Enchantment using the Bukkit Class
     */
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

    /**
    * Will register the Custom Enchantment that is not been registed yet, most prefered when the Server loads up
     * @param enchantment To register the Customized Enchantment using the Bukkit Class
     */
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

    /**
     * Using Roman Numeralds to display once registered on the item
     */
    private static final String[] NUMERALS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
    /**
     * Will apply a String lore that contains the Enchantment on the Item. Use ChatColor.GREY + CEnchantment.returnEnchantmentName(Enchantment enchantment, int enchantmentLevel)
     * @param ench Sets the enchantment onto an item as a lore
     * @param enchLevel Sets the enchantment level onto an item as lore on String
     * @return Returns the enchantment and level in String Form as Roman Numerals.
     */
    public static String returnEnchantmentName(Enchantment ench, int enchLevel){
        if(enchLevel == 1 && ench.getMaxLevel() == 1){
            return ench.getName();
        }
        if(enchLevel > 10 || enchLevel <= 0){
            return ench.getName() + " enchantment.level." + enchLevel;
        }
        return ench.getName() + " " + NUMERALS[enchLevel- 1];
    }

    /**
    * @return Checks if the item contains this type of Enchantment. Note: ItemMeta.hasEnchant() may also work for this function.
    * It may serve as a replacement but ItemMeta.hasEnchant() but it may break if ItemMeta.hasEnchant() is used to check a Custom Enchantment from another Plugin WARNING!
     * @param item Gets the item on the players hand.
     * @param enchant Gets the enchantment where they need to check if it contains the enchantment.
     */
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
    /**
    * @return Returns the Level of the Custom Enchantment that is allowed by the Enchantment.getMaxLevel().
    * ItemMeta.getEnchantLevel() may work for this function. It may break if this plugin is used to get the Custom Enchantment from another plugin WARNING!
     * @param item Gets the item on the players hand.
     * @param enchant Identifies the enchantment so it can identify the Enchantment Level.
;     */
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

    /**
    * @deprecated due to the constant breaks when the method is used. No alternatives for this function.
     * @param player Player object
     * @param enchantment Gets the Enchantment
     * @param level gets the enchantment level
     * @param colour Sets the colour of the lore.
     */
    @Deprecated
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
