package adrock.me;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMInitializationService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMInitializationService";

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "FCM Token : " + fcmToken);
        Toast.makeText(FCMInitializationService.this, fcmToken+"", Toast.LENGTH_SHORT).show();
        //Save or send FCM registration token



    }
}
