package machine.vending.state;

import machine.vending.IVendingMachine;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class MachineEmptyState implements IVendingState{
    private IVendingMachine  vendingMachine;

    public MachineEmptyState(IVendingMachine vendingMachine) {
        assert (vendingMachine.getInventoryService()!=null);
        assert (vendingMachine.getChangeHandler()!=null);
        this.vendingMachine=vendingMachine;
    }

    @Override
    public void insertMoney(Item item) throws Exception {
        throw new Exception("Item not available");
    }

    @Override
    public boolean selectOrder(Item item) throws Exception {
        throw new Exception("You cant select item as we have none");
    }

    @Override
    public int refundMoney(Item item) throws Exception {
        return vendingMachine.getChangeHandler().refundMoney(item.getOrderValue());
    }

    @Override
    public Order ejectOrder(Item item) throws Exception {
        throw new Exception("Order cant be ejected as machine doesnt have the order");
    }

    @Override
    public int returnChange(Item item) throws Exception {
        throw new Exception("No order hence no change");
    }

    @Override
    public boolean refill(ConcurrentHashMap<Order,Integer> refillMap) throws Exception {

        boolean result= vendingMachine.getInventoryService().refillInBulk(refillMap);
        if(result){
            vendingMachine.setIVendingState(VendingStateEnum.MACHINE_READY);
        }
        return result;
    }

}
