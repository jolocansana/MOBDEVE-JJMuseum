package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import ph.edu.dlsu.s12.jandj.jjmuseum.controllers.Pebble;
import ph.edu.dlsu.s12.jandj.jjmuseum.model.Collection;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

public class MainActivity extends AppCompatActivity implements SectionListAdapter.ItemClickListener {

    private Button search_button, qr_button;
    private RecyclerView horizontalScrollView;
    private CardView cv_map_button;

    private SectionListAdapter sectionListAdapter;
    private ArrayList<Pebble> sectionsArrayList;
    private ArrayList<Collection> collectionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        sectionListAdapter = new SectionListAdapter(sectionsArrayList, this);
        sectionListAdapter.setClickListener(this);
        horizontalScrollView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalScrollView.setAdapter(sectionListAdapter);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchActivity = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchActivity);
            }
        });

        qr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent QRActivity = new Intent(getApplicationContext(), QRActivity.class);
                startActivity(QRActivity);
            }
        });

        cv_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MapActivity = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(MapActivity);
            }
        });
    }

    private void init() {
        horizontalScrollView = (RecyclerView) findViewById(R.id.horizontal_scrollview);
        search_button = (Button) findViewById(R.id.search_button);
        qr_button = (Button) findViewById(R.id.qr_button);
        cv_map_button = (CardView) findViewById(R.id.cv_map_button);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(horizontalScrollView.getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.horizontal_spacing));
        horizontalScrollView.addItemDecoration(dividerItemDecoration);

        String collectionJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "collectiondata.json");
        Log.d("JSON", collectionJSONString);

        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Collection>>(){}.getType();

        collectionArrayList = gson.fromJson(collectionJSONString, collectionType);

        Log.d("FIRST IN ARRAY", collectionArrayList.get(0).getCollection());

        sectionsArrayList = new ArrayList<>();

        for(Collection collection : collectionArrayList) {
            sectionsArrayList.add(new Pebble(collection.getHeader(), collection.getCollection()));
        }

        Log.d("HEADER FILE", sectionsArrayList.get(0).getBackground_image());
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent SectionActivity = new Intent(getApplicationContext(), SectionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Collection", collectionArrayList.get(position).getCollection());
        bundle.putString("ID", collectionArrayList.get(position).getID());
        bundle.putString("Header", collectionArrayList.get(position).getHeader());
        bundle.putString("Description", collectionArrayList.get(position).getDescription());
        SectionActivity.putExtras(bundle);
        startActivity(SectionActivity);
    }
}