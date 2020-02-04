package backEnd.domain;

import java.util.ArrayList;
import java.util.List;

public enum Back {
    DEFAULT("Alap kártya","./resources/img/cards/hatlap/blue.png"),
    ANGRYBIRD("Angry Bird kártya","./resources/img/cards/hatlap/Angry.png"),
    RED("Piros kártya","./resources/img/cards/hatlap/Red.png"),
    ORANGE("Narancs sárga kártya","./resources/img/cards/hatlap/Orange.png"),
    BLUE("Kék kártya","./resources/img/cards/hatlap/Blue1.png"),
    BLUEBLACK("Kékes fekete kártya","./resources/img/cards/hatlap/Blue2.png"),
    BLUEWHITE("Kék és fehér kártya","./resources/img/cards/hatlap/WhiteAndBlue.png"),
    GREEN("Zöld kártya","./resources/img/cards/hatlap/Green.png"),
    GREENBLACK("Zöldes fekete kártya","./resources/img/cards/hatlap/Green1.png"),
    GREENWHITE("Zöld könyv kártya","./resources/img/cards/hatlap/GreenBook.png"),
    PURPLE("Lila könyv kártya","./resources/img/cards/hatlap/PurpleBook.png"),
    PURPLEBLACK("Lila kártya","./resources/img/cards/hatlap/BackOfPlaycard.png"),
    BLACKWHITE("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite4.png"),
    BLACKWHITE1("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite1.png"),
    BLACKWHITE2("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite2.png"),
    BLACKWHITE3("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite6.png"),
    BLACKWHITE4("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite7.png"),
    BLACKWHITE5("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite8.png"),
    BLACKWHITE6("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite9.png"),
    BLACK("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite5.png"),
    WHITE("Fekete fehér kártya","./resources/img/cards/hatlap/BlackAndWhite3.png"),
    GOLD("Arany és fekete kártya","./resources/img/cards/hatlap/BlackAndGold.png"),
    GOLDWHITE("Arany és fehér kártya","./resources/img/cards/hatlap/WhitAndGolg.png"),
    BROWN("Barna könyv kártya","./resources/img/cards/hatlap/BlueBook.png");
    private String name;
    private String picture;
    private static List<Back> backList;
    Back(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public static List<Back> getBackList(){
        backList= new ArrayList<>();
        backList.add(Back.DEFAULT);
        backList.add(Back.RED);
        backList.add(Back.ANGRYBIRD);
        backList.add(Back.BLUE);
        backList.add(Back.BLUEWHITE);
        backList.add(Back.BLUEBLACK);
        backList.add(Back.GOLD);
        backList.add(Back.GOLDWHITE);
        backList.add(Back.GREEN);
        backList.add(Back.GREENBLACK);
        backList.add(Back.GREENWHITE);
        backList.add(Back.PURPLE);
        backList.add(Back.PURPLEBLACK);
        backList.add(Back.BROWN);
        backList.add(Back.BLACK);
        backList.add(Back.BLACKWHITE);
        backList.add(Back.BLACKWHITE1);
        backList.add(Back.BLACKWHITE2);
        backList.add(Back.BLACKWHITE3);
        backList.add(Back.BLACKWHITE4);
        backList.add(Back.BLACKWHITE5);
        backList.add(Back.BLACKWHITE6);
        backList.add(Back.WHITE);
        backList.add(Back.ORANGE);
        return backList;
    }

}
