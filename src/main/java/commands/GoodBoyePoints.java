package commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GoodBoyePoints extends Command {

    public GoodBoyePoints() {
        super("goodboypoints");
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args){
        MessageChannel channel = event.getChannel();
    }

    @Override
    public String getUsage() {
        return getName();
    }

    @Override
    public String getDescription(){
        return "Displays the amount of 'Good Boy Points' the user who called the command has.";
    }

}
