package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

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
		event.getJDA().shutdown();
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
