package club.MysticPractice.MysticBridge.DiscordSide;

import club.MysticPractice.MysticBridge.DiscraftBridge.Hashmap;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public class DiscordVerifyCommand extends ListenerAdapter {

	public JavaPlugin plugin;
	public DiscordVerifyCommand(JavaPlugin plugin) {this.plugin = plugin;}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if (!event.getAuthor().isBot()) {
			if (event.getMessage().getContentRaw().equalsIgnoreCase("m!linkmc")) {
				File discordFile = new File(plugin.getDataFolder() + "/discordlinks/" + event.getAuthor().getId() + ".yml");
				if (discordFile.exists()) {
					FileConfiguration fc = YamlConfiguration.loadConfiguration(discordFile);
					UUID playerUUID = UUID.fromString(fc.getString("ingameuuid"));
					event.getChannel().sendMessage("Your Discord account is already linked with the Minecraft account: " + Bukkit.getOfflinePlayer(playerUUID).getName() + ".").queue();
				} else {
					String playerCode = getAlphaNumericString(6);
					Hashmap.hashMap.put(playerCode, event.getAuthor());
					event.getChannel().sendMessage("Your code is: '" + playerCode + "' use this code in game to link your accounts.").queue();
				}
			}
		}
	}

	public String getAlphaNumericString(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString
					.charAt(index));
		}
		return sb.toString();
	}
}
