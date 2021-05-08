package ph.edu.dlsu.s12.jandj.jjmuseum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PieceListAdapter extends RecyclerView.Adapter<PieceListAdapter.PieceViewHolder> {
    private ArrayList<Pebble> pieceArrayList;
    private Context context;
    private PieceListAdapter.ItemClickListener itemClickListener;

    public PieceListAdapter(ArrayList<Pebble> pieceArrayList, Context context) {
        this.pieceArrayList = pieceArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PieceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.pebble_layout, null);
        return new PieceViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull PieceListAdapter.PieceViewHolder holder, int position) {
        Pebble info = pieceArrayList.get(position);
        holder.text_caption.setText(info.getText_caption());
        int drawableId = context.getResources().getIdentifier(info.getBackground_image(), "drawable", context.getPackageName());
        holder.background_image.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        return pieceArrayList.size();
    }

    public class PieceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text_caption;
        public ImageView background_image;

        public PieceViewHolder(@NonNull View itemView) {
            super(itemView);
            background_image = (ImageView) itemView.findViewById(R.id.background_image);
            text_caption = (TextView) itemView.findViewById(R.id.text_caption);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(PieceListAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
