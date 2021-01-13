package io.github.eddiediamondfire.cne.enchantments.core;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.enchantments.Explosive;
import lombok.Getter;
import org.bukkit.Keyed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.*;

public class CEnchantment {
    private final CNE plugin;
    private final CustomEntity customEntity;
    private @Getter Map<String, Enchantment> enchants;
    private static final String[] NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public final static Enchantment EXPLOSIVE = new Explosive();

    public CEnchantment(CNE plugin){
        this.plugin = plugin;
        this.customEntity = new CustomEntity(plugin);
        this.enchants = new HashMap<>();
    }

    public boolean hasEnchantment(ItemStack item, Enchantment enchant){
        if(item.getItemMeta() != null) {
            item.getItemMeta().getEnchants();
            if (item.getItemMeta().getEnchants().size() > 0) {
                for (Iterator<Map.Entry<Enchantment, Integer>> loop = item.getItemMeta().getEnchants().entrySet().iterator(); loop.hasNext(); ) {
                    Map.Entry<Enchantment, Integer> e = loop.next();
                    if (e.getKey().equals(enchant)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getLevel(ItemStack item, Enchantment enchant){
        if(item.getItemMeta() != null) {
            item.getItemMeta().getEnchants();
            if (item.getItemMeta().getEnchants().size() > 0) {
                for(Iterator<Map.Entry<Enchantment, Integer>> loop = item.getItemMeta().getEnchants().entrySet().iterator(); loop.hasNext(); ){
                    Map.Entry<Enchantment, Integer> enchantment = loop.next();
                    if(enchantment.getKey().equals(enchant)){
                        return enchantment.getValue();
                    }
                }
            }
        }
        return 0;
    }

    public String returnEnchantmentName(Enchantment ench, int enchLevel){
        if(enchLevel == 1 && ench.getMaxLevel() == 1){
            return ench.getName();
        }
        if(enchLevel > 10 || enchLevel <= 0){
            return ench.getName() + " enchantment.level." + enchLevel;
        }
        return ench.getName() + " " + NUMERALS[enchLevel- 1];
    }

    public void registerEnchantment(Enchantment enchantment){
        try{
            try{
                Field field = Enchantment.class.getDeclaredField("acceptingNew");
                field.setAccessible(true);
                field.set(null, true);
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                Enchantment.registerEnchantment(enchantment);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void unregisterEnchantment(Enchantment enchantment){
        try{
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byKeyField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Keyed, Enchantment> byKey = (HashMap<Keyed, Enchantment>) byKeyField.get(null);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            byName.remove(enchantment.getName());
            byKey.remove(enchantment.getKey());
        }catch (Exception ignored){
        }
    }

    public void onServerEnable(){
        registerEnchantment(EXPLOSIVE);
        enchants.put("explosive", CEnchantment.EXPLOSIVE);

    }

    public void onServerDisable(){
        unregisterEnchantment(EXPLOSIVE);
    }

    public Enchantment getEnchantmentFromString(String name){
        if(name != null){
            return enchants.get(name);
        }
        return null;
    }

    public CustomEntity getCustomEntity() {
        return customEntity;
    }
}
