package layout;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lawrence.blooddonor.R;

/**
 * Created by EliteBook on 8/15/2017.
 */




public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private Context context;
    private List<Banks> my_data;

    public RVAdapter(Context context, List<Banks> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.hospName.setText(my_data.get(position).getHospName());
        holder.contact.setText(my_data.get(position).getContact());

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public int id;
        public TextView hospName;
        public TextView contact;

        public ViewHolder(View itemView) {
            super(itemView);

            hospName = (TextView) itemView.findViewById(R.id.hosp);
            contact = (TextView) itemView.findViewById(R.id.contact);        }
    }
}
