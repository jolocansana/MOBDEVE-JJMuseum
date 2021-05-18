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

public class SectionListAdapter extends RecyclerView.Adapter<SectionListAdapter.ViewHolder> {
    private ArrayList<Pebble> sectionArrayList;
    private Context context;
    private ItemClickListener itemClickListener;

    public SectionListAdapter(ArrayList<Pebble> sectionArrayList, Context context) {
        this.sectionArrayList = sectionArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.pebble_layout, null);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pebble info = sectionArrayList.get(position);
        holder.text_caption.setText(info.getText_caption());
        int drawableId = context.getResources().getIdentifier(info.getBackground_image(), "drawable", context.getPackageName());
        holder.background_image.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        return sectionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text_caption;
        public ImageView background_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            background_image = (ImageView) itemView.findViewById(R.id.background_image);
            text_caption = (TextView) itemView.findViewById(R.id.text_caption);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
