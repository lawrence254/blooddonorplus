package layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lawrence.blooddonor.R;

/**
 * Created by EliteBook on 8/1/2017.
 */

public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    private List<MyData> my_data;

    public MyAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }


    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.hospName.setText(my_data.get(position).getHospName());
        holder.contact.setText(my_data.get(position).getContact());
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView hospName;
        public TextView contact;

        public ViewHolder(View itemView){
            super(itemView);
            hospName=(TextView) itemView.findViewById(R.id.hosp);
            contact=(TextView) itemView.findViewById(R.id.contact);
        }
    }

}
