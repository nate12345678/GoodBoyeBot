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

import javax.security.auth.login.LoginException;

public class GoodBoyeBot extends ListenerAdapter {
	static JDA jda;
	static String key = Globals.KEY + "";
	
	public static void main(String[] args) {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(Globals.DISCORD_TOKEN).buildBlocking();
			jda.addEventListener(new GoodBoyeBot());
		} catch (LoginException le) {
			le.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		TextChannel ch = event.getTextChannel();
		String messageContent = message.getContentRaw();
		if((messageContent.startsWith(key) || channel.getName().equals("bot"))&& !author.isBot()) channel.sendMessage("Hello, " + author.getName() + "!").queue();
	}
}
