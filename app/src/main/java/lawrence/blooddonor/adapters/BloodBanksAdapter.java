package lawrence.blooddonor.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.activities.CardData;
import lawrence.blooddonor.models.Hospital;

/**
 * Created by EliteBook on 8/1/2017.
 */

public class BloodBanksAdapter extends RecyclerView.Adapter<BloodBanksAdapter.ViewHolder> {
	private Context context;
	private List<Hospital> my_data;

	public BloodBanksAdapter(Context context, List<Hospital> my_data) {
		this.context = context;
		this.my_data = my_data;
	}

	@Override
	public void onBindViewHolder(BloodBanksAdapter.ViewHolder holder, int position) {
		holder.hospName.setText(my_data.get(position).getHospName());
		holder.contact.setText(my_data.get(position).getContact());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
		return new ViewHolder(itemView);
	}

	@Override
	public int getItemCount() {
		return my_data.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		private TextView hospName;
		private TextView contact;

		ViewHolder(View itemView) {
			super(itemView);
			hospName = (TextView)itemView.findViewById(R.id.hosp);
			contact = (TextView)itemView.findViewById(R.id.contact);

            //Handling on click events
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent=new Intent(v.getContext(), CardData.class);
//                    v.getContext().startActivity(intent);
                    Toast.makeText(context,"The Item Clicked is: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
		}
	}

}
