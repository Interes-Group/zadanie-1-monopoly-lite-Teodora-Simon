package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/*
<template Class T>
class ArrayList
{
          T  polje;

}
 */

public class Board
{

    private ArrayList<Field> fields;
    private ArrayList<Player> players;
    private ArrayList<Integer> cards;
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
            money = 1000;
            Player p= new Player(name,money);
            players.add(p);

        }

    }

    private void initBoard(int size)
    {
        fields=new ArrayList<Field>(Collections.nCopies(size, null));
        System.out.println(fields.size());
        Field startField= new Field(FieldType.Start);
        fields.set(0,startField);
        Field jailField = new Field(FieldType.Jail);
        fields.set(6,jailField);
        Field taxField = new Field(FieldType.Tax_payment);
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
                PropertyField p= new PropertyField(FieldType.Property,randomNum);
                fields.set(i,p);

            }
        }

    }

    private void initCards(int cardNum)
    {
        cards=new ArrayList<Integer>();
        for(int i=0; i<cardNum; i++)
        {
            cards.add(i);
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

    public void printCards()
    {
        for(int i=0; i<cards.size(); i++)
        {
            System.out.println(cards.get(i));
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

    public void Play(Player p)
    {
        if(p.getPause()>0)
        {
            p.setPause(p.getPause()-1);
            return;
        }
        Random r= new Random();
        int randomNum = r.nextInt((6 - 1) + 1) + 1;
        int newPosion=p.getPosition()+randomNum;
        if(newPosion>23)
        {
            newPosion=newPosion%24;
            p.setMoney(p.getMoney()+200);
        }
        p.setPosition(newPosion);
        Field f=fields.get(p.getPosition());
        if(f.getType() == FieldType.Police)
        {
            int rNumber = r.nextInt((6 - 1) + 1) + 1;
            p.setPause(rNumber);

        }
        else if(f.getType() == FieldType.Tax_payment)
        {
            p.setMoney(p.getMoney()-200);
            bank+=200;

        }
        else if(f.getType() == FieldType.Chance)
        {
           int i= cards.get(0);
           cards.remove(0);

            if(i==0)
            {
                float newMoney=(float)(p.getMoney()*0.8);
                p.setMoney(newMoney);
            }
            else if(i==1)
            {
                p.setMoney(p.getMoney()+500);
            }
            else if(i==2)
            {
                p.setMoney(p.getMoney()-200);
            }
            else if(i==3)
            {
                p.setPosition(0);
            }
            else if(i==4)
            {
                float newMoney=(float)(p.getMoney()*0.5);
                p.setMoney(newMoney);
            }


        }
        else if(f.getType() == FieldType.Property)
        {
            PropertyField pf=(PropertyField) f;
            if(pf.getPlayer()!=null)
            {
                if(p.getMoney()>pf.getPrice())
                {
                    Player owner=pf.getPlayer();
                    p.setMoney(p.getMoney()-(float)pf.getPrice());
                    pf.setPrice(pf.getPrice()+(float)pf.getPrice());

                }
                else
                    {
                        players.remove(p);
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

            }
        }



    }
    public void Game()
    {
        while (true) {
            for(int i=0;i>players.size();i++)
            {
                Player p=players.get(i);
                Play(p);
                printBoard();
                printPlayers();
                if(players.size()==1)
                {
                    break;
                }
                String s=ZKlavesnice.readString("");






            }

        }
    }



}
