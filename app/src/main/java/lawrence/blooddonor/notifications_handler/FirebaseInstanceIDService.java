package lawrence.blooddonor.notifications_handler;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import lawrence.blooddonor.activities.MainActivity;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by EliteBook on 10/9/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG="FirebaseIDservice";
    @Override
    public void onTokenRefresh(){
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed Token: " + refreshedToken);
        storeToken(refreshedToken);
    }
    private void storeToken(String token){
        //Saving token to shared preferences
        SharedPreferencesManager.getInstance(getApplicationContext()).saveDeviceToken(token);

//        storing token to mysql database
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.137.1/notify/regtoken.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Notify UI of finished registration to get rid of progressbar
        Intent registrationComplete =new Intent(MainActivity.PUSH_NOTIFICATION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
