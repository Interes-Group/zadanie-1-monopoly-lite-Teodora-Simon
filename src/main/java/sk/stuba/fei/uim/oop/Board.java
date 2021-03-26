package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board
{

    private ArrayList<Field> fields;
    private ArrayList<Player> players;
    private ArrayList<Card> cards;
    private double bank;


    public  Board(int size, int numPlayers, int cardNum)
    {
        bank=0;
        players = new ArrayList<Player>();
        initBoard(size);
        initPlayers(numPlayers);
        initCards(cardNum);

    }

    private void initPlayers(int numPlayers)
    {
        for(int i=0; i<numPlayers; i++)
        {
            String name;
            float money;
            name = ZKlavesnice.readString("Enter name of player:");
            money = 2000;
            Player p= new Player(name,money);
            players.add(p);

        }
    }

    private void initBoard(int size)
    {
        fields=new ArrayList<Field>(Collections.nCopies(size, null));
        //System.out.println(fields.size());
        Field startField= new Field(FieldType.Start);
        fields.set(0,startField);
        Field jailField = new Field(FieldType.Jail);
        fields.set(6,jailField);
        Field taxField = new Field(FieldType.Tax);
        fields.set(12,taxField);
        Field policeField = new Field(FieldType.Police);
        fields.set(18,policeField);

        for(int i=1;i<24;i+=6){
            CardField cardField = new CardField(FieldType.Chance);
            fields.set(i, cardField);
        }

        for(int i=0; i<24; i++)
        {
            if(fields.get(i)==null)
            {
                Random r= new Random();
                int randomNum = r.nextInt((1000 - 100) + 1) + 100;
                int fee=randomNum/2;
                PropertyField p= new PropertyField(FieldType.Property,randomNum,fee);
                fields.set(i,p);

            }
        }

    }

    private void initCards(int cardNum)
    {
        cards=new ArrayList<Card>();
        for(int i=0; i<cardNum; i++)
        {
            Card c;
            if(i==0)
            {
                 c= new Card(CardType.Penalty);
            }
            else if(i==1)
            {
                c= new Card(CardType.Luxury_Tax);
            }
            else if(i==2)
            {
                c= new Card(CardType.Bonus);
            }
            else if(i==3)
            {
                c= new Card(CardType.Bingo);
            }
            else
                {
                    c= new Card(CardType.Divorce);
                }

            cards.add(c);
        }
        Collections.shuffle(cards);

    }


    public void printPlayers()
    {
        for(Player p : players)
        {
            System.out.println(p);
        }

    }

    public void printBoard()
    {
       String up="";
       for(int i=0;i<7;i++){
           up = up + (fields.get(i)+"|");
       }

       System.out.println(up);

       int len=up.length();
       int diff=16;

       for(int i=23; i>18; i--)
       {
           String f1=fields.get(i).toString();
           String f2=fields.get(i-diff).toString();
           int spaces=len-f1.length()-f2.length();
           String s="";
           for(int j=0; j<spaces; j++)
           {
                s+=" ";
           }
           String line;
           line=f1+s+f2;
           System.out.println(line);
           diff-=2;
       }
       up="";
       for(int i=18; i>11; i--)
       {
           up = up + (fields.get(i)+"|");
       }

       System.out.println(up);

    }

    public void removePlayer(Player p)
    {
        for(int index: p.getProperties())
        {
            if(fields.get(index) instanceof  PropertyField)
            {
                PropertyField pf=(PropertyField)fields.get(index);
                pf.setPlayer(null);
            }
        }
        System.out.println("Player "+p.getName()+" has been removed from game");
        players.remove(p);


    }

    public void play(Player p)
    {
        if(p.getPause()>0)
        {
            System.out.println(p.getName() +" is in jail for "+p.getPause()+" turns");

            p.setPause(p.getPause()-1);
            return;
        }
        System.out.println(p.getName() +" is on turn!\n");

        Random r= new Random();
        int randomNum = r.nextInt((6 - 1) + 1) + 1;
        System.out.println("Player "+p.getName()+" got "+randomNum);
        int newPosition=p.getPosition()+randomNum;
        if(newPosition>23)
        {
            newPosition=newPosition%24;
            p.setMoney(p.getMoney()+200);
        }
        p.setPosition(newPosition);
        Field f=fields.get(p.getPosition());
        if(f.getType() == FieldType.Police)
        {
            int rNumber = r.nextInt((6 - 1) + 1) + 1;
            p.setPause(rNumber);
            p.setPosition(6);

        }
        else if(f.getType() == FieldType.Tax)
        {
            if(p.getMoney()<200)
            {
                removePlayer(p);
                return;
            }
            System.out.println(p.getName()+" is paying a tax!");
            p.setMoney(p.getMoney()-200);
            bank+=200;
            System.out.println("Bank poses " +bank+ " monies!");

        }
        else if(f.getType() == FieldType.Chance)
        {
           Card c= cards.get(0);
           cards.remove(0);

            if(c.getType() == CardType.Luxury_Tax)
            {
                System.out.println(p.getName()+" got a luxury tax!");
                float newMoney=(float)(p.getMoney()*0.8);
                p.setMoney(newMoney);
            }
            else if(c.getType() == CardType.Bonus)
            {
                System.out.println(p.getName()+" got a bonus!");
                p.setMoney(p.getMoney()+500);
            }
            else if(c.getType() == CardType.Penalty)
            {
                System.out.println(p.getName()+" got a penalty for parking!");
                if(p.getMoney()<200)
                {
                  removePlayer(p);
                }
                else
                    {
                    p.setMoney(p.getMoney() - 200);
                }
            }
            else if(c.getType() == CardType.Bingo)
            {
                System.out.println(p.getName()+" won a BINGO!");
                p.setMoney(p.getMoney()+1000);
            }
            else if(c.getType() == CardType.Divorce)
            {
                System.out.println(p.getName()+" had a divorce!");
                float newMoney=(float)(p.getMoney()*0.5);
                p.setMoney(newMoney);
            }
        }
        else if(f.getType() == FieldType.Property)
        {
            PropertyField pf=(PropertyField) f;
            if(pf.getPlayer()!=null)
            {
                if(p!=pf.getPlayer())
                {
                    if(p.getMoney()>pf.getFee())
                    {

                        Player owner=pf.getPlayer();
                        p.setMoney(p.getMoney()-(float)pf.getFee());
                        owner.setMoney(owner.getMoney()+(float)pf.getFee());

                    }
                    else
                    {
                        removePlayer(p);
                    }
                }
                return;
            }

            int option = ZKlavesnice.readInt("1. Buy a property \n2. Continue turn");
            if(option==1)
            {
                if((p.getMoney()>pf.getPrice())) {
                    p.setMoney(p.getMoney() - (float) pf.getPrice());
                    p.getProperties().add(p.getPosition());
                    pf.setPlayer(p);
                }
                else{
                    System.out.println(p.getName()+" dont have enough money!");
                }
            }
        }

    }

    public void Game()
    {
        labela:
        while (true) {
            for(int i=0;i<players.size();i++)
            {
                Player p=players.get(i);
                play(p);
                if(players.size()==1)
                {
                    System.out.println("The winner is: "+ players.get(0).getName());
                    break labela;
                }
                if(cards.size()==0)
                {
                    initCards(5);
                }

                printBoard();
                System.out.println("------------------------------------------------------------------------------");
                printPlayers();

                ZKlavesnice.readString("");

            }

        }
    }
}
