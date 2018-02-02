package lawrence.blooddonor.notifications_handler;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by EliteBook on 10/9/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG="FirebaseIDservice";
    private RequestQueue queue;
    private tokenobject tokenObject;
    private SharedPreferencesManager mySharedPreference;
    @Override
    public void onTokenRefresh(){
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token: " + refreshedToken);
        storeToken(refreshedToken);
    }
    private void storeToken(final String token){
        //Saving token to shared preferences

        SharedPreferencesManager.getInstance(getApplicationContext()).saveDeviceToken(token);

        //SharedPreferencesManager.getInstance(getApplicationContext()).saveDeviceToken(refreshedToken);


//        storing token to mysql database
        queue = Volley.newRequestQueue(getApplicationContext());
        mySharedPreference = new SharedPreferencesManager(getApplicationContext());
        StringRequest stringPostRequest = new StringRequest(com.android.volley.Request.Method.POST,"https://grazed-scene.000webhostapp.com/bdp/notify/regtoken.php", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                tokenObject = gson.fromJson(response, tokenobject.class);
                if (null == tokenObject) {
                    Toast.makeText(getApplicationContext(), "Can't send your token to the server", Toast.LENGTH_LONG).show();

                    mySharedPreference.saveDeviceToken(String.valueOf(false));
                } else {
                    Toast.makeText(getApplicationContext(), "Token successfully sent to server", Toast.LENGTH_LONG).show();
                    mySharedPreference.saveDeviceToken(String.valueOf(true));

                    mySharedPreference.saveDeviceToken(String.valueOf(false));
                }


            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

               // Map<String, String> params = new HashMap<String, String>();

                params.put("token", token);
                return params;
            }
        };
        queue.add(stringPostRequest);
    }

}




