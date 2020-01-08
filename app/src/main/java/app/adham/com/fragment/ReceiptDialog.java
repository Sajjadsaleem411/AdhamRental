package app.adham.com.fragment;

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

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import app.adham.com.R;
import app.adham.com.activities.LoginActivity;
import app.adham.com.activities.MapsActivity;
import app.adham.com.data.model.booking.Booking;
import app.adham.com.data.model.car.CarsList;
import app.adham.com.utils.AppConstants;

import static app.adham.com.AdhamApp.mPreference;
import static app.adham.com.utils.CommonUtils.DATE_FORMAT;
import static app.adham.com.utils.CommonUtils.POST_DATE_FORMAT;

public class ReceiptDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = ReceiptDialog.class.getSimpleName();
    private CarsList carsList;
    public static Booking booking;
    private String brandID;
    private String[] array;
    TextView tvCarName, tvDate, tvSubTotal, tvTotal, tvDriver, tvBodyguard,tvTax;
    private double total;
    private double[] driverPrices = {0.0, 20.0, 30.0, 40.0};
    private double[] bodyguardPrices = {0.0, 20.0, 30.0, 40.0};
    private double tax=0.2;
    private long days=0;

    public static ReceiptDialog newInstance(CarsList carsList, String brandID, Booking booking) {
        ReceiptDialog fragment = new ReceiptDialog();
        fragment.carsList = carsList;
        fragment.brandID = brandID;
        fragment.booking = booking;
        return fragment;
    }

    public void dismissDialog() {
        dismiss();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_receipt, container, false);
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
            dialog.getWindow().setGravity(Gravity.BOTTOM);
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
        array = getResources().getStringArray(R.array.choiceArray);
        days=getDaysBetweenDates(booking.getFromDate(),booking.getToDate());
        ImageView imageView=view.findViewById(R.id.imageView);
        days++;
        Picasso.get().load(carsList.getCarImages().get(0).getImagePath()).into(imageView);

        view.findViewById(R.id.rlShareLocation).setOnClickListener(this);
        tvCarName = view.findViewById(R.id.tvReceiptCarName);
        tvTax = view.findViewById(R.id.tvTax);
        tvDate = view.findViewById(R.id.tvReceiptDate);
        tvSubTotal = view.findViewById(R.id.tvReceiptSubTotal);
        TextView tvDays= view.findViewById(R.id.tvReceiptDays);
        tvDays.setText(days+" "+getResources().getString(R.string.daysRent));
        tvDriver = view.findViewById(R.id.tvDriverPrice);
        tvBodyguard = view.findViewById(R.id.tvBodyguardPrice);
        tvTotal = view.findViewById(R.id.tvReceiptTotal);
        tvDate.setText(booking.getFromDate() + "  " + booking.getToDate());
        tvSubTotal.setText(AppConstants.currency+ Integer.parseInt(booking.price)*days );
        tvCarName.setText(booking.carName);
        total = Double.parseDouble(booking.price)*days;

        setPrice(tvBodyguard, booking.isBodyGuard, bodyguardPrices);
        setPrice(tvDriver, booking.isDriver, driverPrices);
        tvTax = view.findViewById(R.id.tvTax);
        double taxPrice=total*tax;

        tvTax.setText(AppConstants.currency+ (taxPrice));
        total+=taxPrice;

        tvTotal.setText(AppConstants.currency+ total);
    //    tvTotal.setText("$" + total + " " + getResources().getString(R.string.perDay));

    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rlShareLocation:
                if (mPreference.getBoolean(mPreference.isLogin)) {
                    booking.totalPrice = "" + total;
                    booking.brandId = brandID;
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    intent.putExtra("BrandID", brandID);

                    startActivity(intent);
                }
                else {
                    openDialog(getContext());
                }
                break;
        }

    }

    private void setPrice(TextView tv, String status, double[] prices) {
        if (status.contentEquals(array[0])) {
            tv.setText(AppConstants.currency+ prices[0]*days );
            total += prices[0]*days;
        } else if (status.contentEquals(array[1])) {
            tv.setText(AppConstants.currency+ + prices[1]*days );
            total += prices[1]*days;
        } else if (status.contentEquals(array[2])) {
            tv.setText(AppConstants.currency+ + prices[2]*days );
            total += prices[2]*days;
        } else if (status.contentEquals(array[3])) {
            tv.setText(AppConstants.currency+ + prices[3]*days );
            total += prices[3]*days;
        }
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
     long getDaysBetweenDates(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(POST_DATE_FORMAT, Locale.ENGLISH);
        Date startDate, endDate;
        long numberOfDays = 0;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numberOfDays;
    }
    long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
        long timeDiff = endDate.getTime() - startDate.getTime();
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
    }
}