
package app.adham.com.data.model.brand;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandsList {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("BrandName")
    @Expose
    private String brandName;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}