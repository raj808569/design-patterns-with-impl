package machine.vending;

import machine.vending.state.IVendingState;
import machine.vending.state.Order;
import machine.vending.state.VendingStateEnum;
import machine.vending.state.change.ChangeHandler;
import machine.vending.state.inventory.IInventoryService;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public interface IVendingMachine {
    public boolean selectOrder(Item item);
    public boolean payForOrder(Item item);
    public boolean returnChange(Item item);
    public boolean  refundMoney(Item item);
    boolean collectOrder(Item item);
    public boolean refillInBulK(ConcurrentHashMap<Order,Integer>refillTab);
    public IInventoryService getInventoryService();
    public ChangeHandler getChangeHandler();
    public void setIVendingState(VendingStateEnum state) throws Exception;
    public IVendingState getState();
}
