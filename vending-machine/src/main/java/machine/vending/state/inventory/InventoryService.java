package machine.vending.state.inventory;

import machine.vending.state.Order;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Major enhancement to track here is that there is no upper limit
 * for this inventory although there should be
 */
public class InventoryService implements IInventoryService{
    private ConcurrentHashMap<Order,Integer> quantities;

    public InventoryService(ConcurrentHashMap<Order, Integer> quantities) {
        this.quantities = quantities;
    }

    @Override
    public int getRemaining(Order order) {
        return quantities.getOrDefault(order,0);
    }

    @Override
    public boolean orderExists(Order order) {
        return quantities.getOrDefault(order,0)!=0;
    }

    @Override
    public boolean refillOrder(Order order, int quantity) {
         quantities.put(order,quantities.getOrDefault(order,0)+quantity);
         return true;
    }

    @Override
    public boolean refillInBulk(ConcurrentHashMap<Order, Integer> refill) {
        quantities.putAll(refill);
        return true;
    }

    @Override
    public Order getOrder(Order order) throws Exception {
        if(quantities.getOrDefault(order,0)!=0){
            quantities.put(order,quantities.get(order)-1);
            return order;

        }
        else
            throw new Exception( "Order Unavailable");
    }
}
