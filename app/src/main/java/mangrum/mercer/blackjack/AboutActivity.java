package mangrum.mercer.blackjack;

import android.app.Activity;
import android.view.View;

public class AboutActivity extends Activity implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        setContentView(R.layout.about_activity);
    }
}
