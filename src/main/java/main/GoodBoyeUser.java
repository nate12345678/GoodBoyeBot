package main;

public class GoodBoyeUser {

    private String name;
    private double gbp;

    public GoodBoyeUser(String name){
        this.name = name;
        gbp = 0;
    }

    public void givePoints(double points){
        gbp += points;
    }

    public void removePoints(double points){
        gbp -= points;
    }

    public double getPoints(){
        return gbp;
    }

    public String getName(){
        return name;
    }
}
