package backEnd.domain;

import java.util.ArrayList;
import java.util.List;


//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
public enum Background {
    BLUE("Kék", "-fx-background-color: lightblue;","lightblue"),
    GREEN("Zöld", "-fx-background-color: LIGHTGREEN;","LIGHTGREEN"),
    RED("Piros", "-fx-background-color: TOMATO;","TOMATO"),
    GREY("Szürke", "-fx-background-color: SILVER;","SILVER"),
    PINK("Rozsaszin", "-fx-background-color: PINK;","PINK"),
    PURPLE("Lila", "-fx-background-color: PLUM;","PLUM"),
    WHITE("Fehér", "-fx-background-color: WHITE;","WHITE"),
    BROWN("Barna", "-fx-background-color: TAN;","TAN"),
    TURQUOISE("Türkiz", "-fx-background-color: #9debe2;","#9debe2");


    private static List<Background> backgroundList;
    private String name;
    private String colour;
    private String config;

    Background(String name, String colour,String config) {
        this.name = name;
        this.colour = colour;
        this.config=config;
    }

    public static List<Background> getBackground() {
        backgroundList = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public String getConfig() {
        return config;
    }

    public String getColour() {
        return colour;
    }

}
