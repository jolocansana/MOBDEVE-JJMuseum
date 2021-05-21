package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

import ph.edu.dlsu.s12.jandj.jjmuseum.controllers.Pebble;
import ph.edu.dlsu.s12.jandj.jjmuseum.model.Piece;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

public class SectionActivity extends AppCompatActivity implements PieceListAdapter.ItemClickListener {

    private ImageView back_button;
    private RecyclerView vertical_recyclerview;
    private TextView section_name, section_description;
    private ImageView section_header_photo;

    private ArrayList<Pebble> piecePebbleArrayList;
    private PieceListAdapter pieceListAdapter;
    private ArrayList<Piece> pieceArrayList;
    private ArrayList<Piece> filteredArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        init();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void init() {
        back_button = (ImageView) findViewById(R.id.back_button);

        vertical_recyclerview = (RecyclerView) findViewById(R.id.vertical_recyclerview);

        section_name = (TextView) findViewById(R.id.section_name);
        section_description = (TextView) findViewById(R.id.section_description);
        section_header_photo = (ImageView) findViewById(R.id.section_header_photo);

        Bundle bundle = getIntent().getExtras();
        section_name.setText(bundle.getString("Collection"));
        section_description.setText(bundle.getString("Description"));
        section_header_photo.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+bundle.get("Header"),null, getPackageName())));

        String piecesJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "piecesdata.json");
        Log.d("JSON", piecesJSONString);

        Gson gson = new Gson();
        Type pieceType = new TypeToken<ArrayList<Piece>>(){}.getType();

        pieceArrayList = gson.fromJson(piecesJSONString, pieceType);

        Log.d("FIRST IN ARRAY", pieceArrayList.get(0).getCollection());

        filteredArrayList = new ArrayList<>();
        piecePebbleArrayList = new ArrayList<>();
        filteredArrayList = new ArrayList<>();

        for(Piece piece : pieceArrayList) {
            if(piece.getCollectionID().equals(bundle.get("ID"))) {
                filteredArrayList.add(piece);
                piecePebbleArrayList.add(new Pebble(piece.getAsset(0), piece.getName()));
            }
        }

        Log.d("HEADER FILE", piecePebbleArrayList.get(0).getBackground_image());
    }

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