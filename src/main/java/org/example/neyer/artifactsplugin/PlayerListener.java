package org.example.neyer.artifactsplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {

    private final JavaPlugin plugin;

    public PlayerListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startEffectTask();
    }

    private void startEffectTask() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                applyOrUpdateEffect(player);
            }
        }, 0L, 20L); // Проверка каждые 20 тиков (1 секунда)
    }

    private void applyOrUpdateEffect(Player player) {
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        if (itemInOffHand != null && (itemInOffHand.getType() == Material.YELLOW_DYE || itemInOffHand.getType() == Material.WHITE_DYE)) {
            ItemMeta meta = itemInOffHand.getItemMeta();
            if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "unique_dye"),
                    PersistentDataType.STRING)) {
                String dyeType = meta.getPersistentDataContainer().get(new NamespacedKey(plugin, "unique_dye"), PersistentDataType.STRING);

                PotionEffectType effectType = null;
                if ("haste_artifact".equals(dyeType)) {
                    effectType = PotionEffectType.FAST_DIGGING;
                } else if ("speed_artifact".equals(dyeType)) {
                    effectType = PotionEffectType.SPEED;
                }

                if (effectType != null) {
                    // Применение эффекта на 3 секунды
                    player.addPotionEffect(new PotionEffect(effectType, 60, 1, false, false, false)); // 60 тиков = 3 секунды
                }
            }
        }
    }
}
