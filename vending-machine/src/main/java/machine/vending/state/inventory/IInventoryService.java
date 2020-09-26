package machine.vending.state.inventory;

import machine.vending.state.Order;

import java.util.concurrent.ConcurrentHashMap;

public interface IInventoryService {
    public int getRemaining(Order order);
    public boolean orderExists(Order order);
    public boolean refillOrder(Order order, int quantity);
    public boolean refillInBulk(ConcurrentHashMap<Order,Integer> refill);

    public Order getOrder(Order order) throws Exception;
}
