package app.adham.com.fragment.order;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import app.adham.com.R;
import app.adham.com.activities.LoginActivity;
import app.adham.com.activities.MapsActivity;
import app.adham.com.data.model.booking.Booking;
import app.adham.com.data.model.booking.BookingDetail;
import app.adham.com.data.model.booking.BookingsList;
import app.adham.com.data.model.car.CarsList;

import static app.adham.com.AdhamApp.mPreference;

public class OrderDetailDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = OrderDetailDialog.class.getSimpleName();

    public BookingsList bookingsList;

    public static OrderDetailDialog newInstance(BookingsList bookingsList) {
        OrderDetailDialog fragment = new OrderDetailDialog();
        fragment.bookingsList = bookingsList;
        return fragment;
    }

    public void dismissDialog() {
        dismiss();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_order_detail, container, false);
        setUI(view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        }
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public interface checkout_callback {
        void isCheckout(boolean flag);
    }


    private void setUI(View view) {

        if (bookingsList.getMessage().contains(":")) {
            JsonParser parser = new JsonParser();
            JsonElement mJson = parser.parse(bookingsList.getMessage());
            Gson gson = new Gson();
            BookingDetail object = gson.fromJson(mJson, BookingDetail.class);
            if(object.getCarImage()!=null)
                Picasso.get().load(object.getCarImage())
                        .into(((ImageView)view.findViewById(R.id.ivBookingCarImage)));

            ((TextView)view.findViewById(R.id.tvOrderPhoneNo))
                    .setText(object.getCustomerNumber());
            ((TextView)view.findViewById(R.id.tvOrderUserName))
                    .setText(object.getCustomerName());
            ((TextView)view.findViewById(R.id.tvOrdertotal))
                    .setText(object.getTotalPrice());
            ((TextView)view.findViewById(R.id.tvOrderDriver))
                    .setText(object.getDriver());
            ((TextView)view.findViewById(R.id.tvOrderBodyguard))
                    .setText(object.getBodyguard());
            if(!object.getAirport().isEmpty()) {
                ((TextView) view.findViewById(R.id.tvOrderAirportorAddress))
                        .setText(object.getAirport());
            }
            else if(!object.getAddress().isEmpty()) {
                ((TextView) view.findViewById(R.id.tvOrderAirportorAddress))
                        .setText(object.getAddress());
            }

        }
        ((TextView)view.findViewById(R.id.tvOrderCarName))
                .setText(bookingsList.getVehicleTitle());
        ((TextView)view.findViewById(R.id.tvOrderDates))
                .setText(bookingsList.getFromDate()+" "+bookingsList.getToDate());
        ((TextView)view.findViewById(R.id.tvOrderOrderNo))
                .setText(""+bookingsList.getBookingID());

    /*    array = getResources().getStringArray(R.array.choiceArray);
        view.findViewById(R.id.rlShareLocation).setOnClickListener(this);
        tvCarName = view.findViewById(R.id.tvReceiptCarName);
        tvTax = view.findViewById(R.id.tvTax);
        tvDate = view.findViewById(R.id.tvReceiptDate);
        tvSubTotal = view.findViewById(R.id.tvReceiptSubTotal);
        tvDriver = view.findViewById(R.id.tvDriverPrice);
        tvBodyguard = view.findViewById(R.id.tvBodyguardPrice);
        tvTotal = view.findViewById(R.id.tvReceiptTotal);
        tvDate.setText(booking.getFromDate() + "  " + booking.getToDate());
        tvSubTotal.setText("$" + booking.price + " " + getResources().getString(R.string.perDay));
        tvCarName.setText(booking.carName);
        total = Double.parseDouble(booking.price);
        setPrice(tvBodyguard, booking.isBodyGuard, bodyguardPrices);
        setPrice(tvDriver, booking.isDriver, driverPrices);
        tvTax = view.findViewById(R.id.tvTax);
        double taxPrice=total*tax;
        tvTax.setText("$" + (taxPrice));
        total+=taxPrice;
        tvTotal.setText("$" + total + " " + getResources().getString(R.string.perDay));*/
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();


    }


    public void openDialog(Context context) {
        final Dialog dialog = new Dialog(context); // Context, this, etc.
        dialog.setContentView(R.layout.dialog);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_dialog_msg);
        //    textView.setText(msg);
        //dialog.setTitle(msg);
        Button button = (Button) dialog.findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginActivity.Companion.newInstance(getActivity());
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }
}