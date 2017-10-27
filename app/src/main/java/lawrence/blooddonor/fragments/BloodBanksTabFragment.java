package lawrence.blooddonor.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.adapters.BloodBanksAdapter;
import lawrence.blooddonor.models.Hospital;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class BloodBanksTabFragment extends Fragment {

    private FirebaseAnalytics mFirebaseAnalytics;
	private static final String TAG = "Server Connection";
	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private BloodBanksAdapter adapter;
	private List<Hospital> data_List = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_blood_banks, container, false);
		recyclerView = (RecyclerView)root.findViewById(R.id.bloodBanksRecyclerView);
		recyclerView.setHasFixedSize(true);

		load_data_from_server(0);
		return root;
	}

	private void load_data_from_server(final int id) {
		AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
			private Context applicationContext;
			private Context activity;

			Context getApplicationContext() {
				return applicationContext;
			}

			Context getActivity() {
				return activity;
			}

			@Override
			protected Void doInBackground(Integer... integers) {
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder()
						.url("http://192.168.137.1:80/card.php?id=" + id)
						.build();
				try {
					Response response = client.newCall(request).execute();
					String Json=response.body().string();
					JSONObject jobject=new JSONObject(Json);
					JSONArray array=jobject.getJSONArray("result");

					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						Hospital data =
								new Hospital(object.getInt("id"), object.getString("hospName"), object.getString("contact"));
						data_List.add(data);
						Log.i("Fetching...", "Blood Banks");
					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
					System.out.println("No More Hospitals");
				}
				Log.e(TAG,"I'm Not Working" );
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {

				adapter = new BloodBanksAdapter(this.getActivity(), data_List);
				layoutManager = new LinearLayoutManager(getApplicationContext());
				recyclerView.setLayoutManager(layoutManager);
				recyclerView.setItemAnimator(new DefaultItemAnimator());
				recyclerView.setAdapter(adapter);

				adapter.notifyDataSetChanged();
			}
		};
		task.execute(id);

	}

}
