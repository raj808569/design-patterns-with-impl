package machine.vending.state;

import machine.vending.IVendingMachine;
import machine.vending.state.IVendingState;
import machine.vending.state.Order;
import machine.vending.state.VendingStateEnum;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class EjectOrderState implements IVendingState {

    private IVendingMachine vendingMachine;

    public EjectOrderState(IVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney(Item item) throws Exception {
        throw new Exception("First collect previously payed order");
    }

    @Override
    public boolean selectOrder(Item item) throws Exception {
        throw new Exception("First collect previously payed order");
    }

    @Override
    public int refundMoney(Item item) throws Exception {
       throw  new Exception("change has been deducted money cant be refunded");
    }

    @Override
    public Order ejectOrder(Item item) throws Exception {
        try{
           Order ejected= vendingMachine.getInventoryService().getOrder(item.getOrder());
            if(vendingMachine.getInventoryService().orderExists(item.getOrder())){
                vendingMachine.setIVendingState(VendingStateEnum.MACHINE_READY);
            }else {
                vendingMachine.setIVendingState(VendingStateEnum.MACHINE_EMPTY);
            }
            return ejected;
        }catch (Exception e){
            vendingMachine.setIVendingState(VendingStateEnum.MACHINE_EMPTY);
            //this should never happen
            throw e;
        }
    }

    @Override
    public int returnChange(Item item) throws Exception {
        throw new Exception("Already changed the money");
    }

    @Override
    public boolean refill(ConcurrentHashMap<Order, Integer> refillMap) throws Exception {
        throw new Exception("You cannot refill machine at this state");
    }
}
