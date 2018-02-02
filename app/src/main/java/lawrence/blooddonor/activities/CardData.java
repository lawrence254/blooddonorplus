package lawrence.blooddonor.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lawrence.blooddonor.R;
import lawrence.blooddonor.helper.SQLiteHandler;
import lawrence.blooddonor.helper.SessionManager;

import static java.lang.Integer.parseInt;

public class CardData extends AppCompatActivity {
    private String url = "https://bdplus.000webhostapp.com/card_detail.php?id=";
    private RequestQueue queue;
    private Spinner sType;
    private static final String TAGH = "Fetch";

    //    SessionManager session = new SessionManager(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_data);

        //TextView pos=(TextView)findViewById(R.id.id);
        final TextView center = (TextView) findViewById(R.id.donorCenter);
        final TextView ap = (TextView) findViewById(R.id.Ap);
        final TextView bp = (TextView) findViewById(R.id.Bp);
        final TextView abp = (TextView) findViewById(R.id.ABp);
        final TextView op = (TextView) findViewById(R.id.Op);
        final TextView an = (TextView) findViewById(R.id.An);
        final TextView bn = (TextView) findViewById(R.id.Bn);
        final TextView abn = (TextView) findViewById(R.id.ABn);
        final TextView on = (TextView) findViewById(R.id.On);
        Bundle extra = getIntent().getExtras();
        sType = (Spinner) findViewById(R.id.spinnerRequest);

        final int position = extra.getInt("position");
        System.out.println("Id:" + position);

        //pos.setText(String.valueOf(position));



        /*Instatiating fetching extra data for specific cards based on the id of the clicked card*/
        queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jb = new JSONObject(response);
                    JSONArray jsonArray = jb.getJSONArray("result");

                    int n = jsonArray.length();
                    for (int i = 0; i < n; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String hospName = jsonObject.getString("hospName");
                        String contact = jsonObject.getString("contact");
                        String Ap = jsonObject.getString("A+");
                        String An = jsonObject.getString("A-");
                        String Bp = jsonObject.getString("B+");
                        String Bn = jsonObject.getString("B-");
                        String ABp = jsonObject.getString("AB+");
                        String ABn = jsonObject.getString("AB-");
                        String Op = jsonObject.getString("O+");
                        String On = jsonObject.getString("O-");

                        center.setText(hospName);
                        ap.setText(String.valueOf(Ap));
                        bp.setText(String.valueOf(Bp));
                        abp.setText(String.valueOf(ABp));
                        op.setText(String.valueOf(Op));
                        an.setText(String.valueOf(An));
                        bn.setText(String.valueOf(Bn));
                        abn.setText(String.valueOf(ABn));
                        on.setText(String.valueOf(On));


//                    lastNameTV.setText(lastName);
                        Log.d(TAGH, "It Has Worked: " + "Group A+" + Ap + "Group B+" + Bp + "Group AB+" + ABp + "Group O+" + Op + "Group A-" + An + "Group B-" + Bn + "Group AB-" + ABn + "Group O-" + On);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something is wrong with your internet connection.", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("id", String.valueOf(position));
                return parameters;
            }
        };

        queue.add(stringRequest);

        Button request=(Button)findViewById(R.id.request);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), request.class);
                v.getContext().startActivity(intent);
            }
        });

    }

}
