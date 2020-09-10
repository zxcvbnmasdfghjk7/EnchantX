package io.github.eddiediamondfire.cne.CustomEntity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class VanguardEntity {

    public void spawnEntity(Player player){
        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation().add(1, 0, 1), EntityType.ZOMBIE);

    }
}
