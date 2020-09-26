package machine.vending.state;

public interface Order {


    public String getBrand();

    public void setBrand(String brand);

    public String getType();

    public void setType(String type) ;

    public int getPrice();

    public void setPrice(int price);
}
