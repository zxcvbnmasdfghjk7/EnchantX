package io.github.eddiediamondfire.cne;

import io.github.eddiediamondfire.cne.enchantments.Explosive;
import io.github.eddiediamondfire.cne.wrapper.Enchant;
import org.bukkit.Keyed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.*;

public class CEnchantment {

    private final CNE plugin;
    private final List<Enchantment> enchants;
    private static final String[] NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public final static Enchantment EXPLOSIVE = new Explosive();

    public CEnchantment(CNE plugin){
        this.plugin = plugin;
        enchants = new ArrayList<>();
        enchants.add(new Explosive());
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
                for (Map.Entry<Enchantment, Integer> e : item.getItemMeta().getEnchants().entrySet()) {
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
                for (Map.Entry<Enchantment, Integer> e : item.getItemMeta().getEnchants().entrySet()) {
                    if (e.getKey().equals(enchant)) {
                        return e.getValue();
                    }
                }
            }
        }
        return 0;
    }

    public static String returnEnchantmentName(Enchantment ench, int enchLevel){
        if(enchLevel == 1 && ench.getMaxLevel() == 1){
            return ench.getName();
        }
        if(enchLevel > 10 || enchLevel <= 0){
            return ench.getName() + " enchantment.level." + enchLevel;
        }
        return ench.getName() + " " + NUMERALS[enchLevel- 1];
    }

    public void registerEnchantment(){
        try{
            try{
                Field field = Enchantment.class.getField("acceptingNew");
                field.setAccessible(true);
                field.set(null, true);
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                for(Enchantment enchantment: enchants){
                    Enchantment.registerEnchantment(enchantment);
                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void unregisterEnchantment(){
        try{
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byKeyField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Keyed, Enchantment> byKey = (HashMap<Keyed, Enchantment>) byKeyField.get(null);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            for(Enchantment enchant: enchants){
                byKey.remove(enchant.getKey());
                byName.remove(enchant.getName());
            }
        }catch (Exception ignored){
        }
    }

    public void onServerEnable(){
        registerEnchantment();
        unregisterEnchantment();
    }
}
