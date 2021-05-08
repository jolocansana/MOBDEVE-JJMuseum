package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SectionListAdapter.ItemClickListener {

    private Button button;
    private RecyclerView horizontalScrollView;

    private SectionListAdapter sectionListAdapter;
    private ArrayList<Pebble> sectionsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        sectionListAdapter = new SectionListAdapter(sectionsArrayList, this);
        sectionListAdapter.setClickListener(this);
        horizontalScrollView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalScrollView.setAdapter(sectionListAdapter);
    }

    private void init() {
        horizontalScrollView = (RecyclerView) findViewById(R.id.horizontal_scrollview);

        sectionsArrayList = new ArrayList<>();
        sectionsArrayList.add(new Pebble("sample_piece", "Section 1"));
        sectionsArrayList.add(new Pebble("sample_piece", "Section 2"));
        sectionsArrayList.add(new Pebble("sample_piece", "Section 3"));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent SectionActivity = new Intent(getApplicationContext(), SectionActivity.class);
        startActivity(SectionActivity);
    }
}