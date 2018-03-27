package coursework.mobile.mobilecoursework;
// Usman Iqbal - S1425850
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Usman on 23/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<RssItems> Items;
    Context context;


    public Adapter(Context context, ArrayList<RssItems> items) {
        this.Items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rss_items, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RssItems current = Items.get(position);
        holder.Title.setText(current.getTitle());
        holder.Date.setText(current.getPubDate());
        holder.Description.setText(current.getDescription());

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Description, Date;

        public MyViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.Title);
            Date = (TextView) itemView.findViewById(R.id.Date);
            Description = (TextView) itemView.findViewById(R.id.Description);
        }
    }
}
