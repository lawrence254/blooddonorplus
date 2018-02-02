package lawrence.blooddonor.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

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
	private ProgressDialog pDialog;

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

		// Progress dialog
		pDialog = new ProgressDialog(getContext());
		pDialog.setCancelable(false);

		quotesList=new ArrayList<>();
		ConnectivityManager connMgr = (ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		// If the network is active activate asynctask
		if (networkInfo != null) {
			load_data_from_server();
		}
		else{
			Toast.makeText( getContext(),"Please Check Your Internet Connection and Try again...", Toast.LENGTH_LONG).show();
		}
		return root;
	}

	private void load_data_from_server() {
		AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
			private Context applicationContext;
			private Context activity;

			//Toast.makeText(getContext(),"Fetching some interesting facts for you...",Toast.LENGTH_LONG);

			protected void onPreExecute(){
				pDialog.setMessage("Getting some facts ...");
				showDialog();
			}

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
								new Quotes(object.getString("fact"), object.getString("source"));
						quotesList.add(data);

						Log.i("Fetching...", "Quotes");
					}

				} catch (IOException e) {
					Toast.makeText( getContext(),"Please Check Your Internet Connection and Try again...", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				} catch (JSONException e) {
					Toast.makeText( getContext(),"Please Check Your Internet Connection and Try again...", Toast.LENGTH_LONG).show();
					e.printStackTrace();
					System.out.println("No More Quotes");
				}
				Log.e(TAG,"Server Not Working" );
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

				if (pDialog.isShowing()) {
					pDialog.dismiss();
				}
			}
		};
		task.execute();

	}


	private void showDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

	private void HandleIOError(IOException e) {
		Toast.makeText( getContext(),"Please Check Your Internet Connection and Try again...", Toast.LENGTH_LONG).show();

	}

	private void HandleNetworkError(JSONException e) {
		Toast.makeText( getContext(),"Please Check Your Internet Connection and Try again...", Toast.LENGTH_LONG).show();
	}

}