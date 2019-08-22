package club.MysticPractice.MysticBridge.MinecraftSide;

import club.MysticPractice.MysticBridge.MainClass;
import net.dv8tion.jda.core.entities.User;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;

import java.io.File;

import static org.bukkit.Bukkit.getServer;

public class MinecraftChat implements Listener {

    public MainClass mainClass;
    public static Chat vaultChat = null;

    public MinecraftChat(MainClass mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        File file = new File(mainClass.getDataFolder() + "/links/" + e.getPlayer().getUniqueId() + ".yml");
        if (file.exists()) {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
            if (e.getPlayer().hasPermission("mystic.chatcolor")) {
                e.setMessage(org.bukkit.ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            }
            e.setCancelled(true);
            User discordUser = MainClass.jda.getUserById(fc.getString("discordid"));
            if (fc.getBoolean("showtag")) {
                Bukkit.broadcastMessage(colorize(vaultChat.getPlayerPrefix(e.getPlayer())) + e.getPlayer().getDisplayName() + " " + ChatColor.GRAY + "[" + discordUser.getAsTag() + "] " + ChatColor.RESET + "» " + e.getMessage());
            } else {
                Bukkit.broadcastMessage(colorize(vaultChat.getPlayerPrefix(e.getPlayer())) + e.getPlayer().getDisplayName() + ChatColor.RESET + " » " + e.getMessage());
            }
        } else {
            e.setCancelled(true);
            Bukkit.broadcastMessage(colorize(vaultChat.getPlayerPrefix(e.getPlayer())) + e.getPlayer().getDisplayName() + ChatColor.RESET + " » " + e.getMessage());
        }
    }

    private static String colorize(String s) {
        return s == null ? null : ChatColor.translateAlternateColorCodes('&', s);
    }

    @EventHandler
    public void onServiceChange(ServiceRegisterEvent e) {
        if (e.getProvider().getService() == Chat.class) {
            refreshVault();
        }
    }

    @EventHandler
    public void onServiceChange(ServiceUnregisterEvent e) {
        if (e.getProvider().getService() == Chat.class) {
            refreshVault();
        }
    }

    public static void refreshVault() {
        Chat vaultChat = getServer().getServicesManager().load(Chat.class);
        if (vaultChat != MinecraftChat.vaultChat) {
            Bukkit.getLogger().info("New Vault Chat implementation registered: " + (vaultChat == null ? "null" : vaultChat.getName()));
        }
        MinecraftChat.vaultChat = vaultChat;
    }
}
