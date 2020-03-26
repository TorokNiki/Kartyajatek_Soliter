package backEnd.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum BackgroundImages {
    CAMOFLAGE("Terepminta", "bg01.jpg"),
    PURPLE("Lila", "bg02.png"),
    PINKBUBLE("Lilás körök", "bg03.jpg"),
    BLACK("Fekete", "bg04.jpg"),
    BASE("Bézs", "bg05.jpg"),
    LEAF("Levelek", "bg06.jpg"),
    KAKTUS("Kaktuszok", "bg07.jpg"),
    FLOWER("Virág", "bg08.jpg"),
    ORANGEBUBLE("Narancssárga körök", "bg09.jpg"),
    BROWN("Bézs", "bg10.png"),
    BLACKBASE("Fekete", "bg11.png"),
    ANCORE("Halász", "bg12.jpg"),
    BLUEBUBLE("Kékes körök", "bg13.jpg"),
    ORANGETRAPES("Narancssárga rombusz", "bg14.jpg"),
    BLUE("Kék csempe", "bg15.jpg"),
    MUPPIN("Muffin", "bg16.jpg"),
    HART("Szivek", "bg17.png"),
    FLOWERS("Virágok", "bg18.jpg"),
    LEMON("Citrom", "bg19.jpg"),
    MACARONE("Macarone", "bg20.jpg"),
    OWL("Bagoly", "bg21.jpg"),
    CRISHTMAS("Mézeskalács", "bg22.jpg"),
    CAT("Macska", "bg23.png"),
    ICECREAM("Fagyi", "bg24.png"),
    FISH("Halak", "bg25.png"),
    BLACKSQUER("Fekete csempe", "bg26.jpg"),
    GREENLEAF("Levél", "bg27.png"),
    PINK("Rozsaszin", "bg28.png"),
    PINKDOTS("Rozsaszin", "bg29.jpg");


    private static List<BackgroundImages> backgroundList;
    private String name;
    private String config;

    BackgroundImages(String name, String config) {
        this.name = name;
        this.config=config;
    }

    public static List<BackgroundImages> getBackground() {
        backgroundList = new ArrayList<>();
        backgroundList.add(BackgroundImages.CAMOFLAGE);
        backgroundList.add(BackgroundImages.PURPLE);
        backgroundList.add(BackgroundImages.PINKBUBLE);
        backgroundList.add(BackgroundImages.BLACK);
        backgroundList.add(BackgroundImages.BASE);
        backgroundList.add(BackgroundImages.LEAF);
        backgroundList.add(BackgroundImages.KAKTUS);
        backgroundList.add(BackgroundImages.FLOWER);
        backgroundList.add(BackgroundImages.ORANGEBUBLE);
        backgroundList.add(BackgroundImages.BROWN);
        backgroundList.add(BackgroundImages.BLACKBASE);
        backgroundList.add(BackgroundImages.ANCORE);
        backgroundList.add(BackgroundImages.BLUEBUBLE);
        backgroundList.add(BackgroundImages.ORANGETRAPES);
        backgroundList.add(BackgroundImages.BLUE);
        backgroundList.add(BackgroundImages.MUPPIN);
        backgroundList.add(BackgroundImages.HART);
        backgroundList.add(BackgroundImages.FLOWERS);
        backgroundList.add(BackgroundImages.LEMON);
        backgroundList.add(BackgroundImages.MACARONE);
        backgroundList.add(BackgroundImages.OWL);
        backgroundList.add(BackgroundImages.CRISHTMAS);
        backgroundList.add(BackgroundImages.CAT);
        backgroundList.add(BackgroundImages.ICECREAM);
        backgroundList.add(BackgroundImages.FISH);
        backgroundList.add(BackgroundImages.BLACKSQUER);
        backgroundList.add(BackgroundImages.GREENLEAF);
        backgroundList.add(BackgroundImages.PINK);
        backgroundList.add(BackgroundImages.PINKDOTS);

        return backgroundList;
    }

    public String getName() {
        return name;
    }

    public String getConfig() {
        return config;
    }
}
