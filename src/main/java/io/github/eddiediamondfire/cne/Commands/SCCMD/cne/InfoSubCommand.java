package io.github.eddiediamondfire.cne.Commands.SCCMD.cne;

import io.github.eddiediamondfire.cne.Commands.CMDManager;
import io.github.eddiediamondfire.cne.Commands.SubCommandWrappers.SubCommandCNE;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoSubCommand extends SubCommandCNE {
    @Override
    public void onCommand(Player player, String[] args) {
        player.sendMessage(ChatColor.GREEN + "Specific Custom Enchantment Codded Plugin by zxcvbnmasdfghjk7 (EddieDiamondFire)");
        player.sendMessage(ChatColor.GREEN + "Version: 0.1 Alpha Testing Development");
    }

    @Override
    public String name() {
        return CMDManager.subCommandInfo;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
