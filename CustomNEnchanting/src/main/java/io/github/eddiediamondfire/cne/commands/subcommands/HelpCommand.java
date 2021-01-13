package io.github.eddiediamondfire.cne.commands.subcommands;

import io.github.eddiediamondfire.cne.CNE;
import io.github.eddiediamondfire.cne.commands.CommandManager;
import io.github.eddiediamondfire.cne.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCommand implements SubCommand {
    private final CNE plugin;
    private final CommandManager commandManager;
    public HelpCommand(CNE plugin)
    {
        this.plugin = plugin;
        this.commandManager = plugin.getCommandManager();
    }
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Brings up this";
    }

    @Override
    public String getSyntax() {
        return "/cne help";
    }

    @Override
    public boolean onCommandExecution(Player player, String[] args) {
        player.sendMessage(ChatColor.YELLOW + "----------------");
        for(SubCommand subCommand:commandManager.getSubCommands()){
            player.sendMessage(ChatColor.YELLOW + subCommand.getSyntax() + " - " + subCommand.getDescription());
        }
        player.sendMessage(ChatColor.YELLOW + "----------------");
        return true;
    }

    @Override
    public List<String> onAutoTabCompletion(Player player, String[] args) {
        return null;
    }
}
