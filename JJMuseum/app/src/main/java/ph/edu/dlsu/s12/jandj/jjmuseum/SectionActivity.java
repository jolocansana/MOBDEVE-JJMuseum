package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SectionActivity extends AppCompatActivity implements PieceListAdapter.ItemClickListener {

    private Button back_button;
    private RecyclerView vertical_recyclerview;

    private ArrayList<Pebble> pieceArrayList;
    private PieceListAdapter pieceListAdapter;

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

        pieceListAdapter = new PieceListAdapter(pieceArrayList, this);
        pieceListAdapter.setClickListener(this);
        vertical_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        vertical_recyclerview.setAdapter(pieceListAdapter);
    }

    private void init() {
        back_button = (Button) findViewById(R.id.back_button);

        vertical_recyclerview = (RecyclerView) findViewById(R.id.vertical_recyclerview);

        pieceArrayList = new ArrayList<>();
        pieceArrayList.add(new Pebble("sample_piece", "Piece 1"));
        pieceArrayList.add(new Pebble("sample_piece", "Piece 2"));
        pieceArrayList.add(new Pebble("sample_piece", "Piece 3"));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent PieceActivity = new Intent(getApplicationContext(), PieceActivity.class);
        startActivity(PieceActivity);
    }
}