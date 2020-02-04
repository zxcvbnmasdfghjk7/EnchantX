package io.github.eddiediamondfire.cne.CoolDowns;

import java.util.HashMap;
import java.util.UUID;

public class CoolDownManager {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static final int EXPLOSION_COOLDOWN = 15;

    public void setCooldown(UUID player, long time){
        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public Long getCooldown(UUID player){
        return cooldowns.getOrDefault(player, (long) 0);
    }
}
