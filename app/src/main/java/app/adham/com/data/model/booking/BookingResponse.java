
package app.adham.com.data.model.booking;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingResponse {

    @SerializedName("BookingsList")
    @Expose
    private List<BookingsList> bookingsList = null;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Description")
    @Expose
    private String description;

    public List<BookingsList> getBookingsList() {
        return bookingsList;
    }

    public void setBookingsList(List<BookingsList> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
