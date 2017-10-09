package lawrence.blooddonor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.models.BloodBanks;

/**
 * Created by EliteBook on 8/15/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
	private Context context;
	private List<BloodBanks> my_data;

	public RVAdapter(Context context, List<BloodBanks> my_data) {
		this.context = context;
		this.my_data = my_data;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);

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

	class ViewHolder extends RecyclerView.ViewHolder {

		public int id;
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
					Toast.makeText(context,"The Item Clicked is: "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
				}
            });
		}
	}
}
