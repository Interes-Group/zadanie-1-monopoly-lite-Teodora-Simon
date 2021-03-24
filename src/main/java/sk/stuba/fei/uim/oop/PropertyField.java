package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class PropertyField extends Field{

    private double price;
    private Player player;
    public PropertyField(FieldType type, double price) {
        super(type);
        this.price = price;
        player=null;
    }

    public double getPrice() {
        return price;
    }
    public Player getPlayer() {return player;}
    public  void setPlayer(Player p){this.player=p;}

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getType()+" " + price;
    }
}
