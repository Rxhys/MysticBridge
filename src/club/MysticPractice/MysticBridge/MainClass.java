package club.MysticPractice.MysticBridge;

import club.MysticPractice.MysticBridge.DiscordSide.DiscordVerifyCommand;
import club.MysticPractice.MysticBridge.MinecraftSide.DiscordSettings;
import club.MysticPractice.MysticBridge.MinecraftSide.MinecraftChat;
import club.MysticPractice.MysticBridge.MinecraftSide.MinecraftInvClick;
import club.MysticPractice.MysticBridge.MinecraftSide.MinecraftVerifyCommand;
import net.dv8tion.jda.core.AccountType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class MainClass extends JavaPlugin {

	public static JDA jda = null;

	public void onEnable() {
		initDiscordBot();
		initSpigot();
	}

	public void initSpigot() {
		Bukkit.getServer().getPluginManager().registerEvents(new MinecraftChat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MinecraftInvClick(this), this);
		getCommand("verify").setExecutor(new MinecraftVerifyCommand(this));
		getCommand("discordsettings").setExecutor(new DiscordSettings(this));
		MinecraftChat.refreshVault();
	}
	
	public void initDiscordBot() {
		try {
			jda = new JDABuilder(AccountType.BOT)
					.setToken("NjA1NDYwNTk0NjY5MTI1NzA3.XT81Tw.Azpje5_VbH7v0lIHJ1-bFr0wbR4")
					.build();

			jda.getEventManager().register(new DiscordVerifyCommand(this));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
