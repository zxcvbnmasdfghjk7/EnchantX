package io.github.eddiediamondfire.cne.commands.subcommands;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.commands.SubCommand;
import io.github.eddiediamondfire.cne.enchantments.core.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantCommand implements SubCommand {
    private final CNE plugin;
    public EnchantCommand(CNE plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "enchant";
    }

    @Override
    public String getDescription() {
        return "Enchants the item";
    }

    @Override
    public String getSyntax() {
        return "/cne enchant <enchantment> <level>";
    }

    @Override
    public boolean onCommandExecution(Player player, String[] args) {
        if(args.length > 1){
            try{
                String enchantment = args[1];
                int level = Integer.parseInt(args[2]);

                if(plugin.getEnchantment().getEnchantmentFromString(enchantment) == null){
                    player.sendMessage(ChatColor.RED + "Invalid Enchantment");
                    return true;
                }

                Enchantment enchant = plugin.getEnchantment().getEnchantmentFromString(enchantment);

                if(player.getInventory().getItemInMainHand() == null){
                    player.sendMessage(ChatColor.RED + "You do not have an item in your hand");
                    return true;
                }

                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<>();

                meta.addEnchant(enchant, level, true);
                lore.add(plugin.getEnchantment().returnEnchantmentName(enchant, level));
                meta.setLore(lore);


                item.setItemMeta(meta);
                player.sendMessage(ChatColor.GREEN + "Added enchantment " + enchant.getName());
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(args.length == 1){
            player.sendMessage(ChatColor.RED + "Not enough arguments");
            player.sendMessage(ChatColor.RED + getSyntax());
            return true;
        }
        return false;
    }

    @Override
    public List<String> onAutoTabCompletion(Player player, String[] args) {
        if(args.length == 1)
        {
            return new ArrayList<>(plugin.getEnchantment().getEnchants().keySet());
        }
        return null;
    }
}
