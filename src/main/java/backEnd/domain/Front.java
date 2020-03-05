package backEnd.domain;

import java.util.ArrayList;
import java.util.List;

public enum  Front {
    DECK1("img/cards/paklik/pakli1/","Normál csillogós", "img/cards/paklik/pakli1/KD.png", "img/cards/paklik/pakli1/AD.png", "img/cards/paklik/pakli1/2D.png","pakli1"),
    DECK2("img/cards/paklik/pakli2/","Fekete Fehér csillogós", "img/cards/paklik/pakli2/KD.png", "img/cards/paklik/pakli2/AD.png", "img/cards/paklik/pakli2/2D.png","pakli2"),
    DECK3("img/cards/paklik/pakli3/","Díszes", "img/cards/paklik/pakli3/KD.png", "img/cards/paklik/pakli3/AD.png", "img/cards/paklik/pakli3/2D.png","pakli3"),
    DECK4("img/cards/paklik/pakli4/","Alapértelmezett", "img/cards/paklik/pakli4/KD.png", "img/cards/paklik/pakli4/AD.png", "img/cards/paklik/pakli4/2D.png","pakli4"),
    DECK5("img/cards/paklik/pakli5/","Angry Birds", "img/cards/paklik/pakli5/KD.png", "img/cards/paklik/pakli5/AD.png", "img/cards/paklik/pakli5/2D.png","pakli5"),
    DECK6("img/cards/paklik/pakli6/","Normál díszesen", "img/cards/paklik/pakli6/KD.png", "img/cards/paklik/pakli6/AD.png", "img/cards/paklik/pakli6/2D.png","pakli6"),
    DECK7("img/cards/paklik/pakli7/","ChunkFive", "img/cards/paklik/pakli7/KD.png", "img/cards/paklik/pakli7/AD.png", "img/cards/paklik/pakli7/2D.png","pakli7"),
    DECK8("img/cards/paklik/pakli8/","Gotikus", "img/cards/paklik/pakli8/KD.png", "img/cards/paklik/pakli8/AD.png", "img/cards/paklik/pakli8/2D.png","pakli8"),
    DECK9("img/cards/paklik/pakli9/","Gyerek", "img/cards/paklik/pakli9/KD.png", "img/cards/paklik/pakli9/AD.png", "img/cards/paklik/pakli9/2D.png","pakli9"),
    DECK10("img/cards/paklik/pakli10/","Gyémánt", "img/cards/paklik/pakli10/KD.png", "img/cards/paklik/pakli10/AD.png", "img/cards/paklik/pakli10/2D.png","pakli10"),
    DECK11("img/cards/paklik/pakli11/","alap kicsit csicsázva", "img/cards/paklik/pakli11/KD.png", "img/cards/paklik/pakli11/AD.png", "img/cards/paklik/pakli11/2D.png","pakli11");
    private String path;
    private String name;
    private String picture1,picture2,picture3;
    private static List<Front> frontList;
    private String config;
    Front(String path, String name, String picture1,String picture2,String picture3,String config) {
        this.path = path;
        this.name = name;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.config=config;

    }

    public String getPath() {
        return path;
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
        return frontList;
    }
}
