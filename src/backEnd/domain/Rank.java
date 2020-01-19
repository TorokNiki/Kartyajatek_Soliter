package backEnd.domain;

public enum Rank implements Comparable<Rank>{
    ACE(1, "Ace","A"),
    TWO(2, "2", "2"),
    THREE(3, "3", "3"),
    FOUR(4, "4", "4"),
    FIVE(5, "5", "5"),
    SIX(6, "6", "6"),
    SEVEN(7, "7", "7"),
    EIGHT(8, "8", "8"),
    NINE(9, "9", "9"),
    TEN(10, "10", "10"),
    JACK(11, "Jack", "J"),
    QUEEN(12, "Queen", "Q"),
    KING(13, "King", "K");

    private int value;
    private String name;
    public String helper;

    Rank(int value, String name, String helper) {
        this.value = value;
        this.name = name;
        this.helper=helper;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
//
//    public int compareTo(Rank newRank){
//        if(value==newRank.value)
//            return 0;
//        else if(value>newRank.value)
//            return 1;
//        else
//            return -1;
//    }
//}
}
