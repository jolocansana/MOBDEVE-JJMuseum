package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ph.edu.dlsu.s12.jandj.jjmuseum.model.Comment;

public class PieceActivity extends AppCompatActivity {

    private ImageView back_button;
    private Button btn_comment;
    private ImageView itemIv, item1Iv, item2Iv, item3Iv;
    private TextView header_title, item_nameTv, item_collectionTv, item_time_originTv, item_descriptionTv;
    private VideoView videoView;
    private EditText commentEt, nameEt;
    private ArrayList<String> assetsArrayList;
    private ArrayList<Comment> comments;
    private Uri mediaUri;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String pieceID;
    private CommentAdapter commentAdapter;
    private ListView commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);

        comments = new ArrayList<>();
        commentList = (ListView)findViewById(R.id.comment_listLv);

        commentAdapter = new CommentAdapter(this, comments);
        commentList.setAdapter(commentAdapter);

        init();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                nameEt.setText("");
                commentEt.setText("");
            }
        });

        item1Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIv.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
            }
        });

        item2Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIv.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
                itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(1),null, getPackageName())));
            }
        });

        item3Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!assetsArrayList.get(2).equals("nh03_3")) {
                    itemIv.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.GONE);
                    itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(2),null, getPackageName())));
                } else {
                    itemIv.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                    mediaUri = Uri.parse("android.resource://" + getPackageName() +
                            "/raw/" + "nh03_3");
                    videoView.setVideoURI(mediaUri);
                    videoView.start();
                }
            }
        });

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //only add comment if there are things in name and comment

                if (nameEt.getText().toString().equals("") || commentEt.getText().toString().equals("")){
                    Log.d("Comment Status", "Comment Failed");
                }else {

                    Comment comment = new Comment(
                            pieceID,
                            nameEt.getText().toString(),
                            commentEt.getText().toString()
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

                    nameEt.setText("");
                    commentEt.setText("");
                }
            }
        });

    }

    private void init(){
        back_button = (ImageView) findViewById(R.id.back_button);
        btn_comment = (Button) findViewById(R.id.btn_comment);

        itemIv = (ImageView) findViewById(R.id.itemIv);
        item1Iv = (ImageView) findViewById(R.id.item1Iv);
        item2Iv = (ImageView) findViewById(R.id.item2Iv);
        item3Iv = (ImageView) findViewById(R.id.item3Iv);

        header_title = (TextView) findViewById(R.id.header_title);
        item_nameTv = (TextView)findViewById(R.id.item_nameTv);
        item_collectionTv = (TextView)findViewById(R.id.item_collectionTv);
        item_time_originTv = (TextView)findViewById(R.id.item_time_originTv);
        item_descriptionTv = (TextView)findViewById(R.id.item_descriptionTv);

        commentEt = (EditText)findViewById(R.id.commentEt);
        nameEt = (EditText)findViewById(R.id.nameEt);


        videoView = findViewById(R.id.videoview);

        Bundle bundle = getIntent().getExtras();

        header_title.setText(bundle.getString("Name"));
        item_nameTv.setText(bundle.getString("Name"));
        item_collectionTv.setText(bundle.getString("Collection"));
        item_time_originTv.setText(bundle.getString("Time"));
        item_descriptionTv.setText(bundle.getString("Description"));
        assetsArrayList = bundle.getStringArrayList("Assets");
        pieceID = bundle.getString("ID");


        Log.d( "List Size", "Size is " + assetsArrayList.size());
        itemIv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
        item1Iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(0),null, getPackageName())));
        item2Iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(1),null, getPackageName())));
        if ((assetsArrayList.get(2)).equals("")){
            item3Iv.setVisibility(View.GONE);
        }
        else{
            item3Iv.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("@drawable/"+assetsArrayList.get(2),null, getPackageName())));
        }

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("app/comments");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
//                comments.add(snapshot.getValue(Comment.class));
                if(snapshot.getValue(Comment.class).getId().equals(pieceID)){
                    commentAdapter.addComment(snapshot.getValue(Comment.class));
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