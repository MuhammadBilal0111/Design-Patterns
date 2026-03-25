class Car {
    private final int id;
    private final String brand;
    private final String model;
    private final String color;

    // package-private constructor
    Car(int id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public void show() {
        System.out.println("Car [id=" + id + ", brand=" + brand +
                ", model=" + model + ", color=" + color + "]");
    }
}

class CarBuilder {
    private int id;
    private String brand;
    private String model;
    private String color;

    public CarBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CarBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public Car build() {
        return new Car(this.id, this.brand, this.model, this.color);
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new CarBuilder()
                .setId(1)
                .setBrand("Toyota")
                .setModel("Camry")
                .setColor("Blue")
                .build();

        car.show();
    }
}