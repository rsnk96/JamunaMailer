package in.aqel.jamunamailer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name" ;
    public static final String EXTRA_ROLL = "roll" ;
    public static final String EXTRA_ROOM = "room" ;
    public static final String EXTRA_MOBILE = "mobile" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirm);
        ((TextView) findViewById(R.id.tvName)).setText(getIntent().getExtras().getString(EXTRA_NAME, ""));
        ((TextView) findViewById(R.id.tvRollNumber)).setText(getIntent().getExtras().getString(EXTRA_ROLL, ""));
        ((TextView) findViewById(R.id.tvRoomNumber)).setText(getIntent().getExtras().getInt(EXTRA_ROOM, 0) + " ");


    }
}
