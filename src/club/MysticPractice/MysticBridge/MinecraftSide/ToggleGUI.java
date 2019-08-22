package club.MysticPractice.MysticBridge.MinecraftSide;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ToggleGUI {
    public static void openGUI(Player player, JavaPlugin mainClass) {
        Inventory i = Bukkit.createInventory(null, 9, "Discord Settings:");
        File file = new File(mainClass.getDataFolder() + "/links/" + player.getUniqueId() + ".yml");
        if (file.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
            if (fc.getBoolean("showtag")) {
                i.setItem(4, inventoryItem(Material.EMERALD_BLOCK, "§a§lShow tag in messages: ON"));
            } else {
                i.setItem(4, inventoryItem(Material.REDSTONE_BLOCK, "§4§lShow tag in messages: OFF"));
            }
            player.openInventory(i);
        } else {
            player.sendMessage("§4You don't have a Discord account linked.");
        }
    }

    public static ItemStack inventoryItem(Material material, String name) {
        ItemStack temp = new ItemStack(material);
        ItemMeta tempMeta = temp.getItemMeta();
        tempMeta.setDisplayName(name);
        temp.setItemMeta(tempMeta);
        return temp;
    }
}
