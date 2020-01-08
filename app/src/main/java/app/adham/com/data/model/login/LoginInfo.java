
package app.adham.com.data.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("UserID")
    @Expose
    private Integer userID;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("EmaiId")
    @Expose
    private String emaiId;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("DOB")
    @Expose
    private String dOB;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("RegDate")
    @Expose
    private String regDate;
    @SerializedName("UpdationDate")
    @Expose
    private String updationDate;
    @SerializedName("IsAdmin")
    @Expose
    private Boolean isAdmin;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmaiId() {
        return emaiId;
    }

    public void setEmaiId(String emaiId) {
        this.emaiId = emaiId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdationDate() {
        return updationDate;
    }

    public void setUpdationDate(String updationDate) {
        this.updationDate = updationDate;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
