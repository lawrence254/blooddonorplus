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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.adapters.BloodBanksAdapter;
import lawrence.blooddonor.adapters.FactsAdapter;
import lawrence.blooddonor.models.Hospital;
import lawrence.blooddonor.models.Quotes;
import okhttp3.OkHttpClient;


public class SearchTabFragment extends Fragment {

	private static final String URL_QUOTES = "https://bdplus.000webhostapp.com/quotes.php";
	private FirebaseAnalytics mFirebaseAnalytics;
	private static final String TAG = "Server Connection";
	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private FactsAdapter adapter;
	private List<Quotes> quotesList = new ArrayList<>();

	private Context applicationContext;
	private Context activity;

	Context getApplicationContext() {
		return applicationContext;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Obtain the FirebaseAnalytics instance.
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root= inflater.inflate(R.layout.fragment_search, container, false);

		recyclerView = (RecyclerView)root.findViewById(R.id.factRecycler);
		recyclerView.setHasFixedSize(true);

		quotesList=new ArrayList<>();

		load_data_from_server();

		return root;
	}

	private void load_data_from_server() {
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
				okhttp3.Request request = new okhttp3.Request.Builder()
						.url(URL_QUOTES)
						.build();
				try {
					okhttp3.Response response = client.newCall(request).execute();
					String Json=response.body().string();
					JSONObject jobject=new JSONObject(Json);
					JSONArray array=jobject.getJSONArray("result");

					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						Quotes data =
								new Quotes(object.getInt("id"), object.getString("fact"), object.getString("source"));
						quotesList.add(data);
						Log.i("Fetching...", "Quotes"+object.getString("fact"));
					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
					}

				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {

				adapter = new FactsAdapter(this.getActivity(), quotesList);
				layoutManager = new LinearLayoutManager(getApplicationContext());
				recyclerView.setLayoutManager(layoutManager);
				recyclerView.setItemAnimator(new DefaultItemAnimator());
				recyclerView.setAdapter(adapter);

				adapter.notifyDataSetChanged();
			}
		};
		task.execute();

	}

}
