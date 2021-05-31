package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.model.Comment;

public class PieceActivity extends AppCompatActivity {

    private ImageView back_btn;
    private Button comment_btn;
    private ImageView item_iv, item1_iv, item2_iv, item3_iv;
    private TextView header_title_tv, item_name_tv, item_collection_tv, item_time_origin_tv, item_description_tv;
    private VideoView videoView;
    private EditText comment_et, name_et;

    private ArrayList<String> assetsArrayList;
    private ArrayList<Comment> comments;
    private Uri mediaUri;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String pieceID;
    private CommentAdapter commentAdapter;
    private ListView commentList;
    private NestedScrollView commentsSv;

    private ConstraintLayout.LayoutParams constraintLayout;


    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);

        comments = new ArrayList<>();
        commentList = (ListView)findViewById(R.id.comment_list_lv);

        commentAdapter = new CommentAdapter(this, comments);
        commentList.setAdapter(commentAdapter);

        init();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                name_et.setText("");
                comment_et.setText("");
            }
        });

        item1_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_iv.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                item_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
            }
        });

        item2_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_iv.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                item_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(1),null, getPackageName())));
            }
        });

        item3_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!assetsArrayList.get(2).equals("nh03_3")) {
                    item_iv.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.GONE);
                    item_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(2),null, getPackageName())));
                } else {
                    item_iv.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                    mediaUri = Uri.parse("android.resource://" + getPackageName() +
                            "/raw/" + "nh03_3");
                    videoView.setVideoURI(mediaUri);
                    videoView.start();
                }
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //only add comment if there are things in name and comment

                if (name_et.getText().toString().equals("") || comment_et.getText().toString().equals("")){
                    Log.d("Comment Status", "Comment Failed");
                }else {

                    Comment comment = new Comment(
                            pieceID,
                            name_et.getText().toString(),
                            comment_et.getText().toString()
                    );

                    myRef.push().setValue(comment,
                            new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError error, DatabaseReference ref) {
                                    if (error != null) {
                                        Log.d("ERROR", "ERROR : " + error.getMessage());
                                    } else {
                                        Log.d("SUCCESS", "DATA INSERTED");
                                    }
                                }
                            });

                    name_et.setText("");
                    comment_et.setText("");
                }
            }
        });

    }

    /*
     *  init
     *  initializes XML references and connects to Firebase for comments
     *  void
     */
    private void init(){
        back_btn = (ImageView) findViewById(R.id.back_btn);
        comment_btn = (Button) findViewById(R.id.comment_btn);

        item_iv = (ImageView) findViewById(R.id.item_iv);
        item1_iv = (ImageView) findViewById(R.id.item1_iv);
        item2_iv = (ImageView) findViewById(R.id.item2_iv);
        item3_iv = (ImageView) findViewById(R.id.item3_iv);

        header_title_tv = (TextView) findViewById(R.id.header_title_tv);
        item_name_tv = (TextView)findViewById(R.id.item_name_tv);
        item_collection_tv = (TextView)findViewById(R.id.item_collection_tv);
        item_time_origin_tv = (TextView)findViewById(R.id.item_time_origin_tv);
        item_description_tv = (TextView)findViewById(R.id.item_description_tv);

        comment_et = (EditText)findViewById(R.id.comment_et);
        name_et = (EditText)findViewById(R.id.name_et);

        commentsSv = findViewById(R.id.comments_sv);

        videoView = findViewById(R.id.videoview);

        Bundle bundle = getIntent().getExtras();

        header_title_tv.setText(bundle.getString("Name"));
        item_name_tv.setText(bundle.getString("Name"));
        item_collection_tv.setText(bundle.getString("Collection"));
        item_time_origin_tv.setText(bundle.getString("Time"));
        item_description_tv.setText(bundle.getString("Description"));
        assetsArrayList = bundle.getStringArrayList("Assets");
        pieceID = bundle.getString("ID");


        Log.d( "List Size", "Size is " + assetsArrayList.size());
        item_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
        item1_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
        item2_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(1),null, getPackageName())));
        if ((assetsArrayList.get(2)).equals("")){
            item3_iv.setVisibility(View.GONE);
        }
        else{
            item3_iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(2),null, getPackageName())));
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("app/comments");;

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
//                comments.add(snapshot.getValue(Comment.class));
                if(snapshot.getValue(Comment.class).getId().equals(pieceID)){
                    commentAdapter.addComment(snapshot.getValue(Comment.class));
                    if (comments.size() < 5) {
                        constraintLayout = new ConstraintLayout.LayoutParams(ActionBar.LayoutParams.FILL_PARENT, 150*comments.size());
                        constraintLayout.topToBottom = R.id.comments_ll;
                        commentsSv.setLayoutParams(constraintLayout);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) { }

            @Override
            public void onChildRemoved( DataSnapshot snapshot) { }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) { }

            @Override
            public void onCancelled(DatabaseError error) { }
        };

        myRef.addChildEventListener(childEventListener);

//        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete( Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    //Log.d("firebase", String.valueOf(task.getResult().getValue()));
//
//                    for(DataSnapshot data : task.getResult().getChildren()) {
//                        if(data.getValue(Comment.class).getId().equals(pieceID)){
//                            commentAdapter.addComment(data.getValue(Comment.class));
//                        }
//                    }
//
//                    /*
//                    for(int index = 0; index < comments.size(); index++){
//                        Log.d("contents", comments.get(index).getText());
//                    }
//                    */
//
//                }
//            }
//        });

    }
}