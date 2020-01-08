package app.adham.com.data.model.car;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CarsList {

    @SerializedName("CarImages")
    @Expose
    private List<CarImage> carImages = null;
    @SerializedName("VehicleID")
    @Expose
    private Integer vehicleID;
    @SerializedName("VehiclesTitle")
    @Expose
    private String vehiclesTitle;
    @SerializedName("VehiclesBrand")
    @Expose
    private String vehiclesBrand;
    @SerializedName("VehicleOverview")
    @Expose
    private String vehicleOverview;
    @SerializedName("PricePerDay")
    @Expose
    private String pricePerDay;
    @SerializedName("FuelType")
    @Expose
    private String fuelType;
    @SerializedName("ModelYear")
    @Expose
    private String modelYear;
    @SerializedName("SeatingCapacity")
    @Expose
    private String seatingCapacity;
    @SerializedName("VImage1")
    @Expose
    private String vImage1;
    @SerializedName("VImage2")
    @Expose
    private String vImage2;
    @SerializedName("VImage3")
    @Expose
    private String vImage3;
    @SerializedName("VImage4")
    @Expose
    private String vImage4;
    @SerializedName("VImage5")
    @Expose
    private String vImage5;
    @SerializedName("AirConditioner")
    @Expose
    private String airConditioner;
    @SerializedName("PowerDoorLock")
    @Expose
    private String powerDoorLock;
    @SerializedName("AntiLockBrakingSystem")
    @Expose
    private String antiLockBrakingSystem;
    @SerializedName("BrakeAssit")
    @Expose
    private String brakeAssit;
    @SerializedName("PowerSteering")
    @Expose
    private String powerSteering;
    @SerializedName("DriveAirBag")
    @Expose
    private String driveAirBag;
    @SerializedName("PassengerAirBag")
    @Expose
    private String passengerAirBag;
    @SerializedName("PowerWindows")
    @Expose
    private String powerWindows;
    @SerializedName("CDPlayer")
    @Expose
    private String cDPlayer;
    @SerializedName("CentralLocking")
    @Expose
    private String centralLocking;
    @SerializedName("CrashSensor")
    @Expose
    private String crashSensor;
    @SerializedName("LeatherSeats")
    @Expose
    private String leatherSeats;
    @SerializedName("RegDate")
    @Expose
    private String regDate;
    @SerializedName("UpdationDate")
    @Expose
    private String updationDate;

    public List<CarImage> getCarImages() {
        return carImages;
    }

    public void setCarImages(List<CarImage> carImages) {
        this.carImages = carImages;
    }

    public Integer getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehiclesTitle() {
        return vehiclesTitle;
    }

    public void setVehiclesTitle(String vehiclesTitle) {
        this.vehiclesTitle = vehiclesTitle;
    }

    public String getVehiclesBrand() {
        return vehiclesBrand;
    }

    public void setVehiclesBrand(String vehiclesBrand) {
        this.vehiclesBrand = vehiclesBrand;
    }

    public String getVehicleOverview() {
        return vehicleOverview;
    }

    public void setVehicleOverview(String vehicleOverview) {
        this.vehicleOverview = vehicleOverview;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(String seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getVImage1() {
        return vImage1;
    }

    public void setVImage1(String vImage1) {
        this.vImage1 = vImage1;
    }

    public String getVImage2() {
        return vImage2;
    }

    public void setVImage2(String vImage2) {
        this.vImage2 = vImage2;
    }

    public String getVImage3() {
        return vImage3;
    }

    public void setVImage3(String vImage3) {
        this.vImage3 = vImage3;
    }

    public String getVImage4() {
        return vImage4;
    }

    public void setVImage4(String vImage4) {
        this.vImage4 = vImage4;
    }

    public String getVImage5() {
        return vImage5;
    }

    public void setVImage5(String vImage5) {
        this.vImage5 = vImage5;
    }

    public String getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(String airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getPowerDoorLock() {
        return powerDoorLock;
    }

    public void setPowerDoorLock(String powerDoorLock) {
        this.powerDoorLock = powerDoorLock;
    }

    public String getAntiLockBrakingSystem() {
        return antiLockBrakingSystem;
    }

    public void setAntiLockBrakingSystem(String antiLockBrakingSystem) {
        this.antiLockBrakingSystem = antiLockBrakingSystem;
    }

    public String getBrakeAssit() {
        return brakeAssit;
    }

    public void setBrakeAssit(String brakeAssit) {
        this.brakeAssit = brakeAssit;
    }

    public String getPowerSteering() {
        return powerSteering;
    }

    public void setPowerSteering(String powerSteering) {
        this.powerSteering = powerSteering;
    }

    public String getDriveAirBag() {
        return driveAirBag;
    }

    public void setDriveAirBag(String driveAirBag) {
        this.driveAirBag = driveAirBag;
    }

    public String getPassengerAirBag() {
        return passengerAirBag;
    }

    public void setPassengerAirBag(String passengerAirBag) {
        this.passengerAirBag = passengerAirBag;
    }

    public String getPowerWindows() {
        return powerWindows;
    }

    public void setPowerWindows(String powerWindows) {
        this.powerWindows = powerWindows;
    }

    public String getCDPlayer() {
        return cDPlayer;
    }

    public void setCDPlayer(String cDPlayer) {
        this.cDPlayer = cDPlayer;
    }

    public String getCentralLocking() {
        return centralLocking;
    }

    public void setCentralLocking(String centralLocking) {
        this.centralLocking = centralLocking;
    }

    public String getCrashSensor() {
        return crashSensor;
    }

    public void setCrashSensor(String crashSensor) {
        this.crashSensor = crashSensor;
    }

    public String getLeatherSeats() {
        return leatherSeats;
    }

    public void setLeatherSeats(String leatherSeats) {
        this.leatherSeats = leatherSeats;
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

}