package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.model.Floor;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

import java.io.IOException;


public class MapActivity extends AppCompatActivity {

    private ImageView back_button;
    private TextView floor_name, floor_description;
    private ImageView mapIv, map1, map2, map3;

    private ArrayList<Floor> mapArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        init();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_name.setText(mapArrayList.get(0).getName());
                floor_description.setText(mapArrayList.get(0).getDescription());
                mapIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(0).getFloorplan(),null, getPackageName())));
            }
        });

        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_name.setText(mapArrayList.get(1).getName());
                floor_description.setText(mapArrayList.get(1).getDescription());
                mapIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(1).getFloorplan(),null, getPackageName())));
            }
        });

        map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_name.setText(mapArrayList.get(2).getName());
                floor_description.setText(mapArrayList.get(2).getDescription());
                mapIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(2).getFloorplan(),null, getPackageName())));
            }
        });

    }

    private void init(){
        back_button = (ImageView) findViewById(R.id.back_button);

        floor_name = (TextView) findViewById(R.id.floor_nameTv);
        floor_description = (TextView) findViewById(R.id.floor_descTv);

        mapIv = (ImageView) findViewById(R.id.floorIv);
        map1 = (ImageView) findViewById(R.id.map1Iv);
        map2 = (ImageView) findViewById(R.id.map2Iv);
        map3 = (ImageView) findViewById(R.id.map3Iv);

        String floorJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "floordata.json");
        Log.d("JSON", floorJSONString);

        Gson gson = new Gson();
        Type floorType = new TypeToken<ArrayList<Floor>>(){}.getType();
        mapArrayList = gson.fromJson(floorJSONString, floorType);

        mapIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(0).getFloorplan(),null, getPackageName())));
        map1.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(0).getFloorplan(),null, getPackageName())));
        map2.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(1).getFloorplan(),null, getPackageName())));
        map3.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+mapArrayList.get(2).getFloorplan(),null, getPackageName())));
        floor_name.setText(mapArrayList.get(0).getName());
        floor_description.setText(mapArrayList.get(0).getDescription());

    }
}