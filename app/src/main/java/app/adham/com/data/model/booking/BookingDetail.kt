package app.adham.com.data.model.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookingDetail {

    @SerializedName("brandId")
    @Expose
    var brandId: String? = null
    @SerializedName("totalPrice")
    @Expose
    var totalPrice: String? = null
    @SerializedName("carPrice")
    @Expose
    var carPrice: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("airport")
    @Expose
    var airport: String? = null
    @SerializedName("carName")
    @Expose
    var carName: String? = null
    @SerializedName("carImage")
    @Expose
    var carImage: String? = null
    @SerializedName("customerName")
    @Expose
    var customerName: String? = null
    @SerializedName("customerNumber")
    @Expose
    var customerNumber: String? = null
    @SerializedName("isBodyguard")
    @Expose
    var bodyguard: String? = null
    @SerializedName("isDriver")
    @Expose
    var driver: String? = null
}