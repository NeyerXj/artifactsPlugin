package org.example.neyer.artifactsplugin;

import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArtifactsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        createCustomRecipes();
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new WitherDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PreventMinecartPlaceListener(), this);
    }

    private void createCustomRecipes() {
        // Уникальный желтый краситель (Артефакт спешки)
        ItemStack uniqueYellowDye = new ItemStack(Material.YELLOW_DYE);
        ItemMeta yellowMeta = uniqueYellowDye.getItemMeta();
        if (yellowMeta != null) {
            yellowMeta.setDisplayName("Артефакт спешки");
            yellowMeta.getPersistentDataContainer().set(new NamespacedKey(this, "unique_dye"),
                    PersistentDataType.STRING, "haste_artifact");
            yellowMeta.setCustomModelData(1); // Установите значение для вашей модели
            uniqueYellowDye.setItemMeta(yellowMeta);
        }
        NamespacedKey yellowKey = new NamespacedKey(this, "unique_yellow_dye");

        ShapedRecipe yellowRecipe = new ShapedRecipe(yellowKey, uniqueYellowDye);
        yellowRecipe.shape("SCS", " N ", "R R");
        yellowRecipe.setIngredient('C', Material.COMMAND_BLOCK_MINECART);
        yellowRecipe.setIngredient('S', Material.SUGAR);
        yellowRecipe.setIngredient('R', Material.RABBIT_FOOT);
        yellowRecipe.setIngredient('N', Material.NETHER_STAR);
        Bukkit.addRecipe(yellowRecipe);

        // Уникальный белый краситель (Артефакт скорости)
        ItemStack uniqueWhiteDye = new ItemStack(Material.WHITE_DYE);
        ItemMeta whiteMeta = uniqueWhiteDye.getItemMeta();
        if (whiteMeta != null) {
            whiteMeta.setDisplayName("Артефакт скорости");
            whiteMeta.getPersistentDataContainer().set(new NamespacedKey(this, "unique_dye"),
                    PersistentDataType.STRING, "speed_artifact");
            whiteMeta.setCustomModelData(2); // Установите значение для вашей модели
            uniqueWhiteDye.setItemMeta(whiteMeta);
        }
        NamespacedKey whiteKey = new NamespacedKey(this, "unique_white_dye");

        ShapedRecipe whiteRecipe = new ShapedRecipe(whiteKey, uniqueWhiteDye);
        whiteRecipe.shape("SMS", " N ", "K K");
        whiteRecipe.setIngredient('N', Material.NETHER_STAR);
        whiteRecipe.setIngredient('K', Material.GOLDEN_CARROT);
        whiteRecipe.setIngredient('M', Material.COMMAND_BLOCK_MINECART);
        whiteRecipe.setIngredient('S', Material.SUGAR);
        Bukkit.addRecipe(whiteRecipe);
    }
}