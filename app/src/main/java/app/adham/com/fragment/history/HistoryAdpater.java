package app.adham.com.fragment.history;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.adham.com.R;
import app.adham.com.data.model.booking.BookingDetail;
import app.adham.com.data.model.booking.BookingsList;
import app.adham.com.fragment.order.OrderDetailDialog;


public class HistoryAdpater extends RecyclerView.Adapter<HistoryAdpater.ViewHolder> {

    private List<BookingsList> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    FragmentManager fragmentManager;
    private int width, height;

    // data is passed into the constructor
    HistoryAdpater(Context context, List<BookingsList> data,FragmentManager fragmentManager) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        this.fragmentManager=fragmentManager;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        double cellWidth = width;
        double cellHeight = height * 0.15;
        View view = mInflater.inflate(R.layout.item_history, parent, false);
    /*    LinearLayout imageView = view.findViewById(R.id.ll_item_econmyCar);
        // Set height and width constraints for the image view
        imageView.setLayoutParams(new LinearLayout.LayoutParams((int) cellWidth, (int) cellHeight));

        // Set Padding for images
        imageView.setPadding(1, 1, 1, 1);*/
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookingsList order = mData.get(position);
        if (order.getMessage().contains(":")) {
            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(order.getMessage());
            Gson gson = new Gson();
            BookingDetail object = gson.fromJson(mJson, BookingDetail.class);
            if (object.getCarImage() != null)
                Picasso.get().load(object.getCarImage()).into(holder.carImage);
            holder.tvTotal.setText("$"+object.getTotalPrice());
        }
        holder.tvCarName.setText(order.getVehicleTitle());
        holder.tvDates.setText(order.getPostingDate());
        holder.tvOrderNo.setText("" + order.getBookingID());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCarName, tvDates, tvOrderNo, tvTotal, tvBodyguardDriver;
        ImageView carImage;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderDetailDialog.newInstance(mData.get(getAdapterPosition()))
                            .show(fragmentManager);
                }
            });
            carImage = itemView.findViewById(R.id.ivHistoryCarImage);
            tvCarName = itemView.findViewById(R.id.tvHistoryCarName);
            tvOrderNo = itemView.findViewById(R.id.tvHistoryOrderNo);
            tvTotal = itemView.findViewById(R.id.tvHistoryTotal);
            tvDates = itemView.findViewById(R.id.tvHistoryOrderDate);
            tvBodyguardDriver = itemView.findViewById(R.id.tvHistoryBodyguardorDriver);
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