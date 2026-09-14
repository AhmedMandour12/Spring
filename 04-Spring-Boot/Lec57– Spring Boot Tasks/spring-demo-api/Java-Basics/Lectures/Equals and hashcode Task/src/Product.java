import java.util.Objects;

public class Product {

    String code;
    double price;

    Product(String code, double price) {
        this.code = code;
        this.price = price;
    }

    // equality based on code
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Product))
            return false;

        Product p = (Product) obj;

        return this.code.equals(p.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}