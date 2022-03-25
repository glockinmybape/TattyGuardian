package org.glockinmybape.tattyguardian.listeners;

import org.glockinmybape.tattyguardian.utils.config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

public class AntiTabCompleter implements Listener {
    @EventHandler
    public void onTab(TabCompleteEvent e) {
        if (config.isTabEnabled()) {
            if (!e.getSender().hasPermission("tattyguard.tabcomplete")) {
                e.setCancelled(true);
            }

        }
    }
}
