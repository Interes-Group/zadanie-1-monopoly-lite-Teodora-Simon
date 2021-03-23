package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Player
{
    private String name;
    private float money;
    private int position;
    private ArrayList<Integer> properties;
    private int pause;

    public Player(String name, float money)
    {
        this.name=name;
        this.money=money;
        this.properties= new ArrayList<Integer>();
        position=0;
        pause=0;
    }

    public int getPause()
    {
        return pause;
    }

    public void setPause(int p)
    {
        pause=p;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public float getMoney()
    {
        return money;
    }

    public void setMoney(float money)
    {
        this.money=money;
    }

    public ArrayList<Integer> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<Integer> positions){
        this.properties=positions;
    }

    @Override
    public String toString() {
        return "Player: "  + name + " have money: " + money + "have properties=" + properties;
    }
}
