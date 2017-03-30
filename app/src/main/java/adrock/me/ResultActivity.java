package adrock.me;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent=getIntent();

        if(intent!=null) {
            String title = intent.getStringExtra("title");
            String body = intent.getStringExtra("body");

            TextView titleText=(TextView)findViewById(R.id.textView);
            TextView bodyText=(TextView)findViewById(R.id.textView2);

            Typeface mcf=Typeface.createFromAsset(getAssets(), "Lobster.otf");
            titleText.setTypeface(mcf);
            bodyText.setTypeface(mcf);
            titleText.setText(title);
            bodyText.setText(body);
        }
    }
}
