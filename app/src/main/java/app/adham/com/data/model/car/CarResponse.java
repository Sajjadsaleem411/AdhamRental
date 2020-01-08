
package app.adham.com.data.model.car;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarResponse {

    @SerializedName("CarsList")
    @Expose
    private List<CarsList> carsList = null;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Description")
    @Expose
    private String description;

    public List<CarsList> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<CarsList> carsList) {
        this.carsList = carsList;
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
