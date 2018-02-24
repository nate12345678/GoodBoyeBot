package main;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class GoodBoyeBot {
	static JDA jda;
	
	public static void main(String[] args) {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(Globals.DISCORD_TOKEN).buildBlocking();
		} catch (LoginException le) {
			le.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
