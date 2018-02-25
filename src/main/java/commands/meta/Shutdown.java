package commands.meta;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import commands.Command;
import main.GoodBoyeBot;

/**
 * @author Patrick Ubelhor
 * @version 8/15/2017
 */
public class Shutdown extends Command {
	
	public Shutdown() {
		super("shutdown");
	}
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		if (!GoodBoyeBot.users.get(event.getAuthor().getName()).isBotAdmin()) {
			event.getChannel().sendMessage("Permissions error. You are not admin for this bot").queue();
			return;
		}
		if (args.length == 1) {
			for (Command command : getCommandMap().values()) {
				command.end();
			}
			event.getChannel().sendMessage("Goodnight!").queue();
			event.getJDA().shutdown();
		}
	}
	
	
	@Override
	public String getUsage() {
		return getName();
	}
	
	
	@Override
	public String getDescription() {
		return "Safely shuts down the bot";
	}
	
}
