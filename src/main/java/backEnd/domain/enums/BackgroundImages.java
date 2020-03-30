package backEnd.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum BackgroundImages {
    BRENC("Ágak", "bg31.jpg"),
    DREAM("Álomfogó", "bg32.png"),
    BROWNOWL("Baglyok", "bg34.png"),
    OWL("Bagoly", "bg21.jpg"),
    BASE("Bézs", "bg05.jpg"),
    BROWN("Bézs", "bg10.png"),
    LEMON("Citrom", "bg19.jpg"),
    BLACK("Fekete", "bg04.jpg"),
    BLACKBASE("Fekete", "bg11.png"),
    ICECREAM("Fagyi", "bg24.png"),
    BLACKSQUER("Fekete csempe", "bg26.jpg"),
    CAMOFLAGE("Terepminta", "bg01.jpg"),
    ANCORE("Halász", "bg12.jpg"),
    FISH("Halak", "bg25.png"),
    BLUE("Kék csempe", "bg15.jpg"),
    BLUEHART("Kék szív", "bg33.jpg"),
    BLUES("Kék paca", "bg35.jpg"),
    BLUEFLOWER("Kék virágok", "bg30.jpg"),
    BLUEBUBLE("Kékes körök", "bg13.jpg"),
    BLUEDOTS("Kékes szürke", "bg37.png"),
    LEAF("Levelek", "bg06.jpg"),
    GREENLEAF("Levél", "bg27.png"),
    PURPLE("Lila", "bg02.png"),
    PINKBUBLE("Lilás körök", "bg03.jpg"),
    MACARONE("Macarone", "bg20.jpg"),
    CAT("Macska", "bg23.png"),
    KAKTUS("Kaktuszok", "bg07.jpg"),
    CRISHTMAS("Mézeskalács", "bg22.jpg"),
    MUPPIN("Muffin", "bg16.jpg"),
    ORANGEBUBLE("Narancssárga körök", "bg09.jpg"),
    ORANGETRAPES("Narancssárga rombusz", "bg14.jpg"),
    PINK("Rozsaszin", "bg28.png"),
    PINKDOTS("Rozsaszin", "bg29.jpg"),
    DRAGONFLY("Szitakötő", "bg36.png"),
    HART("Szivek", "bg17.png"),
    SZILVER("Szürke", "bg38.jpg"),
    FLOWER("Virág", "bg08.jpg"),
    FLOWERS("Virágok", "bg18.jpg");


    private static List<BackgroundImages> backgroundList;
    private String name;
    private String config;

    BackgroundImages(String name, String config) {
        this.name = name;
        this.config=config;
    }

    public static List<BackgroundImages> getBackground() {
        backgroundList = new ArrayList<>();
        backgroundList.add(BackgroundImages.BRENC);
        backgroundList.add(BackgroundImages.DREAM);
        backgroundList.add(BackgroundImages.BROWNOWL);
        backgroundList.add(BackgroundImages.OWL);
        backgroundList.add(BackgroundImages.BASE);
        backgroundList.add(BackgroundImages.BROWN);
        backgroundList.add(BackgroundImages.LEMON);
        backgroundList.add(BackgroundImages.BLACK);
        backgroundList.add(BackgroundImages.ICECREAM);
        backgroundList.add(BackgroundImages.BLACKBASE);
        backgroundList.add(BackgroundImages.BLACKSQUER);
        backgroundList.add(BackgroundImages.CAMOFLAGE);
        backgroundList.add(BackgroundImages.ANCORE);
        backgroundList.add(BackgroundImages.FISH);
        backgroundList.add(BackgroundImages.BLUE);
        backgroundList.add(BackgroundImages.BLUEHART);
        backgroundList.add(BackgroundImages.BLUES);
        backgroundList.add(BackgroundImages.BLUEFLOWER);
        backgroundList.add(BackgroundImages.BLUEBUBLE);
        backgroundList.add(BackgroundImages.LEAF);
        backgroundList.add(BackgroundImages.GREENLEAF);
        backgroundList.add(BackgroundImages.PURPLE);
        backgroundList.add(BackgroundImages.PINKBUBLE);
        backgroundList.add(BackgroundImages.MACARONE);
        backgroundList.add(BackgroundImages.CAT);
        backgroundList.add(BackgroundImages.KAKTUS);
        backgroundList.add(BackgroundImages.CRISHTMAS);
        backgroundList.add(BackgroundImages.MUPPIN);
        backgroundList.add(BackgroundImages.ORANGEBUBLE);
        backgroundList.add(BackgroundImages.ORANGETRAPES);
        backgroundList.add(BackgroundImages.PINK);
        backgroundList.add(BackgroundImages.PINKDOTS);
        backgroundList.add(BackgroundImages.DRAGONFLY);
        backgroundList.add(BackgroundImages.HART);
        backgroundList.add(BackgroundImages.SZILVER);
        backgroundList.add(BackgroundImages.FLOWER);
        backgroundList.add(BackgroundImages.FLOWERS);

        return backgroundList;
    }

    public String getName() {
        return name;
    }

    public String getConfig() {
        return config;
    }
}
