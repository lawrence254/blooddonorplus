package lawrence.blooddonor.notifications_handler;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by EliteBook on 10/9/2017.
 */

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME="FIREBase";
    private static final String TAG="DeviceToken";

    private static SharedPreferencesManager mInstance;
    private static Context mcontext;

    SharedPreferencesManager(Context context){
        mcontext=context;
    }
    public static synchronized SharedPreferencesManager getInstance(Context context){
        if (mInstance==null){
            mInstance=new SharedPreferencesManager(context);
        }
        return mInstance;
    }
    //Saving device token to shared preferences
    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TAG,token);
        editor.apply();
        return true;
    }
    //Fetching device token from shared preferences.
    public String getDeviceToken(){
        SharedPreferences sharedPreferences=mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG,null);
    }

//    public void saveDeviceToken(boolean b) {
//
//    }
}
