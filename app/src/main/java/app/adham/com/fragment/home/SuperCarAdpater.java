package app.adham.com.fragment.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.adham.com.R;
import app.adham.com.activities.carDetail.CarDetailActivity;


public class SuperCarAdpater extends RecyclerView.Adapter<SuperCarAdpater.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int previous=0;
    private Activity context;
    private boolean firstTime=true;
    private int width,height;

    // data is passed into the constructor
    SuperCarAdpater(Activity context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
        DisplayMetrics displayMetrics = new DisplayMetrics();

        displayMetrics = context.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     /*   double cellWidth = width * 0.4;
        double cellHeight = height * 0.15;*/

        View view = mInflater.inflate(R.layout.item_super_car, parent, false);
     /*   LinearLayout imageView = view.findViewById(R.id.ll_item_supercar);
        // Set height and width constraints for the image view
        imageView.setLayoutParams(new LinearLayout.LayoutParams((int) cellWidth, (int) cellHeight));

        // Set Padding for images
        imageView.setPadding(1, 1, 1, 1);*/

        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            context.startActivity(new Intent(context, CarDetailActivity.class));
            context.overridePendingTransition(R.anim.enter, R.anim.exit);
         //   if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
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