package machine.vending.state;

import machine.vending.IVendingMachine;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class MachineReadyState implements IVendingState{

    private IVendingMachine vendingMachine;
    public MachineReadyState(IVendingMachine vendingMachine) {
        assert (vendingMachine.getInventoryService()!=null);
        assert (vendingMachine.getChangeHandler()!=null);
        this.vendingMachine=vendingMachine;
    }

    @Override
    public void insertMoney(Item item) throws Exception {
        throw new Exception("You cant insert money without selecting the item");
    }

    @Override
    public boolean selectOrder(Item item) throws Exception {
        boolean result=vendingMachine.getInventoryService().orderExists(item.getOrder());
        if(result){
            vendingMachine.setIVendingState(VendingStateEnum.INSERT_MONEY);
        }
        return result;
    }

    @Override
    public int refundMoney(Item item) throws Exception {
        throw new Exception("You havent inserted money why would we refund ");
    }

    @Override
    public Order ejectOrder(Item item) throws Exception {
        throw new Exception("Item has not been paid for");
    }

    @Override
    public int returnChange(Item item) throws Exception {
        throw new Exception("Item has not been paid for");
    }

    @Override
    public boolean refill(ConcurrentHashMap<Order, Integer> refillMap) throws Exception {
        throw new Exception("You cannot refill machine at this state");
    }
}
