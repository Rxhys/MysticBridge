package club.MysticPractice.MysticBridge.MinecraftSide;

import club.MysticPractice.MysticBridge.DiscraftBridge.Hashmap;
import club.MysticPractice.MysticBridge.DiscraftBridge.RankEnum;
import club.MysticPractice.MysticBridge.MainClass;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class MinecraftVerifyCommand implements CommandExecutor {

	public MainClass mainClass;

	public MinecraftVerifyCommand(MainClass mainClass) {
		this.mainClass = mainClass;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 1) {
				if (Hashmap.hashMap.containsKey(args[0])) {
					Player player = (Player) sender;
					LuckPermsApi api = LuckPerms.getApi();
					me.lucko.luckperms.api.User luckUser = api.getUser(player.getUniqueId());
					User user = Hashmap.hashMap.get(args[0]);
					Guild guild = MainClass.jda.getGuildById("613855107171221516");
					File folder = new File(mainClass.getDataFolder() + "/links/");
					File file = new File(mainClass.getDataFolder() + "/links/" + player.getUniqueId().toString() + ".yml");
					File discordFile = new File(mainClass.getDataFolder() + "/discordlinks/" + user.getId() + ".yml");
					File discordFolder = new File(mainClass.getDataFolder() + "/discordlinks/");
					if (!file.exists()) {
						try {
							if (!folder.exists()) {
								folder.mkdirs();
								folder.mkdir();
								file.createNewFile();
								FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
								fc.set("discordid", user.getId());
								fc.set("showtag", true);
								fc.save(file);
								sender.sendMessage(ChatColor.GREEN + "Linked the Discord account: " + user.getName() + " successfully!");
								switch (luckUser.getPrimaryGroup()) {
									case "default":
										setRoleBasedOffRank(MainClass.jda, RankEnum.DEFAULT, player, user);
										break;
									case "donor":
										setRoleBasedOffRank(MainClass.jda, RankEnum.DONOR, player, user);
										break;
									case "mystical":
										setRoleBasedOffRank(MainClass.jda, RankEnum.MYSTICAL, player, user);
										break;
									case "immortal":
										setRoleBasedOffRank(MainClass.jda, RankEnum.IMMORTAL, player, user);
										break;
									case "jrmod":
										setRoleBasedOffRank(MainClass.jda, RankEnum.JRMOD, player, user);
										break;
									case "srmod":
										setRoleBasedOffRank(MainClass.jda, RankEnum.SRMOD, player, user);
										break;
									case "admin":
										setRoleBasedOffRank(MainClass.jda, RankEnum.ADMIN, player, user);
										break;
									case "owner":
										setRoleBasedOffRank(MainClass.jda, RankEnum.OWNER, player, user);
										break;
									default:
										break;
								}
								if (discordFolder.exists()) {
									discordFile.createNewFile();
									FileConfiguration discordfc = YamlConfiguration.loadConfiguration(discordFile);
									discordfc.set("ingameuuid", ((Player) sender).getUniqueId().toString());
									discordfc.save(discordFile);
								} else {
									discordFolder.mkdirs();
									discordFolder.mkdir();
									discordFile.createNewFile();
									FileConfiguration discordfc = YamlConfiguration.loadConfiguration(discordFile);
									discordfc.set("ingameuuid", ((Player) sender).getUniqueId().toString());
									discordfc.save(discordFile);
								}
								Hashmap.hashMap.remove(args[0]);
								user.openPrivateChannel().queue((channel) -> channel.sendMessage("Successfully linked the account " + sender.getName() + "!").queue());
							} else {
								file.createNewFile();
								FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
								fc.set("discordid", user.getId());
								fc.set("showtag", true);
								fc.save(file);
								sender.sendMessage(ChatColor.GREEN + "Linked the Discord account: " + user.getName() + " successfully!");
								switch (luckUser.getPrimaryGroup()) {
									case "default":
										setRoleBasedOffRank(MainClass.jda, RankEnum.DEFAULT, player, user);
										break;
									case "donor":
										setRoleBasedOffRank(MainClass.jda, RankEnum.DONOR, player, user);
										break;
									case "mystical":
										setRoleBasedOffRank(MainClass.jda, RankEnum.MYSTICAL, player, user);
										break;
									case "immortal":
										setRoleBasedOffRank(MainClass.jda, RankEnum.IMMORTAL, player, user);
										break;
									case "jrmod":
										setRoleBasedOffRank(MainClass.jda, RankEnum.JRMOD, player, user);
										break;
									case "srmod":
										setRoleBasedOffRank(MainClass.jda, RankEnum.SRMOD, player, user);
										break;
									case "admin":
										setRoleBasedOffRank(MainClass.jda, RankEnum.ADMIN, player, user);
										break;
									case "owner":
										setRoleBasedOffRank(MainClass.jda, RankEnum.OWNER, player, user);
										break;
									default:
										break;
								}
								if (discordFolder.exists()) {
									discordFile.createNewFile();
									FileConfiguration discordfc = YamlConfiguration.loadConfiguration(discordFile);
									discordfc.set("ingameuuid", ((Player) sender).getUniqueId().toString());
									discordfc.save(discordFile);
								} else {
									discordFolder.mkdirs();
									discordFolder.mkdir();
									discordFile.createNewFile();
									FileConfiguration discordfc = YamlConfiguration.loadConfiguration(discordFile);
									discordfc.set("ingameuuid", ((Player) sender).getUniqueId().toString());
									discordfc.save(discordFile);
								}
								Hashmap.hashMap.remove(args[0]);
								user.openPrivateChannel().queue((channel) -> channel.sendMessage("Successfully linked the account " + sender.getName() + "!").queue());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
						if (file.exists()) {
							User discordUser = MainClass.jda.getUserById(fc.getString("discordid"));
							player.sendMessage(ChatColor.DARK_RED + "The user " + discordUser.getName() + " is already linked to the Minecraft account: " + player.getName() + "!");
						}
					}
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "That code is invalid, try regenerating it.");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Invalid arguments: Use /verify <code>");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_RED + "You must be a player in order to link a Discord account!");
		}
		return true;
	}

	public void setRoleBasedOffRank(JDA jda, RankEnum rankEnum, Player player, User user) {
		//LuckPermsApi api = LuckPerms.getApi();
		//me.lucko.luckperms.api.User user = api.getUser(player.getUniqueId());
		//user.getPrimaryGroup()
		Guild guild = jda.getGuildById("613855107171221516");
		Member member = guild.getMember(user);
		switch (rankEnum) {
			case DEFAULT:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Default] " + player.getName()).complete();
				break;
			case DONOR:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Donor]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Donor] " + player.getName()).complete();
				break;
			case MYSTICAL:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Mystical]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Mystical] " + player.getName()).complete();
				break;
			case IMMORTAL:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Immortal]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Immortal] " + player.getName()).complete();
				break;
			case JRMOD:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Jr.Mod]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Jr.Mod] " + player.getName()).complete();
				break;
			case SRMOD:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Sr.Mod]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Sr.Mod] " + player.getName()).complete();
				break;
			case ADMIN:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Admin]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Admin] " + player.getName()).complete();
				break;
			case OWNER:
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Owner]", true)).complete();
				guild.getController().addRolesToMember(member, jda.getRolesByName("[Default]", true)).complete();
				guild.getController().setNickname(guild.getMember(user), "[Owner] " + player.getName()).complete();
				break;
			default:
				break;
		}
	}
}
