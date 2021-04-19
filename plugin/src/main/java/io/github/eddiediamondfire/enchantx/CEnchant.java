package io.github.eddiediamondfire.enchantx;

import io.github.eddiediamondfire.enchantx.enchants.Pern;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CEnchant {

    private final EnchantX plugin;
    private final Logger logger;

    private final Map<String, Enchantment> customEnchantments;
    private static final String[] NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static final Enchantment PERN_ENCHANTMENT = new Pern();

    public CEnchant(EnchantX plugin){
        this.plugin = plugin;
        logger = LoggerFactory.getLogger(CEnchant.class);
        customEnchantments = new HashMap<>();
    }

    public void registerCustomEnchantment(Enchantment enchantment){
        boolean isRegistered = false;
        try{
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);

            if(!customEnchantments.containsKey(enchantment.getName())){
                getCustomEnchantments().put(enchantment.getName(), enchantment);
            }

            isRegistered = true;
        }catch (Exception ex){
            logger.error("An error occured while registering Enchantment", ex);
            ex.printStackTrace();
            isRegistered = false;
        }finally{
            if(isRegistered){
                logger.info("Enchantment " + enchantment.getName() + " is registered!");
            }else{
                logger.info("Failed to register enchantment " + enchantment.getName());
            }

        }
    }

    @SuppressWarnings("unchecked")
    public void unregisterCustomEnchantment(Enchantment enchantment){
        try{
            Field keyField = Enchantment.class.getDeclaredField("byKey");
            Field nameField = Enchantment.class.getDeclaredField("byName");

            keyField.setAccessible(true);
            nameField.setAccessible(true);

            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byKey.containsKey(enchantment.getKey())){
                byKey.remove(enchantment.getKey());
            }

            if(byName.containsKey(enchantment.getName())){
                byName.remove(enchantment.getName());
            }

            if(customEnchantments.containsKey(enchantment.getName())){
                customEnchantments.remove(enchantment.getName());
            }
        }catch (Exception ignored){
        }
    }

    public static String returnEnchantmentName(Enchantment enchant, int enchantLevel){
        if(enchantLevel == 1 && enchant.getMaxLevel() == 1){
            return enchant.getName();
        }

        if(enchantLevel > 10 || enchantLevel <= 0){
            return enchant.getName() + " enchantment.level." + enchantLevel;
        }

        return enchant.getName() + " " + NUMERALS[enchantLevel- 1];
    }

    public static boolean hasEnchantment(ItemStack item, Enchantment enchantment){
        if(item.getItemMeta() != null && item.getItemMeta().getEnchants() != null && item.getItemMeta().getEnchants().size() > 0){
            for(Iterator<Map.Entry<Enchantment, Integer>> it = item.getItemMeta().getEnchants().entrySet().iterator(); it.hasNext();){
                Map.Entry<Enchantment, Integer> e = it.next();

                if(e.getKey().equals(enchantment)){
                    return true;
                }
            }
        }
        return false;
    }

    public static int getEnchantmentLevel(ItemStack item, Enchantment enchantment){
        if(item.getItemMeta() != null && item.getItemMeta().getEnchants() != null && item.getItemMeta().getEnchants().size() > 0){
            for(Iterator<Map.Entry<Enchantment, Integer>> it = item.getItemMeta().getEnchants().entrySet().iterator(); it.hasNext();){
                Map.Entry<Enchantment, Integer> e = it.next();

                if(e.getKey().equals(enchantment)){
                    return e.getValue();
                }
            }
        }
        return 0;
    }

    public Enchantment getEnchantment(String enchantName){
        for(Enchantment enchantment: customEnchantments.values()){
            if(enchantment.getName().equalsIgnoreCase(enchantName)){
                return enchantment;
            }
        }
        return null;
    }

    public Map<String, Enchantment> getCustomEnchantments() {
        return customEnchantments;
    }
}
