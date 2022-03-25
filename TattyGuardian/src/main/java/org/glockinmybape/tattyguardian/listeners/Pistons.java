package org.glockinmybape.tattyguardian.listeners;

import java.util.Iterator;
import org.glockinmybape.tattyguardian.utils.config;
import org.glockinmybape.tattyguardian.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.glockinmybape.tattyguardian.TattyGuardian;

public class Pistons implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (this.enabled("CrashPistons.fix.dispenser") && b.getLocation().getY() >= (double)this.limit("CrashPistons.limit.dispenser") && b.getType() == Material.DISPENSER) {
            e.setCancelled(true);
            this.sendMsg(p);
        }

        if (this.enabled("CrashPistons.fix.dropper") && b.getLocation().getY() >= (double)this.limit("CrashPistons.limit.dropper") && b.getType() == Material.DROPPER) {
            e.setCancelled(true);
            this.sendMsg(p);
        }

        if (this.enabled("CrashPistons.fix.piston") && b.getLocation().getY() >= (double)this.limit("CrashPistons.limit.piston") && (b.getType() == Material.PISTON_BASE || b.getType() == Material.PISTON_STICKY_BASE)) {
            e.setCancelled(true);
            this.sendMsg(p);
        }

        if (config.enabledOtherBlocks()) {
            Iterator var5 = config.getOtherBlocks().iterator();

            while(var5.hasNext()) {
                String block = (String)var5.next();
                String[] args = block.split(":");
                Material mat = Material.matchMaterial(args[0]);
                int limit = Integer.parseInt(args[1]);
                if (b.getType() == mat && b.getLocation().getY() >= (double)limit) {
                    e.setCancelled(true);
                    this.sendMsg(p);
                }
            }
        }

    }

    private void sendMsg(Player p) {
        p.sendMessage(Utils.colorize(TattyGuardian.cfg.getString("CrashPistons.fix.message")));
    }

    private int limit(String text) {
        return TattyGuardian.cfg.getInt(text);
    }

    private boolean enabled(String text) {
        return TattyGuardian.cfg.getBoolean(text);
    }
}
