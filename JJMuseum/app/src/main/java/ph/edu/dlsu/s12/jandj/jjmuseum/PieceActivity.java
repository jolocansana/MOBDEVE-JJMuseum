package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PieceActivity extends AppCompatActivity {

    private ImageView back_button;
    private Button btn_comment;
    private ImageView itemIv, item1Iv, item2Iv, item3Iv;
    private TextView header_title, item_nameTv, item_collectionTv, item_time_originTv, item_descriptionTv;
    private EditText commentEt;
    private ArrayList<String> assetsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);

        init();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        item1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
            }
        });

        item2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(1),null, getPackageName())));
            }
        });

        item3Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(2),null, getPackageName())));
            }
        });
    }

    private void init(){
        back_button = (ImageView) findViewById(R.id.back_button);
        btn_comment = (Button) findViewById(R.id.btn_comment);

        itemIv = (ImageView) findViewById(R.id.itemIv);
        item1Iv = (ImageView) findViewById(R.id.item1Iv);
        item2Iv = (ImageView) findViewById(R.id.item2Iv);
        item3Iv = (ImageView) findViewById(R.id.item3Iv);

        header_title = (TextView) findViewById(R.id.header_title);
        item_nameTv = (TextView)findViewById(R.id.item_nameTv);
        item_collectionTv = (TextView)findViewById(R.id.item_collectionTv);
        item_time_originTv = (TextView)findViewById(R.id.item_time_originTv);
        item_descriptionTv = (TextView)findViewById(R.id.item_descriptionTv);

        commentEt = (EditText)findViewById(R.id.commentEt);

        Bundle bundle = getIntent().getExtras();

        header_title.setText(bundle.getString("Name"));
        item_nameTv.setText(bundle.getString("Name"));
        item_collectionTv.setText(bundle.getString("Collection"));
        item_time_originTv.setText(bundle.getString("Time"));
        item_descriptionTv.setText(bundle.getString("Description"));
        assetsArrayList = bundle.getStringArrayList("Assets");

        Log.d( "List Size", "Size is " + assetsArrayList.size());
        itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
        item1Iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
        item2Iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(1),null, getPackageName())));
        if ((assetsArrayList.get(2)).equals("")){
            item3Iv.setVisibility(View.GONE);
        }
        else{
            item3Iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(2),null, getPackageName())));
        }

    }
}