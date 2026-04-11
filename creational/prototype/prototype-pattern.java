import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Vehicle{
  protected String brand;
  protected String model;
  protected String color;

  protected Vehicle(String brand, String model, String color) {
    this.brand = brand;
    this.model = model;
    this.color = color;
  }
  protected Vehicle(Vehicle vehicle) {
    this.brand = vehicle.brand;
    this.model = vehicle.model;
    this.color = vehicle.color;
  }

  public abstract Vehicle clone();
}
class Car extends Vehicle{
  private int topSpeed;
  private GpsSystem gpsSystem; // Shallow Copy of gpsSystem, both original Car and cloned Car will reference the same GpsSystem object

  public Car(String brand, String model, String color, int topSpeed, GpsSystem gpsSystem) {
    // When a subclass object is created, parent constructor runs first
    super(brand, model, color);
    this.topSpeed = topSpeed;
    this.gpsSystem = gpsSystem;
  }

  public Car(Car car){
    // When a subclass object is created, parent constructor runs first
    super(car); // Call the constructor of Vehicle to copy brand, model, color
    this.topSpeed = car.topSpeed;
    this.gpsSystem = car.gpsSystem;
  }

  @Override
  public Vehicle clone() {
    return new Car(this); 
          // this:
          // Refers to the current object
          // The object on which clone() is called
  }
}
class Bus extends Vehicle{
  private int capacity;
  private GpsSystem gpsSystem;

  public Bus(String brand, String model, String color, int capacity, GpsSystem gpsSystem) {
    // When a subclass object is created, parent constructor runs first
    super(brand, model, color);
    this.capacity = capacity;
    this.gpsSystem = gpsSystem;
  }

  public Bus(Bus bus){
    // When a subclass object is created, parent constructor runs first
    super(bus); // Call the constructor of Vehicle to copy brand, model, color
    // Control goes to Vehicle constructor
    this.capacity = bus.capacity;
    this.gpsSystem = new GpsSystem(bus.gpsSystem); // Deep Copy of gpsSystem, each Bus will have its own GpsSystem object
  }

  @Override
  public Vehicle clone() {
    return new Bus(this);
  }
}
class GpsSystem {
  private String gpsVersion;

  public GpsSystem(String gpsVersion) {
    this.gpsVersion = gpsVersion;
  }

  public GpsSystem(GpsSystem gpsSystem) {
    this.gpsVersion = gpsSystem.gpsVersion;
  }
}
// Implements as a factory class
class VehicleCache{
  private Map<String, Vehicle> cache = new HashMap<>();

  public VehicleCache(){
    Car car = new Car("Toyota", "Camry", "Blue", 120, new GpsSystem("v1.0"));
    Bus bus = new Bus("Mercedes", "Sprinter", "White", 30, new GpsSystem("v2.0"));

    cache.put("car1", car);
    cache.put("bus1", bus);
  }
  public Vehicle getVehicle(String key) {
    Vehicle vehicle = cache.get(key);
    return vehicle.clone(); // Prototype Pattern here
  }
}

class Client {
  public static List<Vehicle> cloneVehicle(List<Vehicle> vehicles) {
    List<Vehicle> copyList = new ArrayList<>();
    for (Vehicle vehicle : vehicles) {
      copyList.add(vehicle.clone()); // no dependency on concrete classes
    }
    return copyList;
  }
}
class Main {
  public static void main(String[] args) {
    VehicleCache vehicleCache = new VehicleCache();
    Vehicle car1 = vehicleCache.getVehicle("car1");
    Vehicle bus1 = vehicleCache.getVehicle("bus1");

    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(car1);
    vehicles.add(bus1);
    List<Vehicle> clonedVehicles = Client.cloneVehicle(vehicles);
  }
}