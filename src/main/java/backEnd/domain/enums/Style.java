package backEnd.domain.enums;

public enum Style implements Comparable<Style> {
    HEARTS(1, "Hearts", 'H'),
    DIAMONDS(2, "Diamonds", 'D'),
    SPADES(3, "Spades", 'S'),
    CLUBS(4, "Clubs", 'C');

    public char helper;
    private int value;
    private String name;

    Style(int value, String name, char helper) {
        this.value = value;
        this.name = name;
        this.helper = helper;
    }

    public int getValue() {
        return value;
    }

    public char getHelper() {
        return helper;
    }

    public String getName() {
        return name;
    }
}
