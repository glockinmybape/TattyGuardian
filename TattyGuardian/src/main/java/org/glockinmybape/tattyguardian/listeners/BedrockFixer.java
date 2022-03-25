package org.glockinmybape.tattyguardian.listeners;

import org.glockinmybape.tattyguardian.utils.config;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BedrockFixer implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (config.isBedrockFixEnabled()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Location loc = e.getPlayer().getLocation();
                if (loc.getWorld().getEnvironment() == Environment.NETHER && loc.getY() >= 128.0D) {
                    e.setCancelled(true);
                }
            }

        }
    }
}
