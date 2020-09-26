package machine.vending.order;

import machine.vending.state.Order;

import java.util.Objects;

public class Chocolate implements Order {
    private String brand;
    private String type;
    private int price;

    public Chocolate(String brand, String type, int price) {
        this.brand = brand;
        this.type = type;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chocolate chocolate = (Chocolate) o;
        return getPrice() == chocolate.getPrice() &&
                getBrand().equals(chocolate.getBrand()) &&
                getType().equals(chocolate.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getType(), getPrice());
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand=brand;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type=type;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price=price;
    }
}
