package commands;

import main.GoodBoyeBot;
import main.GoodBoyeUser;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

public class GoodBoyePoints extends Command {
	static final String POINT_HELP = "```Usage:\n\n" +
			"Balance: Prints out amount of 'Good Boye Points' you have\n\n" +
			"Show [name]: Displays the amount of 'Good Boye Points' of a user\n\n" +
			"Give [int, String]: Adds an amount of 'Good Boye Points' to a user (Admin Only)\n\n" +
			"Take [int, String]: Removes an amount of 'Good Boye Points' from a user (Admin Only)```";


	public GoodBoyePoints() {
		super("goodboyepoints");
	}
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		MessageChannel channel = event.getChannel();
		User author = event.getAuthor();
		switch(args[1]){
			case "help":
				event.getChannel().sendMessage(POINT_HELP).queue();
				break;
			case "take":
				for (int i = 4; i < args.length; i++) {
					args[3] += " " + args[i];
				}
				if (GoodBoyeBot.users.get(author.getName()).isBotAdmin()) {
					GoodBoyeBot.users.get(args[3]).removePoints(Double.parseDouble(args[2]));
				} else {
					channel.sendMessage("Permissions error. You are not admin for this bot");
				}
				break;
			case "give":
				for (int i = 4; i < args.length; i++) {
					args[3] += " " + args[i];
				}
				if(GoodBoyeBot.users.get(author.getName()).isBotAdmin()) {
					GoodBoyeBot.users.get(args[3]).givePoints(Double.parseDouble(args[2]));
				} else {
					channel.sendMessage("Permissions error. You are not admin for this bot");
				}
				break;
			case "balance":
				channel.sendMessage(GoodBoyeBot.users.get(author.getName()).getName() + " has " + String.format("%.1f", GoodBoyeBot.users.get(author.getName()).getPoints()) + " 'Good Boye Points'").queue();
				break;
			case "show":
				for (int i = 3; i < args.length; i++) {
					args[2] += " " + args[i];
				}
				channel.sendMessage(GoodBoyeBot.users.get(args[2]).getName() + " has " + String.format("%.1f", GoodBoyeBot.users.get(args[2]).getPoints()) + " 'Good Boye Points'").queue();
				break;
			default:
				event.getChannel().sendMessage("Arguments invalid. type \"goodboyepoints help\" for more info.").queue();
		}
		if (args.length == 1) {
			channel.sendMessage(GoodBoyeBot.users.get(author.getName()).getName() + " has " + GoodBoyeBot.users.get(author.getName()).getPoints() + " 'Good Boye Points'").queue();
		}
	}
	
	
	@Override
	public String getUsage() {
		return getName();
	}
	
	
	@Override
	public String getDescription() {
		return "Displays the amount of 'Good Boy Points' the user who called the command has.";
	}
	
}
