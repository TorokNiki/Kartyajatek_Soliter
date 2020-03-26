package backEnd.domain.enums;

import java.util.ArrayList;
import java.util.List;


//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/Color.html
public enum Backgrounds {
    BLUE("Kék", "-fx-background-color: lightblue;","lightblue"),
    GREEN("Zöld", "-fx-background-color: LIGHTGREEN;","LIGHTGREEN"),
    RED("Piros", "-fx-background-color: TOMATO;","TOMATO"),
    GREY("Szürke", "-fx-background-color: SILVER;","SILVER"),
    PINK("Rozsaszin", "-fx-background-color: PINK;","PINK"),
    PURPLE("Lila", "-fx-background-color: PLUM;","PLUM"),
    WHITE("Fehér", "-fx-background-color: WHITE;","WHITE"),
    BROWN("Barna", "-fx-background-color: TAN;","TAN"),
    TURQUOISE("Türkiz", "-fx-background-color: #9debe2;","#9debe2");


    private static List<Backgrounds> backgroundsList;
    private String name;
    private String colour;
    private String config;

    Backgrounds(String name, String colour, String config) {
        this.name = name;
        this.colour = colour;
        this.config=config;
    }

    public static List<Backgrounds> getBackground() {
        backgroundsList = new ArrayList<>();
        backgroundsList.add(Backgrounds.BLUE);
        backgroundsList.add(Backgrounds.GREEN);
        backgroundsList.add(Backgrounds.RED);
        backgroundsList.add(Backgrounds.GREY);
        backgroundsList.add(Backgrounds.PINK);
        backgroundsList.add(Backgrounds.PURPLE);
        backgroundsList.add(Backgrounds.WHITE);
        backgroundsList.add(Backgrounds.BROWN);
        backgroundsList.add(Backgrounds.TURQUOISE);

        return backgroundsList;
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
