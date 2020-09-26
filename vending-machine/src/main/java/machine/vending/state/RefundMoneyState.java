package machine.vending.state;

import machine.vending.IVendingMachine;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class RefundMoneyState implements IVendingState{

    private IVendingMachine vendingMachine;
    public RefundMoneyState(IVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(Item item) throws Exception {
        throw new Exception("Cannot insert new money before refund");
    }

    @Override
    public boolean selectOrder(Item item) throws Exception {
        throw new Exception("Cannot select new order before collecting refund");
    }

    @Override
    public int refundMoney(Item item) throws Exception {
        int refund=vendingMachine.getChangeHandler().refundMoney(item.getOrderValue());
        vendingMachine.setIVendingState(VendingStateEnum.MACHINE_READY);
        return refund;
    }

    @Override
    public Order ejectOrder(Item item) throws Exception {
        throw new Exception("Opted for refund cannot eject order");
    }

    @Override
    public int returnChange(Item item) throws Exception {
        throw new Exception("Opted for refund cannot change for order");
    }

    @Override
    public boolean refill(ConcurrentHashMap<Order, Integer> refillMap) throws Exception {
        throw new Exception("You cannot refill machine at this state");
    }
}
