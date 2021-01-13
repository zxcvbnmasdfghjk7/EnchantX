package io.github.eddiediamondfire.cne.commands;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.commands.subcommands.EnchantCommand;
import io.github.eddiediamondfire.cne.commands.subcommands.HelpCommand;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter
{

    private final CNE plugin;
    private @Getter final List<SubCommand> subCommands;
    private @Getter final SubCommand helpCommand;
    public CommandManager(CNE plugin)
    {
        this.plugin = plugin;
        this.subCommands = new ArrayList<>();
        this.helpCommand = new HelpCommand(plugin);
        subCommands.add(new EnchantCommand(plugin));
        subCommands.add(new HelpCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
        }

        Player player = (Player) sender;

        if(args.length > 0){
            for(int i = 0; i < subCommands.size(); i++){
                if(args[0].equalsIgnoreCase(subCommands.get(i).getName())){
                    subCommands.get(i).onCommandExecution(player, args);
                }
            }
        }else{
            player.sendMessage(ChatColor.YELLOW + "----------------");
            for(SubCommand subCommand:subCommands){
                player.sendMessage(ChatColor.YELLOW + subCommand.getSyntax() + " - " + subCommand.getDescription());
            }
            player.sendMessage(ChatColor.YELLOW + "----------------");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1)
        {
            List<String> subCommandsArguments = new ArrayList<>();
            for(int i = 0; i < subCommands.size(); i++){
                subCommandsArguments.add(subCommands.get(i).getName());
            }
            return subCommandsArguments;
        }else if(args.length >= 2)
        {
            for (SubCommand subCommand : subCommands)
            {
                if (args[0].equalsIgnoreCase(subCommand.getName()))
                {
                    return subCommand.onAutoTabCompletion((Player) sender, args);
                }
            }
        }
        return null;
    }
}
