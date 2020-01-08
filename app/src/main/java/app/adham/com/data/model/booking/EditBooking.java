package app.adham.com.data.model.booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditBooking {

    @SerializedName("BookingID")
    @Expose
    private Integer bookingID;
    @SerializedName("Status")
    @Expose
    private String status;

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}