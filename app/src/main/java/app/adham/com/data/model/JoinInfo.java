package app.adham.com.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinInfo {

    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("EmaiID")
    @Expose
    private String emaiID;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("LicenseNo")
    @Expose
    private String licenseNo;
    @SerializedName("JoinAs")
    @Expose
    private String joinAs;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmaiID() {
        return emaiID;
    }

    public void setEmaiID(String emaiID) {
        this.emaiID = emaiID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getJoinAs() {
        return joinAs;
    }

    public void setJoinAs(String joinAs) {
        this.joinAs = joinAs;
    }

}