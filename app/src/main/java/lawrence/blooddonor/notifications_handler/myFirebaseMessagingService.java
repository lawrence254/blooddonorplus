package lawrence.blooddonor.notifications_handler;

import android.content.Intent;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by EliteBook on 10/9/2017.
 */

public class FirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG="FirebaseMessageService";

    @Override
    public void onMessageRecieved(RemoteMessage remoteMessage){
        if(remoteMessage.getData().size()>0){
            Log.e(TAG, "Data Payload: "+remoteMessage.getData().toString() );
            try {
                JSONObject json=new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            }catch (Exception e){
                Log.e(TAG, "Exception: "+ e.getMessage() );
            }
        }
    }
    //Method to display notification passed from Firebase
    private void sendPushNotification(JSONObject json){
        //Display in log for debugging
        Log.e(TAG, "Notification JSON: "+json.toString() );
        try{
            //Fetching the data
            JSONObject data=json.getJSONObject("data");

            //Parsing the data recieved
            String title=data.getString("title");
            String message=data.getString("message");
            String ImageUrl=data.getString("image");

            //Notification Manager object
            MyNotificationManager mNotificationManager= new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            //Action when no image is present
            if(ImageUrl.equals("null")){
                //Small notification
                mNotificationManager.showSmallNotification(title,message,intent);

            }else {
                //Big notification with image
                mNotificationManager.showBigNotification(title,message,ImageUrl,intent);
            }
        }catch (JSONException e){
            Log.e(TAG, "JSON EXCEPTION: "+e.getMessage() );
        }catch (Exception e){
            Log.e(TAG, "Exception: "+e.getMessage() );
        }
    }
}
