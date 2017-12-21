package lawrence.blooddonor.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lawrence.blooddonor.R;
import lawrence.blooddonor.helper.SQLiteHandler;

public class request extends AppCompatActivity {
    private RequestQueue queue;
    private Spinner sType;
    private EditText unitsin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Bundle extra=getIntent().getExtras();
        sType=(Spinner)findViewById(R.id.spinnerRequest);

        /*Handling ocClick actions of the request donations button*/

                /*Initializing Spinner Values*/
        List<String> spinnerarray=new ArrayList<>();
        spinnerarray.add("Tap to select your blood group");
        spinnerarray.add("O+");
        spinnerarray.add("O-");
        spinnerarray.add("A+");
        spinnerarray.add("A-");
        spinnerarray.add("B+");
        spinnerarray.add("B-");
        spinnerarray.add("AB+");
        spinnerarray.add("AB-");


        ArrayAdapter<String> adapter= new ArrayAdapter<>(request.this, android.R.layout.simple_spinner_item, spinnerarray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sType.setAdapter(adapter);
                /*End Of Spinner Initialization*/

        unitsin=(EditText)findViewById(R.id.units);



        Button sendRequest=(Button)findViewById(R.id.placeRequest);

        queue = Volley.newRequestQueue(getApplicationContext());
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String units=unitsin.getText().toString();
                final String type= sType.getSelectedItem().toString();
                        /*Handling Requests after Request Button is Clicked*/
                //int fin=Integer.parseInt(units);
                if(units.trim().length()<1){
                            /*Getting UID of Signed in user to allow for saving donor "requester" into database*/
                    Toast.makeText(getApplicationContext(),
                            "Please enter number of units required!", Toast.LENGTH_LONG)
                            .show();
                }else {
                    SQLiteHandler db=new SQLiteHandler(getApplicationContext());
                    HashMap<String,String> user=db.getUserDetails();
                    String uid=user.get("uid");
                    bloodRequest(units,type,uid);
                    Toast.makeText(getApplicationContext(),
                            "Sending request for: "+units+" Units of blood. Request ID: "+uid, Toast.LENGTH_SHORT)
                            .show();
                    System.out.print("Units entered: "+units);

                }
            }
        });
    }
    private void bloodRequest(final String units, final String type, final String uid) {
        String URL="https://bdplus.000webhostapp.com/request.php";
        StringRequest strRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response From Server IS: "+response);
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("uid",uid);
                params.put("blood", type);
                params.put("units",units);
                return params;
            }
        };
        queue.add(strRequest);
    }


}
