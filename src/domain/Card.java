package domain;

import javax.swing.*;
import java.awt.*;

public class Card {

    private int value;
    private boolean isChoose;
    private Type type;

    public Card(int value, Type type) {
        super();
        this.value = value;
        this.type = type;
    }

    public Card(int value, boolean isChoose, Type type) {
        super();
        this.value = value;
        this.isChoose = isChoose;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Card)) {
            return false;
        }

        Card card = (Card) obj;
        return (card.value == value
                && card.type.ordinal() == type.ordinal());
    }

    @Override
    public int hashCode() {

        int result = 17;
        int valuesOfIsChoose = (isChoose ? 1 : 0);
        int valuesOfNumber = value;
        int valuesOfType;

        if (type.ordinal() == Type.DIAMONDS.ordinal()) {

            valuesOfType = 18;
        } else if (type.ordinal() == Type.HEARTS.ordinal()) {

            valuesOfType = 19;
        } else if (type.ordinal() == Type.SPADES.ordinal()) {

            valuesOfType = 23;
        } else if (type.ordinal() == Type.CLUBS.ordinal()) {

            valuesOfType = 29;
        } else {

            valuesOfType = 31;
        }

        result = 31 * result + valuesOfIsChoose;
        result = 31 * result + valuesOfNumber;
        result = 31 * result + valuesOfType;

        return result;
    }
}
