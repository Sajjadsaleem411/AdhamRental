
package app.adham.com.data.model.brand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BrandResponse {

    @SerializedName("BrandsList")
    @Expose
    private ArrayList<BrandsList> list = null;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Description")
    @Expose
    private String description;

    public ArrayList<BrandsList> getList() {
        return list;
    }

    public void setList(ArrayList<BrandsList> list) {
        this.list = list;
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
