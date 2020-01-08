
package app.adham.com.data.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("LoginInfo")
    @Expose
    private LoginInfo loginInfo;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ArabicDriverAmount")
    @Expose
    private String arabicDriverAmount;
    @SerializedName("FrenchDriverAmount")
    @Expose
    private String frenchDriverAmount;
    @SerializedName("ArabicBodyGuardAmount")
    @Expose
    private String arabicBodyGuardAmount;
    @SerializedName("FrenchBodyGuardAmount")
    @Expose
    private String frenchBodyGuardAmount;

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
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

    public String getArabicDriverAmount() {
        return arabicDriverAmount;
    }

    public void setArabicDriverAmount(String arabicDriverAmount) {
        this.arabicDriverAmount = arabicDriverAmount;
    }

    public String getFrenchDriverAmount() {
        return frenchDriverAmount;
    }

    public void setFrenchDriverAmount(String frenchDriverAmount) {
        this.frenchDriverAmount = frenchDriverAmount;
    }

    public String getArabicBodyGuardAmount() {
        return arabicBodyGuardAmount;
    }

    public void setArabicBodyGuardAmount(String arabicBodyGuardAmount) {
        this.arabicBodyGuardAmount = arabicBodyGuardAmount;
    }

    public String getFrenchBodyGuardAmount() {
        return frenchBodyGuardAmount;
    }

    public void setFrenchBodyGuardAmount(String frenchBodyGuardAmount) {
        this.frenchBodyGuardAmount = frenchBodyGuardAmount;
    }

}