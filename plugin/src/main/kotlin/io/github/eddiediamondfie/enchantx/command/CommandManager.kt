package io.github.eddiediamondfie.enchantx.command

import io.github.eddiediamondfie.enchantx.utils.Message
import io.github.eddiediamondfire.enchantx.EnchantX
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.ArrayList

class CommandManager(plugin: EnchantX): CommandExecutor {
    private val subCommands: MutableList<SubCommand>

    init{
        subCommands = ArrayList()
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if(sender is Player){
            val player: Player = sender

            if(args.isNotEmpty()){
                for(i in 0 until subCommands.size){
                    if(args[0].equals(subCommands[i].getName(), true)){
                        subCommands[i].onAction(player, args)
                    }
                }
            }else if(args.isEmpty()){
                Message.sendMessage(player, ChatColor.YELLOW, "-------------------------------")
                for(i in 0 until subCommands.size){
                    Message.sendMessage(player, ChatColor.YELLOW, "${subCommands[i].getSyntax()} - ${subCommands[i].getDescription()}")
                }
                Message.sendMessage(player, ChatColor.YELLOW, "-------------------------------")
            }
        }
        return false;
    }
}