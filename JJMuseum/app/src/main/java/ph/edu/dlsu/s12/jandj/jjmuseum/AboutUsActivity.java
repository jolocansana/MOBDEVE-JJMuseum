package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextView msgDirectorsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        init();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        msgDirectorsTv = findViewById(R.id.msgDirectorsTv);

        msgDirectorsTv.setText("The premier repository and custodian of the country’s heritage introduces its significant collections, and shares to a wider audience on-line its efforts to protect, conserve and promote the natural and cultural heritage of the Filipino people. We hope that through this website, we can stimulate your interest to visit us and appreciate in person the legacies that characterize and make up the Filipino identity.");
    }
}