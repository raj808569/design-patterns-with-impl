package machine.vending.state;

import machine.vending.IVendingMachine;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class InsertMoneyState implements IVendingState{
    private IVendingMachine vendingMachine;

    public InsertMoneyState(IVendingMachine vendingMachine) {
        assert (vendingMachine.getInventoryService()!=null);
        assert (vendingMachine.getChangeHandler()!=null);
        this.vendingMachine=vendingMachine;
    }

    @Override
    public void insertMoney(Item item) throws Exception {
        boolean result=vendingMachine.getChangeHandler().insertMoney(item.getOrderValue()) ;
        if(result){
            vendingMachine.setIVendingState(VendingStateEnum.RETURN_CHANGE);
        }
    }

    @Override
    public boolean selectOrder(Item item) throws Exception {
        throw new Exception("Finish processing for the selected order first");
    }

    @Override
    public int refundMoney(Item item) throws Exception {
        throw new Exception("Insert Money first");
    }

    @Override
    public Order ejectOrder(Item item) throws Exception {
        throw new Exception("Insert Money first");
    }

    @Override
    public int returnChange(Item item) throws Exception {
        throw new Exception("Make  Payment first");
    }

    @Override
    public boolean refill(ConcurrentHashMap<Order, Integer> refillMap) throws Exception {
        throw new Exception("You cannot refill machine at this state");
    }
}
