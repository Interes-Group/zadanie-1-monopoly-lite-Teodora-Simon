package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class PropertyField extends Field{

    private double price;
    public PropertyField(FieldType type, double price) {
        super(type);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
