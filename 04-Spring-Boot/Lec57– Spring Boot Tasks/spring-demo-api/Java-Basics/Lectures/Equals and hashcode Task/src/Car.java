import java.util.Objects;

public class Car {

    String plateNumber;
    String color;

    Car(String plateNumber, String color) {
        this.plateNumber = plateNumber;
        this.color = color;
    }

    // equality based on plate number
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Car))
            return false;

        Car c = (Car) obj;

        return this.plateNumber.equals(c.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }
}