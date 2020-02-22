package backEnd.domain;

import java.util.ArrayList;
import java.util.List;

public enum Background {
    BLUE("Kék","-fx-background-color: lightblue;"),
    GREEN("Zöld","-fx-background-color: #9aff8f;"),
    RED("Piros","-fx-background-color: #fc6262;"),
    GREY("Szürke","-fx-background-color: #cfcaca;"),
    PINK("Rozsaszin","-fx-background-color: #f786e2;"),
    PURPLE("Lila","-fx-background-color: #e388f7;"),
    WHITE("Fehér","-fx-background-color: #ffffff;"),
    BROWN("Barna","-fx-background-color: #b89e76;"),
    TURQUOISE("Türkiz","-fx-background-color: #9debe2;");


    private String name;
    private String colour;
    private static List<Background> backgroundList;
    Background(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public static List<Background> getBackground(){
        backgroundList= new ArrayList<>();
        backgroundList.add(Background.BLUE);
        backgroundList.add(Background.GREEN);
        backgroundList.add(Background.RED);
        backgroundList.add(Background.GREY);
        backgroundList.add(Background.PINK);
        backgroundList.add(Background.PURPLE);
        backgroundList.add(Background.WHITE);
        backgroundList.add(Background.BROWN);
        backgroundList.add(Background.TURQUOISE);

        return backgroundList;
    }

}
