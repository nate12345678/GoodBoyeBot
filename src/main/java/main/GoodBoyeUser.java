package main;

import net.dv8tion.jda.core.entities.User;

public class GoodBoyeUser {
	
	private String name;
	private String userID;
	private double gbp;
	private boolean isBotAdmin;
	
	public GoodBoyeUser(User user) {
		this.name = user.getName();
		this.userID = user.getId();
		if (userID.equals("179701404028370944") || userID.equals("105122368061480960")) isBotAdmin = true;
		gbp = 0;
	}
	
	public void givePoints(double points) {
		gbp += points;
	}
	
	public void removePoints(double points) {
		gbp -= points;
	}
	
	public double getPoints() {
		return gbp;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isBotAdmin() {
		return isBotAdmin;
	}
	
	public void setBotAdmin(boolean botAdmin) {
		isBotAdmin = botAdmin;
	}
	
	public String getUserID() {
		return userID;
	}
}
