package club.MysticPractice.MysticBridge.MinecraftSide;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MinecraftInvClick implements Listener {
    public JavaPlugin plugin;
    public MinecraftInvClick(JavaPlugin plugin) {this.plugin = plugin;}
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (ChatColor.stripColor(event.getInventory().getName()).equals("Discord Settings:")) {
            switch (event.getCurrentItem().getType()) {
                case REDSTONE_BLOCK:
                    event.getWhoClicked().closeInventory();
                    File file = new File(plugin.getDataFolder() + "/links/" + event.getWhoClicked().getUniqueId() + ".yml");
                    FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
                    fc.set("showtag", true);
                    try {
                        fc.save(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    event.getWhoClicked().sendMessage("§aSet 'Show tag in messages' to §a§lON§r§a.");
                    break;
                case EMERALD_BLOCK:
                    event.getWhoClicked().closeInventory();
                    File file2 = new File(plugin.getDataFolder() + "/links/" + event.getWhoClicked().getUniqueId() + ".yml");
                    FileConfiguration fc2 = YamlConfiguration.loadConfiguration(file2);
                    fc2.set("showtag", false);
                    try {
                        fc2.save(file2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    event.getWhoClicked().sendMessage("§aSet 'Show tag in messages' to §4§lOFF§r§a.");
                    break;
                default:
                    break;
            }
        }
    }
}
