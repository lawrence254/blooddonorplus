package lawrence.blooddonor.adapters;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.activities.CardData;
import lawrence.blooddonor.models.Hospital;
import lawrence.blooddonor.models.Quotes;

/**
 * Created by ELITEBOOK on 21/12/2017.
 */

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.ViewHolder> {

    private Context context;
    private List<Quotes> my_data;

    public FactsAdapter(Context context, List<Quotes> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public void onBindViewHolder(FactsAdapter.ViewHolder holder, int position) {
        holder.fact.setText(my_data.get(position).getFact());
        holder.source.setText(my_data.get(position).getSource());
        //holder.id.setText(String.valueOf(my_data.get(position).getId()));

        holder.itemView.setTag(position);
    }

    @Override
    public FactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_list, parent, false);
        return new FactsAdapter.ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fact;
        private TextView source;
        //private TextView id;


        ViewHolder(final View itemView) {
            super(itemView);
            fact = (TextView)itemView.findViewById(R.id.tvFact);
            source = (TextView)itemView.findViewById(R.id.tvSource);
            //id=(TextView)itemView.findViewById(R.id.id);

            /*Handling on click events
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), CardData.class);
                    int position= getAdapterPosition();
                    intent.putExtra("position",position+1 );
                    v.getContext().startActivity(intent);

                    //Toast.makeText(context,"The Item Clicked is: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    //Trying this shit out

                }
            });*/
        }
    }

}
