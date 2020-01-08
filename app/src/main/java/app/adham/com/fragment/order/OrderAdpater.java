package app.adham.com.fragment.order;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.adham.com.AdhamApp;
import app.adham.com.R;
import app.adham.com.data.model.ApiResponse;
import app.adham.com.data.model.booking.BookingDetail;
import app.adham.com.data.model.booking.BookingsList;
import app.adham.com.data.model.booking.EditBooking;
import app.adham.com.data.remote.ApiHelper;
import app.adham.com.utils.CommonUtils;
import app.adham.com.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Response;


public class OrderAdpater extends RecyclerView.Adapter<OrderAdpater.ViewHolder> {

    private List<BookingsList> mData;
    private LayoutInflater mInflater;
    private OrderClickListener mClickListener;
    private Context context;
    private int status;
    private int width,height;
    FragmentManager fragmentManager;

    // data is passed into the constructor
    OrderAdpater(Context context, List<BookingsList> data,
                 int status,OrderClickListener clickListener,FragmentManager fragmentManager) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context=context;
        this.status=status;
        this.fragmentManager=fragmentManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mClickListener=clickListener;

        displayMetrics = context.getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        double cellWidth = width;
        double cellHeight = height * 0.15;

        View view = mInflater.inflate(R.layout.item_order, parent, false);
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
        BookingsList order=mData.get(position);
        if (order.getMessage().contains(":")) {
            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(order.getMessage());
            Gson gson = new Gson();
            BookingDetail object = gson.fromJson(mJson, BookingDetail.class);
            if(object.getCarImage()!=null)
            Picasso.get().load(object.getCarImage()).into(holder.carImage);
            holder.tvUserName.setText(object.getCustomerName());
            holder.tvPhone.setText(object.getCustomerNumber());

        }
        holder.tvCarName.setText(order.getVehicleTitle());
        holder.tvDates.setText(order.getFromDate()+" "+order.getToDate());
        holder.tvOrderNo.setText(""+order.getBookingID());



    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button accept,reject,cancel;
        TextView tvCarName,tvDates,tvOrderNo,tvUserName,tvPhone;
        ImageView carImage;
        ViewHolder(View itemView) {
            super(itemView);

            carImage = itemView.findViewById(R.id.ivBookingCarImage);
            accept = itemView.findViewById(R.id.btnOrderAccept);
            reject = itemView.findViewById(R.id.btnOrderReject);
            cancel = itemView.findViewById(R.id.btnOrderCancel);
            tvCarName=itemView.findViewById(R.id.tvOrderCarName);
            tvDates=itemView.findViewById(R.id.tvOrderDates);
            tvOrderNo=itemView.findViewById(R.id.tvOrderOrderNo);
            tvUserName=itemView.findViewById(R.id.tvOrderUserName);
            tvPhone=itemView.findViewById(R.id.tvOrderPhoneNo);
            accept.setOnClickListener(this);
            reject.setOnClickListener(this);
            cancel.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderDetailDialog.newInstance(mData.get(getAdapterPosition()))
                            .show(fragmentManager);
                }
            });
            switch (status){
                case 0:
                    accept.setVisibility(View.VISIBLE);
                    reject.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    break;

                case 1:
                    accept.setVisibility(View.GONE);
                    reject.setVisibility(View.GONE);
                    cancel.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    accept.setVisibility(View.GONE);
                    reject.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            EditBooking editBooking=new EditBooking();
            switch (view.getId()){
                case R.id.btnOrderAccept:
                    editBooking.setBookingID(mData.get(getAdapterPosition()).getBookingID());
                    editBooking.setStatus("1");
                    callAPI(editBooking);
                    break;

                case R.id.btnOrderReject:
                    editBooking.setBookingID(mData.get(getAdapterPosition()).getBookingID());
                    editBooking.setStatus("2");
                    callAPI(editBooking);
                    break;

                case R.id.btnOrderCancel:
                    editBooking.setBookingID(mData.get(getAdapterPosition()).getBookingID());
                    editBooking.setStatus("2");
                    callAPI(editBooking);

                    break;
            }
        }

    }


    // allows clicks events to be caught
    void setClickListener(OrderClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface OrderClickListener {
        void onItemClick();
    }
    private void callAPI(EditBooking editBooking) {

        if (NetworkUtils.isNetworkConnected(context)) {
            final ProgressDialog dialog = CommonUtils.showLoadingDialog(context);

            ApiHelper apiService =
                    AdhamApp.retrofit.create(ApiHelper.class);
            Call<ApiResponse> call = apiService.editBooking(editBooking);
            call.enqueue(new retrofit2.Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    ApiResponse data = response.body();
                    if(data.getStatus()==1){
                        mClickListener.onItemClick();
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(context, context.getResources().getString(R.string.internetError), Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                    // Log error here since request failed

                }
            });
        }
    }
}