package commands.meta;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import commands.Command;
import main.GoodBoyeBot;
import main.GoodBoyeUser;

public class BotAdmin extends Command {
	static final String BOT_ADMIN_HELP = "```Usage:\n\n" +
			"add [String]: makes the specified user and admin\n\n" +
			"remove [String]: removes admin privileges from the selected user\n\n" +
			"adduser [String]: adds a user with the specified name (not yet implimented)\n\n" +
			"list: lists all bot admins```";
	
	
	public BotAdmin() {
		super("botadmin");
	}
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		if (!GoodBoyeBot.users.get(event.getAuthor().getName()).isBotAdmin()) {
			event.getChannel().sendMessage("Permissions error. You are not admin for this bot").queue();
			return;
		}
		if (args.length >= 2) {
			switch (args[1]) {
				case "help":
					event.getChannel().sendMessage(BOT_ADMIN_HELP).queue();
					break;
				case "add":
					for (int i = 3; i < args.length; i++) {
						args[2] += " " + args[i];
					}
					if (args.length >= 3 && GoodBoyeBot.users.containsKey(args[2])) {
						GoodBoyeBot.users.get(args[2]).setBotAdmin(true);
					} else {
						event.getChannel().sendMessage( args[2] + " is not a valid name.").queue();
					}
					break;
				case "remove":
					for (int i = 3; i < args.length; i++) {
						args[2] += " " + args[i];
					}
					if (args.length >= 3 && GoodBoyeBot.users.containsKey(args[2])) {
						GoodBoyeBot.users.get(args[2]).setBotAdmin(false);
					} else {
						event.getChannel().sendMessage(args[2] + "is not a valid name.").queue();
					}
					break;
				case "list":
					String output = "```Admins:\n";
					for (GoodBoyeUser user : GoodBoyeBot.users.values()) {
						if (user.isBotAdmin()){
							output += user.getName() + "\n";
						}
					}
					output += "```";
					event.getChannel().sendMessage(output).queue();
					break;
				default:
					event.getChannel().sendMessage("args[1] is not a valid command.").queue();
					break;
			}
			
			
		} else {
			event.getChannel().sendMessage("Arguments invalid. type \"botadmin help\" for more info.").queue();
			
		}
		
	}
	
	@Override
	public String getUsage() {
		return getName() + " [String] {String}";
	}
	
	@Override
	public String getDescription() {
		return "commands to modify bot admins (only can be run by BotAdmins)";
	}
}
