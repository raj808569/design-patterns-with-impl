package machine.vending.state.inventory;

import machine.vending.state.Order;

public interface Item {
    public Order getOrder();
    public int getOrderValue();

}
