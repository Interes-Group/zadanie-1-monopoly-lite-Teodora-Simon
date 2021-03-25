package sk.stuba.fei.uim.oop;
//printBoard
//define propery fields
// logika

public class Assignment1 {
    public static void main(String[] args) {

        Board b= new Board(24,ZKlavesnice.readInt("Enter the number of players: "),5);
        b.Game();


    }
}