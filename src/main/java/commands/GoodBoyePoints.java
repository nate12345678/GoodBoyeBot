package commands;

import main.GoodBoyeBot;
import main.GoodBoyeUser;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

public class GoodBoyePoints extends Command {
	
	
	public GoodBoyePoints() {
		super("goodboyepoints");
	}
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		MessageChannel channel = event.getChannel();
		User author = event.getAuthor();
		if (args.length > 0) {
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
