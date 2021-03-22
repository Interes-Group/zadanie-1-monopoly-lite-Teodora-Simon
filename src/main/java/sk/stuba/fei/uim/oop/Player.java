package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
//FieldType
// Field
//ProperyField, CardField,CornerField


public class Player
{
    private String name;
    private float money;
    private ArrayList<Integer> positions;
    private int pause;

    public Player(String name, float money)
    {
        this.name=name;
        this.money=money;
        this.positions= new ArrayList<Integer>();
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

    public ArrayList<Integer> getPositions()
    {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions){
        this.positions=positions;
    }


}
