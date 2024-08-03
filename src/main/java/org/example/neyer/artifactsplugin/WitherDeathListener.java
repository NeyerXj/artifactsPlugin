package org.example.neyer.artifactsplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class WitherDeathListener implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public WitherDeathListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWitherDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.WITHER) {
            if (random.nextDouble() <= 0.5) {
                ItemStack soulShard = new ItemStack(Material.COMMAND_BLOCK_MINECART);
                ItemMeta meta = soulShard.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName("Осколок души");
                    soulShard.setItemMeta(meta);
                }
                event.getDrops().add(soulShard);
                Bukkit.getLogger().info("Осколок души добавлен в лут.");
            }
        }
    }
}