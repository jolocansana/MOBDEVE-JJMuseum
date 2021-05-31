package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.controllers.Pebble;
import ph.edu.dlsu.s12.jandj.jjmuseum.model.Piece;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

public class SectionActivity extends AppCompatActivity implements PieceListAdapter.ItemClickListener {

    private ImageView back_btn;
    private RecyclerView vertical_rv;
    private TextView section_name_tv, section_description_tv;
    private ImageView section_header_photo_iv;

    private ArrayList<Pebble> piecePebbleArrayList;
    private PieceListAdapter pieceListAdapter;
    private ArrayList<Piece> pieceArrayList;
    private ArrayList<Piece> filteredArrayList;

    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        init();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pieceListAdapter = new PieceListAdapter(piecePebbleArrayList, this);
        pieceListAdapter.setClickListener(this);
        vertical_rv.setLayoutManager(new GridLayoutManager(this, 2));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(vertical_rv.getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.horizontal_spacing));
        vertical_rv.addItemDecoration(dividerItemDecoration);

        dividerItemDecoration = new DividerItemDecoration(vertical_rv.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.vertical_spacing));
        vertical_rv.addItemDecoration(dividerItemDecoration);

        vertical_rv.setAdapter(pieceListAdapter);
    }

    /*
     *  init
     *  initializes XML references and gets data from json files using Gson()
     *  void
     */
    private void init() {
        back_btn = (ImageView) findViewById(R.id.back_btn);

        vertical_rv = (RecyclerView) findViewById(R.id.vertical_rv);

        section_name_tv = (TextView) findViewById(R.id.section_name_tv);
        section_description_tv = (TextView) findViewById(R.id.section_description_tv);
        section_header_photo_iv = (ImageView) findViewById(R.id.section_header_photo_iv);

        Bundle bundle = getIntent().getExtras();
        section_name_tv.setText(bundle.getString("Collection"));
        section_description_tv.setText(bundle.getString("Description"));
        section_header_photo_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+bundle.get("Header"),null, getPackageName())));

        String piecesJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "piecesdata.json");
        Log.d("JSON", piecesJSONString);

        Gson gson = new Gson();
        Type pieceType = new TypeToken<ArrayList<Piece>>(){}.getType();

        pieceArrayList = gson.fromJson(piecesJSONString, pieceType);

        Log.d("FIRST IN ARRAY", pieceArrayList.get(0).getCollection());

        piecePebbleArrayList = new ArrayList<>();
        filteredArrayList = new ArrayList<>();

        for(Piece piece : pieceArrayList) {
            if(piece.getCollectionID().equals(bundle.get("ID"))) {
                filteredArrayList.add(piece);
                piecePebbleArrayList.add(new Pebble(piece.getHeader(), piece.getName()));
            }
        }

        Log.d("HEADER FILE", piecePebbleArrayList.get(0).getBackground_image());
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
        Intent PieceActivity = new Intent(getApplicationContext(), PieceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ID", filteredArrayList.get(position).getID());
        bundle.putString("Name", filteredArrayList.get(position).getName());
        bundle.putString("Collection", filteredArrayList.get(position).getCollection());
        bundle.putString("CollectionID", filteredArrayList.get(position).getCollectionID());
        bundle.putString("Time", filteredArrayList.get(position).getTime());
        bundle.putString("Description", filteredArrayList.get(position).getDescription());
        bundle.putStringArrayList("Assets",filteredArrayList.get(position).getAssets());
        PieceActivity.putExtras(bundle);
        startActivity(PieceActivity);
    }
}