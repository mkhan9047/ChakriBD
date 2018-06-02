package notification.push.com.chakribd.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import notification.push.com.chakribd.POJO.NaviListPojo;
import notification.push.com.chakribd.R;

/**
 * Created by Mujahid on 2/22/2018.
 */

public class NaviListCustomAdapter extends ArrayAdapter<NaviListPojo> {

    TextView textView;
    ImageView imageView;
    int resource;
    Context context;
    List<NaviListPojo> list;

    Switch UnSwitch;

    public NaviListCustomAdapter(@NonNull Context context, int resource, List<NaviListPojo> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    public View getView(int p, View convetView, @NonNull ViewGroup parent) {
        LinearLayout NaviView;
        final NaviListPojo pojo = list.get(p);
        if (convetView == null) {
            NaviView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(inflater);
            assert layoutInflater != null;
            layoutInflater.inflate(resource, NaviView, true);

        } else {

            NaviView = (LinearLayout) convetView;

        }
        textView = NaviView.findViewById(R.id.simple_navi_text);
        imageView = NaviView.findViewById(R.id.simple_navi_icon);

        textView.setText(pojo.getText());
        imageView.setImageResource(pojo.getImage());

        return NaviView;
    }

}
