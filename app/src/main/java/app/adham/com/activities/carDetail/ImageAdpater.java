package app.adham.com.activities.carDetail;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.adham.com.R;
import app.adham.com.data.model.car.CarImage;


public class ImageAdpater extends RecyclerView.Adapter<ImageAdpater.ViewHolder> {

    private List<CarImage> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int previous=0;
    private Context context;
    private boolean firstTime=true;

    // data is passed into the constructor
    ImageAdpater(Context context, List<CarImage> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_detail, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.get().load(mData.get(position).getImagePath()).into(holder.carImage);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carImage;
        ViewHolder(View itemView) {
            super(itemView);
            carImage=(ImageView)itemView.findViewById(R.id.ivCarImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}