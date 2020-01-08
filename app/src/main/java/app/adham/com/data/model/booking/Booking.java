package app.adham.com.data.model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kotlin.jvm.Transient;

public class Booking {

    transient  public String isDriver;

    transient public String isBodyGuard;

    transient public String carImage;
    transient public String brandId;

    transient public String totalPrice;
    transient public String ToDateFrom;
    transient public String price;
    transient public String customerName;
    transient public String customerNumber;
    transient public String carName;

    @SerializedName("BookingID")
    @Expose
    private Integer bookingID;
    @SerializedName("PostingDate")
    @Expose
    private String postingDate;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("UserEmail")
    @Expose
    private String userEmail;
    @SerializedName("VehicleID")
    @Expose
    private String vehicleID;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

}