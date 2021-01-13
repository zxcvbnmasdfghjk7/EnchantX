package io.github.eddiediamondfire.cne.enchantments.core;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import io.github.eddiediamondfire.cne.CNE;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class CustomEntity {
    private final CNE plugin;
    private final List<String> metaDataKeys;
    public CustomEntity(CNE plugin){
        this.plugin = plugin;
        metaDataKeys = new ArrayList<>();
        metaDataKeys.add("customCreeperExplosiveEnchantment");
    }

    public void spawnCustomChargedCreeper(Player player, Location location, Entity damager){
        Creeper creeper = (Creeper) player.getWorld().spawnEntity(location, EntityType.CREEPER);
        creeper.setPowered(true);
        player.getWorld().strikeLightning(creeper.getLocation());
        creeper.setMetadata("customCreeperExplosiveEnchantment", new FixedMetadataValue(plugin, "customCreeperExplosiveEnchantment"));
        creeper.setTarget((LivingEntity) damager);
    }

    public List<String> getMetaDataKeys(){
        return metaDataKeys;
    }
}
