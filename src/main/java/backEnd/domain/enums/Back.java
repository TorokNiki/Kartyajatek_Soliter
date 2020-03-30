package backEnd.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum Back {
    DEFAULT("Alap kártya", "img/cards/hatlap/blue.png", "blue"),
    ANGRYBIRD("Angry Bird kártya", "img/cards/hatlap/Angry.png", "Angry"),
    RED("Piros kártya", "img/cards/hatlap/Red.png", "Red"),
    ORANGE("Narancs sárga kártya", "img/cards/hatlap/Orange.png", "Orange"),
    BLUE("Kék kártya", "img/cards/hatlap/Blue1.png", "Blue1"),
    BLUEBLACK("Kékes fekete kártya", "img/cards/hatlap/Blue2.png", "Blue2"),
    BLUEWHITE("Kék és fehér kártya", "img/cards/hatlap/WhiteAndBlue.png", "WhiteAndBlue"),
    GREEN("Zöld kártya", "img/cards/hatlap/Green.png", "Green"),
    GREENBLACK("Zöldes fekete kártya", "img/cards/hatlap/Green1.png", "Green1"),
    GREENWHITE("Zöld könyv kártya", "img/cards/hatlap/GreenBook.png", "GreenBook"),
    PURPLE("Lila könyv kártya", "img/cards/hatlap/PurpleBook.png", "PurpleBook"),
    PURPLEBLACK("Lila kártya", "img/cards/hatlap/BackOfPlaycard.png", "BackOfPlaycard"),
    BLACKWHITE("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite4.png", "BlackAndWhite4"),
    BLACKWHITE1("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite1.png", "BlackAndWhite1"),
    BLACKWHITE2("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite2.png", "BlackAndWhite2"),
    BLACKWHITE3("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite6.png", "BlackAndWhite6"),
    BLACKWHITE4("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite7.png", "BlackAndWhite7"),
    BLACKWHITE5("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite8.png", "BlackAndWhite8"),
    BLACKWHITE6("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite9.png", "BlackAndWhite9"),
    BLACK("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite5.png", "BlackAndWhite5"),
    WHITE("Fekete fehér kártya", "img/cards/hatlap/BlackAndWhite3.png", "BlackAndWhite3"),
    GOLD("Arany és fekete kártya", "img/cards/hatlap/BlackAndGold.png", "BlackAndGold"),
    GOLDWHITE("Arany és fehér kártya", "img/cards/hatlap/WhitAndGolg.png", "WhitAndGolg"),
    BROWN("Barna könyv kártya", "img/cards/hatlap/BlueBook.png", "BlueBook");
    private static List<Back> backList;
    private String name;
    private String picture;
    private String config;

    Back(String name, String picture, String config) {
        this.name = name;
        this.picture = picture;
        this.config = config;
    }

    public static List<Back> getBackList() {
        backList = new ArrayList<>();
        backList.add(Back.DEFAULT);
        backList.add(Back.RED);
        backList.add(Back.ANGRYBIRD);
        backList.add(Back.GOLD);
        backList.add(Back.GOLDWHITE);
        backList.add(Back.BROWN);
        backList.add(Back.BLACK);
        backList.add(Back.WHITE);
        backList.add(Back.BLACKWHITE);
        backList.add(Back.BLACKWHITE1);
        backList.add(Back.BLACKWHITE2);
        backList.add(Back.BLACKWHITE3);
        backList.add(Back.BLACKWHITE4);
        backList.add(Back.BLACKWHITE5);
        backList.add(Back.BLACKWHITE6);
        backList.add(Back.BLUEBLACK);
        backList.add(Back.BLUEWHITE);
        backList.add(Back.BLUE);
        backList.add(Back.PURPLE);
        backList.add(Back.PURPLEBLACK);
        backList.add(Back.ORANGE);
        backList.add(Back.GREEN);
        backList.add(Back.GREENBLACK);
        backList.add(Back.GREENWHITE);
        return backList;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getConfig() {
        return config;
    }

}
