package ph.edu.dlsu.s12.jandj.jjmuseum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.controllers.Pebble;

public class PieceListAdapter extends RecyclerView.Adapter<PieceListAdapter.PieceViewHolder> {
    private ArrayList<Pebble> pieceArrayList;
    private Context context;
    private PieceListAdapter.ItemClickListener itemClickListener;

    /*
     * PieceListAdapter
     * constructs the PieceListAdapter object
     * ArrayList<Pebble> pieceArrayList - collection of pieces
     * Context context - context of the current activity
     * void
     */
    public PieceListAdapter(ArrayList<Pebble> pieceArrayList, Context context) {
        this.pieceArrayList = pieceArrayList;
        this.context = context;
    }

    /*
     *  onCreateViewHolder
     *  Inflates a new layout
     *  ViewGroup parent - special view that contains other view
     *  int viewType - integer that determines which ViewHolder should the item data bind
     *  new layout PieceViewHolder(rowView)
     */
    @NonNull
    @Override
    public PieceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.pebble_layout, null);
        return new PieceViewHolder(rowView);
    }

    /*
     *  onBindViewHolder
     *  Recycling the view
     *  PieceListAdapter.PieceViewHolder holder - holder for the new layout
     *  int position - current position of the added viewholder
     *  void
     */
    @Override
    public void onBindViewHolder(@NonNull PieceListAdapter.PieceViewHolder holder, int position) {
        Pebble info = pieceArrayList.get(position);
        holder.text_caption.setText(info.getText_caption());
        int drawableId = context.getResources().getIdentifier(info.getBackground_image(), "drawable", context.getPackageName());
        holder.background_image.setImageResource(drawableId);
    }

    /*
     *  getItemCount
     *  getting the size of the pieceArrayList
     *  void
     *  int pieceArrayList.size()
     */
    @Override
    public int getItemCount() {
        return pieceArrayList.size();
    }

    public class PieceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text_caption;
        public ImageView background_image;

        /*
         * PieceHolder
         * constructs the PieceViewHolder object
         * View itemView - new layout created for the object
         * void
         */
        public PieceViewHolder(@NonNull View itemView) {
            super(itemView);
            background_image = itemView.findViewById(R.id.background_image);
            text_caption =  itemView.findViewById(R.id.text_caption);
            itemView.setOnClickListener(this);
        }

        /*
         *  onClick
         *  gets the position of the clicked object
         *  View view - reference to current view
         *  void
         */
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(PieceListAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
