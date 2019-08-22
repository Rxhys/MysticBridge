package club.MysticPractice.MysticBridge.MinecraftSide;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordSettings implements CommandExecutor {
    public JavaPlugin plugin;
    public DiscordSettings(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ToggleGUI.openGUI((Player)sender, plugin);
        }
        return true;
    }
}
