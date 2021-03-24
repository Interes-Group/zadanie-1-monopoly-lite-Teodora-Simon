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



    public  Board(int size, int numPlayers, int cardNum)
    {


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
       String gore="";
       for(int i=0;i<7;i++){
           gore = gore + (fields.get(i)+"|");
       }

        System.out.println(gore);
       int duzina=gore.length();
       int razlika=16;
       for(int i=23; i>18; i--)
       {
           String   f1=fields.get(i).toString();
           String f2=fields.get(i-razlika).toString();
           int spaces=duzina-f1.length()-f2.length();
           String s="";
           for(int j=0; j<spaces; j++)
           {
                s+=" ";
           }
           String line;
           line=f1+s+f2;
           System.out.println(line);
           razlika-=2;
       }
       gore="";
       for(int i=18; i>11; i--)
       {
           gore = gore + (fields.get(i)+"|");
       }

       System.out.println(gore);

    }

    public void Game()
    {
        while (true) {
            for(int i=0;i>players.size();i++){
                Random r= new Random();
                int randomNum = r.nextInt((6 - 1) + 1) + 1;
                Player p=players.get(i);
                p.setPosition(p.getPosition()+randomNum);
                fields.get(p.getPosition()).getType();




            }
        }
    }



}
