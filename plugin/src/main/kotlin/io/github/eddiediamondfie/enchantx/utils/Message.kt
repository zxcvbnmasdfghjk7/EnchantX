package io.github.eddiediamondfie.enchantx.utils

import io.github.eddiediamondfire.enchantx.EnchantX
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

class Message(plugin: EnchantX) {

    companion object{

        fun sendMessage(player: Player, colour: ChatColor, message: String){
            player.sendMessage("$colour $message")
        }

        fun sendMessage(colour: ChatColor, message: String){
            val console: ConsoleCommandSender = Bukkit.getConsoleSender()
            console.sendMessage("$colour $message")
        }
    }
}