package main;

import commands.GoodBoyePoints;
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
import java.util.LinkedHashMap;

import javax.security.auth.login.LoginException;

import commands.Command;
import commands.Help;

public class GoodBoyeBot extends ListenerAdapter {
	static JDA jda;
	static String key = Globals.KEY + "";
	private static final LinkedHashMap<String, Command> commands = Command.getCommandMap();
	public static ArrayList<GoodBoyeUser> users = new ArrayList<GoodBoyeUser>();
	
	
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
	
	private static void commandToMap() {
		Help help = new Help();
		GoodBoyePoints goodboyepoints = new GoodBoyePoints();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		TextChannel ch = event.getTextChannel();
		String messageContent = message.getContentRaw();
		if ((messageContent.startsWith(key) || channel.getName().equals("bot")) && !author.isBot()) {
			if (messageContent.startsWith(key)) messageContent = messageContent.substring(1);
			String[] args = messageContent.split(" ");
			for(int i = 0; i < users.size(); i++){
				if(author.getName().equals(users.get(i).getName())){
					users.get(i).givePoints(0.1);
				} else {
					users.add(new GoodBoyeUser(author.getName()));
				}
			}
			if (commands.containsKey(args[0])) {
				commands.get(args[0]).run(event, null);
			} else {
				channel.sendMessage("What the fuck did you just fucking say about me, you little bitch? I’ll have you know I graduated top of my class in the Navy Seals, and I’ve been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and I’m the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. You’re fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and that’s just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little “clever” comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldn’t, you didn’t, and now you’re paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. You’re fucking dead, kiddo.\n\n(That is not a command)").queue();
			}
		}
	}
}
