package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
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

  //  (CardField)f.getValue();

    public  Board(int size, int numPlayers)
    {

        fields =  new ArrayList<Field>(size);
        players = new ArrayList<Player>();

        for(int i=0; i<numPlayers; i++)
        {

            String name;
            float money;
            name = ZKlavesnice.readString("Enter name of player:");
            money = ZKlavesnice.readInt("Enter the money he got:");
            Player p= new Player(name,money);
            players.add(p);

        }
    }


}
