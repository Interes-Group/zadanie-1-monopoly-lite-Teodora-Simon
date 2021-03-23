package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Field {

    private FieldType type;
    private ArrayList<Integer> player;

    public Field(FieldType type, ArrayList<Integer> player) {
        this.type = type;
        this.player = player;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public ArrayList<Integer> getPlayer() {
        return player;
    }

    public void setPlayer(ArrayList<Integer> player) {
        this.player = player;
    }

}
