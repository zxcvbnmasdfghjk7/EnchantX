package io.github.eddiediamondfie.enchantx.command

import org.bukkit.entity.Player

interface SubCommand {

    fun getName(): String

    fun getSyntax(): String

    fun getDescription(): String

    fun onAction(player: Player, args: Array<String>)
}