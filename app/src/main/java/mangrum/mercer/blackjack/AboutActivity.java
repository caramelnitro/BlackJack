package mangrum.mercer.blackjack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class AboutActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        // Set no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Make full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.about_activity);
    }
}
