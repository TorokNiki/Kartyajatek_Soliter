package backEnd.domain;

import java.util.ArrayList;
import java.util.List;

public enum  Front {
    DECK1("./resources/img/cards/paklik/pakli1/","Normál csillogós","./resources/img/cards/paklik/pakli1/KD.png","./resources/img/cards/paklik/pakli1/AD.png","./resources/img/cards/paklik/pakli1/2D.png"),
    DECK2("./resources/img/cards/paklik/pakli2/","Fekete Fehér csillogós","./resources/img/cards/paklik/pakli2/KD.png","./resources/img/cards/paklik/pakli2/AD.png","./resources/img/cards/paklik/pakli2/2D.png"),
    DECK3("./resources/img/cards/paklik/pakli3/","Díszes","./resources/img/cards/paklik/pakli3/KD.png","./resources/img/cards/paklik/pakli3/AD.png","./resources/img/cards/paklik/pakli3/2D.png"),
    DECK4("./resources/img/cards/paklik/pakli4/","Alapértelmezett","./resources/img/cards/paklik/pakli4/KD.png","./resources/img/cards/paklik/pakli4/AD.png","./resources/img/cards/paklik/pakli4/2D.png"),
    DECK5("./resources/img/cards/paklik/pakli5/","Angry Birds","./resources/img/cards/paklik/pakli5/KD.png","./resources/img/cards/paklik/pakli5/AD.png","./resources/img/cards/paklik/pakli5/2D.png"),
    DECK6("./resources/img/cards/paklik/pakli6/","Normál díszesen","./resources/img/cards/paklik/pakli6/KD.png","./resources/img/cards/paklik/pakli6/AD.png","./resources/img/cards/paklik/pakli6/2D.png"),
    DECK7("./resources/img/cards/paklik/pakli7/","ChunkFive","./resources/img/cards/paklik/pakli7/KD.png","./resources/img/cards/paklik/pakli7/AD.png","./resources/img/cards/paklik/pakli7/2D.png"),
    DECK8("./resources/img/cards/paklik/pakli8/","Gotikus","./resources/img/cards/paklik/pakli8/KD.png","./resources/img/cards/paklik/pakli8/AD.png","./resources/img/cards/paklik/pakli8/2D.png"),
    DECK9("./resources/img/cards/paklik/pakli9/","Gyerek","./resources/img/cards/paklik/pakli9/KD.png","./resources/img/cards/paklik/pakli9/AD.png","./resources/img/cards/paklik/pakli9/2D.png"),
    DECK10("./resources/img/cards/paklik/pakli10/","Gyémánt","./resources/img/cards/paklik/pakli10/KD.png","./resources/img/cards/paklik/pakli10/AD.png","./resources/img/cards/paklik/pakli10/2D.png"),
    DECK11("./resources/img/cards/paklik/pakli11/","alap kicsit csicsázva","./resources/img/cards/paklik/pakli11/KD.png","./resources/img/cards/paklik/pakli11/AD.png","./resources/img/cards/paklik/pakli11/2D.png");
    private String path;
    private String name;
    private String picture1,picture2,picture3;
    private static List<Front> frontList;
    Front(String path, String name, String picture1,String picture2,String picture3) {
        this.path = path;
        this.name = name;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;

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
