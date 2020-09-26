package machine.vending.state;

import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

/**
 * This interface is used to encapsulate different states a vending machine can be in
 * The operations that can be performed on vending machine
 * insertMoney
 */
public interface IVendingState {
    public void insertMoney(Item item) throws Exception;
    public boolean selectOrder(Item item)throws Exception;
    public int refundMoney(Item item) throws Exception;
    public Order ejectOrder(Item item) throws Exception;
    public int returnChange(Item item) throws Exception;
    public boolean refill(ConcurrentHashMap<Order,Integer> refillMap) throws Exception;

}

