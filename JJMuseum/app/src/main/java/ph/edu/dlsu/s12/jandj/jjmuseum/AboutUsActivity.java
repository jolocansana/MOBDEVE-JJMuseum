package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView back_btn;
    private TextView msgDirectorsTv, aboutDescTv;

    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        init();

        aboutDescTv.setText("The premier repository and custodian of the country’s heritage introduces its significant collections, and shares to a wider audience on-line its efforts to protect, conserve and promote the natural and cultural heritage of the Filipino people. We hope that through this mobile app, we can stimulate your interest to visit us and appreciate in person the legacies that characterize and make up the Filipino identity.");
        msgDirectorsTv.setText("The interdisciplinary nature of the work in the JJ Museum also encourages exchange of information and collaborative strategies towards achieving its goals as a cultural, scientific, and educational institution. As we make accessible the museum’s resources in the web, we also invite everyone to engage and commit themselves in the continuous endeavors of heritage preservation not only in the Philippines but throughout the world.");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /*
     *  openBrowser
     *  sets the action to open an URL
     *  url declared in the XML tag
     *  redirected to the URL provided
     */
    public void openBrowser(View view){

        //Get url from tag
        String url = (String)view.getTag();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        //pass the url to intent data
        intent.setData(Uri.parse(url));

        startActivity(intent);
    }

    /*
     *  init
     *  initializes XML references
     *  void
     */
    private void init() {
        back_btn = findViewById(R.id.back_btn);
        aboutDescTv = findViewById(R.id.aboutDescTv);
        msgDirectorsTv = findViewById(R.id.msgDirectorsTv);
    }
}