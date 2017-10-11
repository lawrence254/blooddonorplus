package lawrence.blooddonor.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;

import lawrence.blooddonor.R;
import lawrence.blooddonor.activities.MainActivity;
import lawrence.blooddonor.helper.SQLiteHandler;
import lawrence.blooddonor.helper.SessionManager;

import static android.R.attr.bitmap;
import static android.app.Activity.RESULT_OK;


public class BioDataTabFragment extends Fragment {
	private static final int SELECT_PHOTO = 100;
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
		ImageView dp=(ImageView) root.findViewById(R.id.imageView);

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

			//Changing the Display Picture from the default
			dp.setOnClickListener(new onClickListener());

		}
		return root;
	}


	private class onClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent,SELECT_PHOTO);
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent imageReturnIntent){
		super.onActivityResult(requestCode,resultCode,imageReturnIntent);
		switch (requestCode){
			case SELECT_PHOTO:
				if(resultCode==RESULT_OK){
					Uri selectedImage=imageReturnIntent.getData();

					Bitmap yourselectedimage=null;
					try{
						yourselectedimage=decodeUri(selectedImage);
					}catch (FileNotFoundException e){
						e.printStackTrace();
					}
					ImageView dp = null;
					dp.setImageBitmap(yourselectedimage);
				}
		}
	}
	private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException{
		BitmapFactory.Options o=new BitmapFactory.Options();
		o.inJustDecodeBounds=true;
		BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage),null,o);
				final int REQUIRED_SIZE=140;
		int width_tmp = o.outWidth, height_tmp = o.outHeight;
		int scale = 1;
		while (true) {
			if (width_tmp / 2 < REQUIRED_SIZE
					|| height_tmp / 2 < REQUIRED_SIZE) {
				break;
			}
			width_tmp /= 2;
			height_tmp /= 2;
			scale *= 2;
		}
		BitmapFactory.Options o2 = new BitmapFactory.Options();
		o2.inSampleSize = scale;
		return BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o2);


	}
}
