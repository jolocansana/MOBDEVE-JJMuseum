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
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import ph.edu.dlsu.s12.jandj.jjmuseum.controllers.Pebble;
import ph.edu.dlsu.s12.jandj.jjmuseum.model.Collection;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

public class MainActivity extends AppCompatActivity implements SectionListAdapter.ItemClickListener {

    private ImageView search_btn, qr_btn;
    private RecyclerView horizontal_sv;
    private CardView map_button_cv, about_button_cv;

    private SectionListAdapter sectionListAdapter;
    private ArrayList<Pebble> sectionsArrayList;
    private ArrayList<Collection> collectionArrayList;


    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        sectionListAdapter = new SectionListAdapter(sectionsArrayList, this);
        sectionListAdapter.setClickListener(this);
        horizontal_sv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontal_sv.setAdapter(sectionListAdapter);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchActivity = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchActivity);
            }
        });

        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent QRActivity = new Intent(getApplicationContext(), QRActivity.class);
                startActivity(QRActivity);
            }
        });

        map_button_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MapActivity = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(MapActivity);
            }
        });

        about_button_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AboutUsActivity = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(AboutUsActivity);
            }
        });
    }

    /*
     *  init
     *  initializes XML references and gets data from json files using Gson()
     *  void
     */
    private void init() {
        horizontal_sv = (RecyclerView) findViewById(R.id.horizontal_rv);
        search_btn = (ImageView) findViewById(R.id.search_btn);
        qr_btn = (ImageView) findViewById(R.id.qr_btn);

        about_button_cv = (CardView) findViewById(R.id.about_button_cv);
        map_button_cv = (CardView) findViewById(R.id.map_button_cv);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(horizontal_sv.getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.horizontal_spacing));
        horizontal_sv.addItemDecoration(dividerItemDecoration);


        // Gets collection data from json file
        String collectionJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "collectiondata.json");
        Log.d("JSON", collectionJSONString);

        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Collection>>(){}.getType();

        // Assigns contents of json to collectionArrayList
        collectionArrayList = gson.fromJson(collectionJSONString, collectionType);

        Log.d("FIRST IN ARRAY", collectionArrayList.get(0).getCollection());

        sectionsArrayList = new ArrayList<>();

        for(Collection collection : collectionArrayList) {
            sectionsArrayList.add(new Pebble(collection.getHeader(), collection.getCollection()));
        }

        Log.d("HEADER FILE", sectionsArrayList.get(0).getBackground_image());
    }

    /*
     *  onItemClick
     *  Attaches an onClickEvent to all items in RecyclerView
     *  View view - reference to current view
     *  int position - position of clicked item in the recylcerview list
     *  void
     */
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