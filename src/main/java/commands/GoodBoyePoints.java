package commands;

import main.GoodBoyeBot;
import main.GoodBoyeUser;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

public class GoodBoyePoints extends Command {
	static final String POINT_HELP = "```Usage:\n\n" +
			"Give [name, int]: Adds an amount of 'Good Boye Points' to a user (Admin Only)\n\n" +
			"Take [name, int}: Removes an amount of 'Good Boye Points' from a user (Admin Only)\n\n" +
			"Balance: Prints out amount of 'Good Boye Points' you have```";


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
				GoodBoyeBot.users.get(args[2]).removePoints(Double.parseDouble(args[3]));
				break;
			case "give":
				GoodBoyeBot.users.get(args[2]).givePoints(Double.parseDouble(args[3]));
				break;
			case "balance":
				channel.sendMessage(GoodBoyeBot.users.get(author.getName()).getName() + " has " + GoodBoyeBot.users.get(author.getName()).getPoints() + " 'Good Boye Points'").queue();
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
