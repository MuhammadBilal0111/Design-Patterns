interface Vehicle { }

class Car implements Vehicle {
    String brand;
    String model;
    String color;
    int topSpeed;

    public Car(String brand, String model, String color, int topSpeed) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.topSpeed = topSpeed;
    }
}

class Bus implements Vehicle {
    int capacity;

    public Bus(int capacity) {
        this.capacity = capacity;
    }
}
class Client {

    public static Vehicle copyObj(Vehicle vehicle) {

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;

            Car copy = new Car(
                car.brand,
                car.model,
                car.color,
                car.topSpeed
            );

            return copy;

        } else if (vehicle instanceof Bus) {
            Bus bus = (Bus) vehicle;

            Bus copy = new Bus(bus.capacity);

            return copy;
        }

        return null;
    }
}