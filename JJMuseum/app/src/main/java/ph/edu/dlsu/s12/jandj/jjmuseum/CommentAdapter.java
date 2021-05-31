package ph.edu.dlsu.s12.jandj.jjmuseum;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.model.Comment;


public class CommentAdapter extends ArrayAdapter<Comment> {

    private Activity activity;
    private ArrayList<Comment> commentsList;

    public CommentAdapter(Activity activity, ArrayList<Comment> commentsArrayList){
        super(activity, R.layout.comment_layout, commentsArrayList);
        this.activity = activity;
        this.commentsList = commentsArrayList;
    }

    public void addComment(Comment comment){
        commentsList.add(0, comment);
        notifyDataSetChanged();
        Log.d("CommentSize", "Size is " + commentsList.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView = convertView;

        if (rowView == null){
            //Create Layout
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.comment_layout, null);

            ViewHolder commentViewHolder = new ViewHolder();
            commentViewHolder.id = (TextView) rowView.findViewById(R.id.usernameTv);
            commentViewHolder.text = (TextView) rowView.findViewById(R.id.textTv);

            rowView.setTag(commentViewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        Comment comment = commentsList.get(position);
        holder.id.setText(comment.getUsername());
        holder.text.setText(comment.getText());

        return rowView;
    }

    static class ViewHolder{
        public TextView id;
        public TextView text;
    }

}
