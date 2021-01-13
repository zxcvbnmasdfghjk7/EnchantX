package io.github.eddiediamondfire.cne.commands;

import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    String getName();

    String getDescription();

    String getSyntax();

    boolean onCommandExecution(Player player, String[] args);

    List<String> onAutoTabCompletion(Player player, String[] args);
}
