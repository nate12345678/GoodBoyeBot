package main;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.security.auth.login.LoginException;

import commands.Command;
import commands.Help;
import commands.MemeBank;
import commands.Shutdown;
import commands.SpankBank;
import commands.GoodBoyePoints;


/**
 * @author Nate Post, Armaan Shah
 * Last updated: 2/24/18
 */
public class GoodBoyeBot extends ListenerAdapter {
	static JDA jda;
	
	static String key = Globals.KEY + "";
	
	private static final LinkedHashMap<String, Command> commands = Command.getCommandMap();
	public static HashMap<String, GoodBoyeUser> users = new HashMap<>();
	
	
	public static void main(String[] args) {
		commandToMap();
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(Globals.DISCORD_TOKEN).buildBlocking();
			jda.addEventListener(new GoodBoyeBot());
		} catch (LoginException le) {
			le.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	
	/**
	 * adds all commands to the hashmap we use to call commands
	 */
	private static void commandToMap() {
		Help help = new Help();
		GoodBoyePoints goodboyepoints = new GoodBoyePoints();
		SpankBank spankBank = new SpankBank();
		MemeBank memeBank = new MemeBank();
		Shutdown shutdown = new Shutdown();
	}
	
	
	/**
	 * Method that is called every time a message is sent to the server
	 *
	 * @param event the event call that triggered this method
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		//get message info
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		TextChannel ch = event.getTextChannel();
		String messageContent = message.getContentRaw();
		
		if ((messageContent.startsWith(key) || channel.getName().equals("bot")) && !author.isBot()) {
			if (messageContent.startsWith(key)) messageContent = messageContent.substring(1);
			String[] args = messageContent.split(" ");
			if (users.containsKey(author.getName())) {
				users.get(author.getName()).givePoints(0.1);
			} else {
				users.put(author.getName(), new GoodBoyeUser(author.getName()));
				users.get(author.getName()).givePoints(.1);
			}
			if (commands.containsKey(args[0])) {
				commands.get(args[0]).run(event, args);
			} else {
				channel.sendMessage(Globals.NO_COMMAND_MESSAGE).queue();
			}
		}
	}
}
