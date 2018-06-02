package notification.push.com.chakribd.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import notification.push.com.chakribd.Adapter.DialogAdapterRecycle;
import notification.push.com.chakribd.POJO.FavoutitePojo;
import notification.push.com.chakribd.R;

/**
 * Created by Mujahid on 5/30/2018.
 */

public class FavouriteDialog extends DialogFragment {
    RecyclerView view;
    List<FavoutitePojo> list;
    DialogAdapterRecycle adapterRecycle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favoruite_dailog, container, false);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View in = inflater.inflate(R.layout.favoruite_dailog, null);

        Bundle a = getArguments();
        list = (List<FavoutitePojo>) a.getSerializable("data");
        view = in.findViewById(R.id.dialog_list);

        adapterRecycle = new DialogAdapterRecycle(list,getActivity());
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.setHasFixedSize(true);
        view.setAdapter(adapterRecycle);



        builder.setView(in);
        builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder.create();
    }
}
