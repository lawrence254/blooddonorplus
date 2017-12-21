package lawrence.blooddonor.activities;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import lawrence.blooddonor.R;

/**
 * Created by ELITEBOOK on 21/12/2017.
 */

class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<SearchData> data;
    SearchData current;

    // create constructor to initialize context and data sent from MainActivity
    public SearchAdapter(Context context, List<SearchData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_search, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        SearchData current=data.get(position);
        myHolder.texthospName.setText(current.hospName);
        myHolder.textContact.setText("Contact: " + current.contact);
        myHolder.textUnits.setText("Units: " + current.units);
        //myHolder.textPrice.setText("Rs. " + current.price + "\\Kg");
        myHolder.textUnits.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView texthospName;
        TextView textContact;
        TextView textUnits;
        //TextView textPrice;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            texthospName= (TextView) itemView.findViewById(R.id.texthospName);
            textContact = (TextView) itemView.findViewById(R.id.textContact);
            textUnits = (TextView) itemView.findViewById(R.id.textUnits);
            //textPrice = (TextView) itemView.findViewById(R.id.textPrice);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {

            Toast.makeText(context, "You clicked an item", Toast.LENGTH_SHORT).show();

        }

    }

}
