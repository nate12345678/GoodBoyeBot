package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.File;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;


/**
 * @author Nate Post
 * Last Updated: 2/22/2018
 */
public class MemeBank extends Command {
	static final String SPANK_HELP = "```Usage:\n\n" +
			"Withdraw [int]: returns specified memebank image, or random if no int specified\n\n" +
			"Deposit: deposits image attached to command in the meme bank\n\n" +
			"Balance: returns the number of images in the meme bank\n\n" +
			"Remove [int]: removes image from the meme bank (must be bot admin to call this command)```";
	
	List<File> pics;
	
	public MemeBank() {
		super("memebank");
		pics = Arrays.asList((new File("data/memebank").listFiles()));
		
	}
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		if (args.length < 2) {
			event.getChannel().sendMessage("Arguments invalid. type \"memebank help\" for more info.").queue();
		}
		switch (args[1]) {
			case "help":
				event.getChannel().sendMessage(SPANK_HELP).queue();
				break;
			case "withdraw":
				if (args.length > 2) {
					try {
						int imageChoice = Integer.parseInt(args[2]);
						if (imageChoice >= pics.size()) throw new IndexOutOfBoundsException();
						event.getChannel().sendFile(pics.get(imageChoice)).queue();
					} catch (Exception e) {
						event.getChannel().sendMessage("arg[2] is not a valid integer.").queue();
					}
				} else {
					event.getChannel().sendFile(pics.get((int) ((Math.random()) * (double) pics.size()))).queue();
				}
				break;
			case "deposit":
				if (event.getMessage().getAttachments().size() != 0 &&
						event.getMessage().getAttachments().get(0).isImage()) {
					File newImage = new File("data/memebank/" + Instant.now().toString() + ".png");
					event.getMessage().getAttachments().get(0).download(newImage);
					pics.add(newImage);
					event.getChannel().sendMessage("Meme successfully added.").queue();
				}
				break;
			case "balance":
				event.getChannel().sendMessage("There are " + pics.size() + " images in the meme bank.").queue();
				break;
			case "remove":
				try {
					int imageChoice = Integer.parseInt(args[2]);
					if (imageChoice >= pics.size()) throw new IndexOutOfBoundsException();
					if (pics.get(imageChoice).delete()) {
						event.getChannel().sendMessage("Image successfully removed.").queue();
					} else throw new Exception();
				} catch (Exception e) {
					event.getChannel().sendMessage("arg[2] is not a valid integer.").queue();
				}
				break;
			default:
				event.getChannel().sendMessage("Arguments invalid. type \"memebank help\" for more info.").queue();
		}
	}
	
	
	@Override
	public String getUsage() {
		return getName();
	}
	
	
	@Override
	public String getDescription() {
		return "The community back of dank memes";
	}
}
