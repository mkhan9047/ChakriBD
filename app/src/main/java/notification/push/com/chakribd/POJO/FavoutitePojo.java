package notification.push.com.chakribd.POJO;

import java.io.Serializable;

/**
 * Created by Mujahid on 5/30/2018.
 */

public class FavoutitePojo implements Serializable {
    private String title;
    private String link;

    public FavoutitePojo(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
