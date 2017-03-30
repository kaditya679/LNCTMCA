package adrock.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MyAlert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alert);
    }

    public void dialogClose(View view)
    {
        finish();
    }
}
