package lawrence.blooddonor.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lawrence.blooddonor.R;
import lawrence.blooddonor.adapters.SearchAdapter;
import lawrence.blooddonor.models.Search;

/**
 * Created by ELITEBOOK on 15/01/2018.
 */

public class SearchResults extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Search> contactList;
    private SearchAdapter mAdapter;
    private SearchView searchView;

    // url to fetch contacts json
    private static final String URL = "https://bdplus.000webhostapp.com/hos.php?query=";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);


            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            contactList = new ArrayList<Search>();
            mAdapter = new SearchAdapter(this.getBaseContext(), contactList);

            // white background notification bar
            whiteNotificationBar(recyclerView);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
            recyclerView.setAdapter(mAdapter);

            fetchContacts(query);
        }
    }

    private void fetchContacts(final String query) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jb = new JSONObject(response);
                    JSONArray jsonArray = jb.getJSONArray("result");

                    int n = jsonArray.length();
                    for (int i = 0; i < n; i++) {
                        Search search=new Search();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        search.fname = jsonObject.getString("fname");
                        search.phone = jsonObject.getString("phone");

                        contactList.add(search);
                    }
                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("query", query);
                return parameters;
            }
        };

        queue.add(stringRequest);

    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

//	@Override
//	public void onContactSelected(Contact contact) {
//		Toast.makeText(getApplicationContext(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();
//	}
@Override
public void onBackPressed() {
    // close search view on back button pressed
    if (!searchView.isIconified()) {
        searchView.setIconified(true);
        return;
    }
    super.onBackPressed();
}
}



