
package app.adham.com.data.remote;

import app.adham.com.data.model.ApiResponse;
import app.adham.com.data.model.booking.Booking;
import app.adham.com.data.model.JoinInfo;
import app.adham.com.data.model.booking.BookingResponse;
import app.adham.com.data.model.booking.EditBooking;
import app.adham.com.data.model.brand.BrandResponse;
import app.adham.com.data.model.car.CarResponse;
import app.adham.com.data.model.login.LoginInfo;
import app.adham.com.data.model.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiHelper {
    @GET("login/{email}/{password}")
    Call<LoginResponse> Login(@Path("email") String email, @Path("password") String password);

    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<ApiResponse> SignUp(@Body LoginInfo info);

    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<ApiResponse> JoinNow(@Body JoinInfo info);

    @Headers("Content-Type: application/json")
    @POST("car/bookings/add/")
    Call<ApiResponse> booking(@Body Booking info);

    @Headers("Content-Type: application/json")
    @POST("car/bookings/edit/")
    Call<ApiResponse> editBooking(@Body EditBooking info);

    @GET("brands")
    Call<BrandResponse> getAllBrands();

    @GET("cars/{carID}/{brandID}")
    Call<CarResponse> getCars(@Path("carID") String carID, @Path("brandID") String brandID);

    @GET("bookings/{bookingID}")
    Call<BookingResponse> getBookingOrder(@Path("bookingID") String bookingID);
    @GET("bookings/history/{email}/")
    Call<BookingResponse> getHistory(@Path("email") String email);
}
