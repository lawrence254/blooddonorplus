package lawrence.blooddonor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import lawrence.blooddonor.R;
import lawrence.blooddonor.activities.MainActivity;
import lawrence.blooddonor.helper.SQLiteHandler;
import lawrence.blooddonor.helper.SessionManager;


public class BioDataTabFragment extends Fragment {
	private SQLiteHandler db;
	private SessionManager sessionManager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 View root =inflater.inflate(R.layout.fragment_bio_data, container, false);

		TextView first=(TextView) root.findViewById(R.id.etFname);
		TextView last=(TextView)root.findViewById(R.id.etLname);
		TextView gender=(TextView)root.findViewById(R.id.etGender);
		TextView mail=(TextView)root.findViewById(R.id.etEmail);
		TextView blood=(TextView)root.findViewById(R.id.etBGroup);

		db =new SQLiteHandler(getContext());
		sessionManager=new SessionManager(getContext());

		if (sessionManager.isLoggedIn()) {

			// User is already logged in. Take him to main activity
			//Intent intent = new Intent(getActivity(),SQLiteHandler.class);


			String fname=db.getUserDetails().get("fname");
			String lname=db.getUserDetails().get("lname");
			String sex=db.getUserDetails().get("email");
			String email=db.getUserDetails().get("blood");
			String bgroup=db.getUserDetails().get("sex");

			first.setText(fname);
			last.setText(lname);
			mail.setText(email);
			gender.setText(sex);
			blood.setText(bgroup);

//			startActivity(intent);
			
		}
		return root;
	}


}
