package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Collections;
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

  //  (CardField)f.getValue();

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
        System.out.print("zmena");
    }

}
