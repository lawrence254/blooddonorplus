package lawrence.blooddonor.notifications_handler;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import lawrence.blooddonor.activities.MainActivity;

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

        //Notify UI of finished registration to get rid of progressbar
        Intent registrationComplete =new Intent(MainActivity.PUSH_NOTIFICATION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
