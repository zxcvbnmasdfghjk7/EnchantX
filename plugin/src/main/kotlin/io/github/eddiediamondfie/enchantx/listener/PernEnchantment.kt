package io.github.eddiediamondfie.enchantx.listener

import io.github.eddiediamondfire.enchantx.CEnchant
import io.github.eddiediamondfire.enchantx.EnchantX
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class PernEnchantment(plugin: EnchantX): Listener {

    @EventHandler
    fun onAttackEvent(event: EntityDamageByEntityEvent){
        if(event.damager is Player)
        {
            val player: Player = event.damager as Player
            val opponent: Entity = event.entity

            if(CEnchant.hasEnchantment(player.inventory.itemInMainHand, CEnchant.PERN_ENCHANTMENT)){
                val enchantLevel: Int = CEnchant.getEnchantmentLevel(player.inventory.itemInMainHand, CEnchant.PERN_ENCHANTMENT)

                if(enchantLevel == 1){
                    val world: World = player.world
                    world.strikeLightning(opponent.location)
                }
            }
        }
    }
}