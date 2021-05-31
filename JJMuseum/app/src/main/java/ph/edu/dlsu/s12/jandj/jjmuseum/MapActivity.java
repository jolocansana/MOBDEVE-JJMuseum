package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.model.Floor;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;


public class MapActivity extends AppCompatActivity {

    private ImageView back_btn;
    private TextView floor_name_tv, floor_description_tv;
    private Button map1_btn, map2_btn, map3_btn;
    private ImageView map_iv;

    private ArrayList<Floor> mapArrayList;


    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        init();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        map1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map1_btn.setBackgroundColor(getResources().getColor(R.color.theme_darkgrey));
                map2_btn.setBackgroundColor(getResources().getColor(R.color.theme_orange));
                map3_btn.setBackgroundColor(getResources().getColor(R.color.theme_orange));
                floor_name_tv.setText(mapArrayList.get(0).getName());
                floor_description_tv.setText(mapArrayList.get(0).getDescription());
                map_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(0).getFloorplan(),null, getPackageName())));
            }
        });

        map2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map1_btn.setBackgroundColor(getResources().getColor(R.color.theme_orange));
                map2_btn.setBackgroundColor(getResources().getColor(R.color.theme_darkgrey));
                map3_btn.setBackgroundColor(getResources().getColor(R.color.theme_orange));
                floor_name_tv.setText(mapArrayList.get(1).getName());
                floor_description_tv.setText(mapArrayList.get(1).getDescription());
                map_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(1).getFloorplan(),null, getPackageName())));
            }
        });

        map3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map1_btn.setBackgroundColor(getResources().getColor(R.color.theme_orange));
                map2_btn.setBackgroundColor(getResources().getColor(R.color.theme_orange));
                map3_btn.setBackgroundColor(getResources().getColor(R.color.theme_darkgrey));
                floor_name_tv.setText(mapArrayList.get(2).getName());
                floor_description_tv.setText(mapArrayList.get(2).getDescription());
                map_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(2).getFloorplan(),null, getPackageName())));
            }
        });

    }

    /*
     *  init
     *  initializes XML references and gets data from json files using Gson()
     *  void
     */
    private void init(){
        back_btn = (ImageView) findViewById(R.id.back_btn);

        floor_name_tv = (TextView) findViewById(R.id.floor_name_tv);
        floor_description_tv = (TextView) findViewById(R.id.floor_desc_tv);

        map_iv = (ImageView) findViewById(R.id.floor_iv);
        map1_btn = (Button) findViewById(R.id.map1_btn);
        map2_btn = (Button) findViewById(R.id.map2_btn);
        map3_btn = (Button) findViewById(R.id.map3_btn);

        // Gets floor information from JSON
        String floorJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "floordata.json");
        Log.d("JSON", floorJSONString);

        Gson gson = new Gson();
        Type floorType = new TypeToken<ArrayList<Floor>>(){}.getType();
        mapArrayList = gson.fromJson(floorJSONString, floorType);

        map_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(0).getFloorplan(),null, getPackageName())));
        floor_name_tv.setText(mapArrayList.get(0).getName());
        floor_description_tv.setText(mapArrayList.get(0).getDescription());

    }
}