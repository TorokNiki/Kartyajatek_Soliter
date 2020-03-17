package backEnd.services.factory;

import backEnd.domain.Rank;
import backEnd.domain.Style;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Asset {
    private static Map<String, Image> assets;

    public static void Asset() {
        assets=new HashMap<>();
        for (Style color : Style.values()) {
            for (Rank rank : Rank.values()) {
                String s=rank.helper+""+color.helper;
                Image img=new Image(DeckFactory.DECK +Config.properties.getProperty("front")+"/"+ rank.helper+color.helper +".png");
                assets.put(s,img);
            }
        }
        assets.put("back",new Image(DeckFactory.BACKOFCARD+Config.properties.getProperty("back")+".png"));
    }

    public static Map<String, Image> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, Image> assets) {
        this.assets = assets;
    }
}
