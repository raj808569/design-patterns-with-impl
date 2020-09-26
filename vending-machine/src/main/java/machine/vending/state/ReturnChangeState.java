package machine.vending.state;

import machine.vending.IVendingMachine;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class ReturnChangeState implements IVendingState {

    private IVendingMachine vendingMachine;

    public ReturnChangeState(IVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(Item item) throws Exception {
        throw new Exception("Collect change first");
    }

    @Override
    public boolean selectOrder(Item item) throws Exception {
        throw new Exception("Collect change first");
    }

    @Override
    public int refundMoney(Item item) throws Exception {
        int refund=vendingMachine.getChangeHandler().refundMoney(item.getOrderValue());
        vendingMachine.setIVendingState(VendingStateEnum.MACHINE_READY);
        return refund;
    }

    @Override
    public Order ejectOrder(Item item) throws Exception {
        throw new Exception("Already ejected");
    }

    @Override
    public int returnChange(Item item) throws Exception {
        int change= vendingMachine.getChangeHandler().getChange(item.getOrderValue());
        vendingMachine.setIVendingState(VendingStateEnum.EJECT_ORDER);
        return change;
    }

    @Override
    public boolean refill(ConcurrentHashMap<Order, Integer> refillMap) throws Exception {
        throw new Exception("You cannot refill machine at this state");
    }
}
