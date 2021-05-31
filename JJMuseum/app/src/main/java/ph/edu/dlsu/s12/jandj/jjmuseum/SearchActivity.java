package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.controllers.Pebble;
import ph.edu.dlsu.s12.jandj.jjmuseum.model.Piece;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

public class SearchActivity extends AppCompatActivity implements PieceListAdapter.ItemClickListener {

    private ImageView back_button, search_button;
    private RecyclerView vertical_recyclerview;
    private EditText search_field_et;
    private TextView no_results_tv;

    private ArrayList<Pebble> piecePebbleArrayList;
    private ArrayList<Piece> pieceArrayList, searchArrayList;
    private PieceListAdapter pieceListAdapter;

    private String searchInput;

    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(search_field_et.getText().toString());
            }
        });

        pieceListAdapter = new PieceListAdapter(piecePebbleArrayList, this);
        pieceListAdapter.setClickListener(this);
        vertical_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(vertical_recyclerview.getContext(), DividerItemDecoration.HORIZONTAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.horizontal_spacing));
        vertical_recyclerview.addItemDecoration(dividerItemDecoration);

        dividerItemDecoration = new DividerItemDecoration(vertical_recyclerview.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.vertical_spacing));
        vertical_recyclerview.addItemDecoration(dividerItemDecoration);

        vertical_recyclerview.setAdapter(pieceListAdapter);
    }

    /*
     *  init
     *  initializes XML references and gets data from json files via Gson()
     *  void
     */
    private void init() {
        back_button = (ImageView) findViewById(R.id.back_button);
        search_button = (ImageView) findViewById(R.id.search_button);
        search_field_et = (EditText) findViewById(R.id.search_field_et);
        no_results_tv = (TextView) findViewById(R.id.no_results_tv);

        vertical_recyclerview = (RecyclerView) findViewById(R.id.vertical_recyclerview);

        String piecesJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "piecesdata.json");
        Log.d("JSON", piecesJSONString);

        Gson gson = new Gson();
        Type pieceType = new TypeToken<ArrayList<Piece>>(){}.getType();

        pieceArrayList = new ArrayList<>();
        pieceArrayList = gson.fromJson(piecesJSONString, pieceType);

        searchArrayList = new ArrayList<>();

        piecePebbleArrayList = new ArrayList<>();
    }

    /*
     *  search
     *  Lifecycle function to start QR Scanning on resume of activity
     *  String searchInput - The text query string to base the search from
     *  void
     */
    private void search(String searchInput) {

        searchArrayList.removeAll(searchArrayList);
        piecePebbleArrayList.removeAll(piecePebbleArrayList);

        // Checks if searchInput exists in the Name, Description, or Collection of the pieces
        for (Piece piece : pieceArrayList) {
            if(     piece.getName().toLowerCase().contains(searchInput.toLowerCase()) ||
                    piece.getDescription().toLowerCase().contains(searchInput.toLowerCase()) ||
                    piece.getCollection().toLowerCase().contains(searchInput.toLowerCase())
                ) {
                piecePebbleArrayList.add(new Pebble(piece.getHeader(), piece.getName()));
                searchArrayList.add(piece);
            }
        }

        // Updates dataset to show to user the results of the search
        pieceListAdapter.notifyDataSetChanged();

        // Displays a text if results were empty
        if(searchArrayList.isEmpty()) {
            no_results_tv.setVisibility(View.VISIBLE);
        } else {
            no_results_tv.setVisibility(View.GONE);
        }
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
        bundle.putString("ID", searchArrayList.get(position).getID());
        bundle.putString("Name", searchArrayList.get(position).getName());
        bundle.putString("Collection", searchArrayList.get(position).getCollection());
        bundle.putString("CollectionID", searchArrayList.get(position).getCollectionID());
        bundle.putString("Time", searchArrayList.get(position).getTime());
        bundle.putString("Description", searchArrayList.get(position).getDescription());
        bundle.putStringArrayList("Assets",searchArrayList.get(position).getAssets());
        PieceActivity.putExtras(bundle);
        startActivity(PieceActivity);
    }
}