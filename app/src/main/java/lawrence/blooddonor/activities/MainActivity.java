package lawrence.blooddonor.activities;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lawrence.blooddonor.R;
import lawrence.blooddonor.adapters.SearchAdapter;
import lawrence.blooddonor.adapters.TabsPagerAdapter;
import lawrence.blooddonor.models.AppController;
import lawrence.blooddonor.models.Search;

public class MainActivity extends AppCompatActivity {

	private BroadcastReceiver mRegistrationBroadcastReceiver;
	public static final String PUSH_NOTIFICATION = "pushNotification";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		/*Initialize a new method to handle search*/

        /*End of initialization*/





		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setText("Facts"));
		tabLayout.addTab(tabLayout.newTab().setText("Blood Banks"));
		tabLayout.addTab(tabLayout.newTab().setText("My Data"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		final TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(adapter);
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
	}




    @Override
	protected void onResume() {
		super.onResume();

		// register new push message receiver
		// by doing this, the activity will be notified each time a new message arrives
		LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(PUSH_NOTIFICATION));
	}

	@Override
	protected void onPause() {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.search_main,menu);
		MenuItem item=menu.findItem(R.id.action_search);
		SearchView search = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		search.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResults.class)));
		search.setQueryHint(getResources().getString(R.string.search_hint));
		return true;
	}



}
