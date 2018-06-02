package notification.push.com.chakribd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import notification.push.com.chakribd.Database.DatabaseOperation;
import notification.push.com.chakribd.MainActivity;
import notification.push.com.chakribd.POJO.FavoutitePojo;
import notification.push.com.chakribd.R;

/**
 * Created by Mujahid on 5/30/2018.
 */

public class DialogAdapterRecycle extends RecyclerView.Adapter<DialogAdapterRecycle.MyViewHolder> {
    List<FavoutitePojo> favoutitePojos;
    Context context;

    public DialogAdapterRecycle(List<FavoutitePojo> favoutitePojos, Context context) {
        this.favoutitePojos = favoutitePojos;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialoa_recycle,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.title.setText(parse(favoutitePojos.get(position).getTitle()));

    }

    private String parse(String s){
       return s.replace("Pratidin24.com:","");
    }
    @Override
    public int getItemCount() {
        return favoutitePojos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView delte;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.dialog_title);
            delte = itemView.findViewById(R.id.dialog_delete);
            delte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(DatabaseOperation.DeleteFav(favoutitePojos.get(getAdapterPosition()).getTitle(),context)){
                        Toast.makeText(context,"Delected!",Toast.LENGTH_LONG).show();
                        favoutitePojos.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(context," Not Delected!",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("link",favoutitePojos.get(getAdapterPosition()).getLink());
            context.startActivity(intent);
        }
    }
}
