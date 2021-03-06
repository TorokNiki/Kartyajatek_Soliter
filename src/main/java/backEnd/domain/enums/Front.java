package backEnd.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum  Front {
    DECK0("Alapértelmezett", "img/cards/paklik/pakli4/KD.png", "img/cards/paklik/pakli4/AD.png", "img/cards/paklik/pakli4/2D.png","pakli4"),
    DECK1("Alap kicsit dekoratívan", "img/cards/paklik/pakli11/KD.png", "img/cards/paklik/pakli11/AD.png", "img/cards/paklik/pakli11/2D.png","pakli11"),
    DECK2("Angry Birds", "img/cards/paklik/pakli5/KD.png", "img/cards/paklik/pakli5/AD.png", "img/cards/paklik/pakli5/2D.png","pakli5"),
    DECK3("ChunkFive", "img/cards/paklik/pakli7/KD.png", "img/cards/paklik/pakli7/AD.png", "img/cards/paklik/pakli7/2D.png","pakli7"),
    DECK4("Díszes", "img/cards/paklik/pakli3/KD.png", "img/cards/paklik/pakli3/AD.png", "img/cards/paklik/pakli3/2D.png","pakli3"),
    DECK5("Díszes világos", "img/cards/paklik/pakli12/KD.png", "img/cards/paklik/pakli12/AD.png", "img/cards/paklik/pakli12/2D.png","pakli12"),
    DECK6("Díszes sötét", "img/cards/paklik/pakli13/KD.png", "img/cards/paklik/pakli13/AD.png", "img/cards/paklik/pakli13/2D.png","pakli13"),
    DECK7("Fekete Fehér csillogós", "img/cards/paklik/pakli2/KD.png", "img/cards/paklik/pakli2/AD.png", "img/cards/paklik/pakli2/2D.png","pakli2"),
    DECK8("Gotikus", "img/cards/paklik/pakli8/KD.png", "img/cards/paklik/pakli8/AD.png", "img/cards/paklik/pakli8/2D.png","pakli8"),
    DECK9("Gyerek", "img/cards/paklik/pakli9/KD.png", "img/cards/paklik/pakli9/AD.png", "img/cards/paklik/pakli9/2D.png","pakli9"),
    DECK10("Gyémánt", "img/cards/paklik/pakli10/KD.png", "img/cards/paklik/pakli10/AD.png", "img/cards/paklik/pakli10/2D.png","pakli10"),
    DECK11("Lilás Kék", "img/cards/paklik/pakli0/KD.png", "img/cards/paklik/pakli0/AD.png", "img/cards/paklik/pakli0/2D.png","pakli0"),
    DECK12("Normál csillogós", "img/cards/paklik/pakli1/KD.png", "img/cards/paklik/pakli1/AD.png", "img/cards/paklik/pakli1/2D.png","pakli1"),
    DECK13("Normál díszesen", "img/cards/paklik/pakli6/KD.png", "img/cards/paklik/pakli6/AD.png", "img/cards/paklik/pakli6/2D.png","pakli6"),
    DECK14("Narancssárga", "img/cards/paklik/pakli14/KD.png", "img/cards/paklik/pakli14/AD.png", "img/cards/paklik/pakli14/2D.png","pakli14"),
    DECK15("Rajzolt hatásu", "img/cards/paklik/pakli15/KD.png", "img/cards/paklik/pakli15/AD.png", "img/cards/paklik/pakli15/2D.png","pakli15");

    private String name;
    private String picture1,picture2,picture3;
    private static List<Front> frontList;
    private String config;
    Front( String name, String picture1,String picture2,String picture3,String config) {
        this.name = name;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.config=config;

    }

    public String getName() {
        return name;
    }

    public String getPicture1() {
        return picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public String getConfig() {
        return config;
    }

    public static List<Front> getFrontList(){
        frontList= new ArrayList<>();
        frontList.add(Front.DECK0);
        frontList.add(Front.DECK1);
        frontList.add(Front.DECK2);
        frontList.add(Front.DECK3);
        frontList.add(Front.DECK4);
        frontList.add(Front.DECK5);
        frontList.add(Front.DECK6);
        frontList.add(Front.DECK7);
        frontList.add(Front.DECK8);
        frontList.add(Front.DECK9);
        frontList.add(Front.DECK10);
        frontList.add(Front.DECK11);
        frontList.add(Front.DECK12);
        frontList.add(Front.DECK13);
        frontList.add(Front.DECK14);
        frontList.add(Front.DECK15);
        return frontList;
    }
}
