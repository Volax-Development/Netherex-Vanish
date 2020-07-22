package fr.volax.netherexvanish;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class NetherexVanish extends JavaPlugin {
    public static ArrayList<Player> vanished = new ArrayList<>();
    public static ArrayList<Player> netherite = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("vanish").setExecutor(new VanishCommand());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Events(), this);
        ShapedRecipe NETHERITE_HELMET = new ShapedRecipe(new NamespacedKey(this, "netherite_helmet_key"), new ItemBuilder(Material.NETHERITE_HELMET, 1).setName("§cCasque en Netherite").toItemStack()).shape("LLL", "L L").setIngredient('L', Material.NETHERITE_INGOT);
        ShapedRecipe NETHERITE_CHESTPLATE = new ShapedRecipe(new NamespacedKey(this, "netherite_chestplate_key"), new ItemBuilder(Material.NETHERITE_CHESTPLATE, 1).setName("§cPlastron en Netherite").toItemStack()).shape("L L", "LLL", "LLL").setIngredient('L', Material.NETHERITE_INGOT);
        ShapedRecipe NETHERITE_LEGGINGS = new ShapedRecipe(new NamespacedKey(this, "netherite_leggings_key"), new ItemBuilder(Material.NETHERITE_LEGGINGS, 1).setName("§cJambières en Netherite").toItemStack()).shape("LLL", "L L", "L L").setIngredient('L', Material.NETHERITE_INGOT);
        ShapedRecipe NETHERITE_BOOTS = new ShapedRecipe(new NamespacedKey(this, "netherite_boots_key"), new ItemBuilder(Material.NETHERITE_BOOTS, 1).setName("§cBottes en Netherite").toItemStack()).shape("L L", "L L").setIngredient('L', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(NETHERITE_HELMET);
        Bukkit.addRecipe(NETHERITE_CHESTPLATE);
        Bukkit.addRecipe(NETHERITE_LEGGINGS);
        Bukkit.addRecipe(NETHERITE_BOOTS);
    }
}
