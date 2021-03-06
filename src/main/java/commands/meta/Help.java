package commands.meta;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import commands.Command;

/**
 * @author Patrick Ubelhor
 * @version 8/15/2017
 */
public class Help extends Command {
	
	public Help() {
		super("help");
	}
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		MessageChannel channel = event.getChannel();
		StringBuilder msg = new StringBuilder("```\n");
		
		for (Command c : Command.getCommandMap().values()) {
			msg.append(String.format(
					"\t%s\n%s\n\n",
					c.getUsage(),
					c.getDescription()));
		}
		msg.append("```");
		
		channel.sendMessage(msg.toString()).queue();
	}
	
	
	@Override
	public String getUsage() {
		return getName();
	}
	
	
	@Override
	public String getDescription() {
		return "Prints a message containing all bot commands and their descriptions";
	}
	
}
