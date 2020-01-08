package app.adham.com.data.model.car;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarImage {

    @SerializedName("ImagePath")
    @Expose
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}