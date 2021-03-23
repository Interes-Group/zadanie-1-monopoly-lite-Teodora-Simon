package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class CardField extends Field{

    private int value;

    public CardField(FieldType type, int value) {
        super(type);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
