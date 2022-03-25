package org.glockinmybape.tattyguardian.listeners;

import java.util.Iterator;
import org.glockinmybape.tattyguardian.utils.config;
import org.glockinmybape.tattyguardian.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChunkLimiter implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (this.contains(b.getType())) {
            int limit = this.getLimit(b.getType());
            if (limit != -1 && this.getsCancelled(b.getLocation(), b.getType(), limit)) {
                e.setCancelled(true);
                p.sendMessage(Utils.colorize(config.getBLCMessage().replace("%limit%", "" + limit)));
            }
        }

    }

    private boolean getsCancelled(Location bLoc, Material limited, int limit) {
        int number = 0;
        Chunk checkchunk = bLoc.getChunk();

        for(int x = 0; x < 16; ++x) {
            for(int y = 0; y < 256; ++y) {
                for(int z = 0; z < 16; ++z) {
                    Material check = checkchunk.getBlock(x, y, z).getType();
                    if (check.equals(limited)) {
                        ++number;
                    }
                }
            }
        }

        if (number > limit) {
            return true;
        } else {
            return false;
        }
    }

    private int getLimit(Material mat) {
        Iterator var3 = config.getBLCBlocks().iterator();

        while(var3.hasNext()) {
            String block = (String)var3.next();
            String[] args = block.split(":");
            Material material = Material.matchMaterial(args[0]);
            if (mat == material) {
                return Integer.parseInt(args[1]);
            }
        }

        return -1;
    }

    private boolean contains(Material mat) {
        Iterator var3 = config.getBLCBlocks().iterator();

        while(var3.hasNext()) {
            String block = (String)var3.next();
            String[] args = block.split(":");
            Material material = Material.matchMaterial(args[0]);
            if (mat == material) {
                return true;
            }
        }

        return false;
    }
}
