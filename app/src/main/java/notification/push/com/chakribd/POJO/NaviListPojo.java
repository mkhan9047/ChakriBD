package notification.push.com.chakribd.POJO;

import java.io.Serializable;

/**
 * Created by Mujahid on 2/22/2018.
 */

public class NaviListPojo implements Serializable {

    private String text;
    private int image;

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public NaviListPojo(String text, int image) {
        this.text = text;
        this.image = image;
    }
}
