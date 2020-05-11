package io.github.eddiediamondfire.cne.Commands;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.Commands.SCCMD.cne.InfoSubCommand;
import io.github.eddiediamondfire.cne.Commands.SubCommandWrappers.SubCommandCNE;
import io.github.eddiediamondfire.cne.Ultils.CEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class MainCMDManager implements CommandExecutor {

    private static CNE plugin = CNE.getInstance();
    public static ArrayList<SubCommandCNE> subCommandCNE = new ArrayList<>();

    public String cne = "cne";
    public String enchant = "enchant";
    public static String subCommandInfo = "info";

    public void loadCommands(){
        plugin.getCommand(cne).setExecutor(this);
        subCommandCNE.add(new InfoSubCommand());
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Players are not allowed!");
            return true;
        }

        // Declare if the Player is the Sender
        Player player = (Player) sender;

        // for CNE (Main) command
        if(command.getName().equalsIgnoreCase(cne)){
            if(args.length == 0){
                player.sendMessage("Not Enough Arguments");
                return true;
            }

            SubCommandCNE target = CommandMethods.get(args[0]);

            if(target == null){
                player.sendMessage("Invailded SubCommand");
                return true;
            }

            ArrayList<String> string = new ArrayList<>();

            string.addAll(Arrays.asList(args));
            string.remove(0);

            try{
                target.onCommand(player,args);
            }catch (Exception e){
                player.sendMessage(ChatColor.RED + "An error has occurred.");

                e.printStackTrace();
            }

            if(command.getName().equalsIgnoreCase(enchant)){

                if(args.length == 1){
                    try{
                        int level = Integer.parseInt(args[2]);
                        Enchantment enchantment  = Enchantment.getByName(args[1]);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    player.sendMessage("Not Enough Arguments");
                    return true;
                }
            }
        }
        return false;
    }
}
