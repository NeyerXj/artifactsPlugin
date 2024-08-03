package org.example.neyer.artifactsplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class PreventMinecartPlaceListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.COMMAND_BLOCK_MINECART && "Осколок души".equals(item.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
        }
    }
}
