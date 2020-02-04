package io.github.eddiediamondfire.cne.Commands.SubCommandWrappers;

import org.bukkit.entity.Player;

public abstract class SubCommandCNE {

    public SubCommandCNE(){
    }

    public abstract void onCommand(Player player, String[] args);
    public abstract String name();
    public abstract String info();
    public abstract String[] aliases();
}
