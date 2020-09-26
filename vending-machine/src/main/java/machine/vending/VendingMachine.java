package machine.vending;

import machine.vending.state.*;
import machine.vending.state.change.ChangeHandler;
import machine.vending.state.inventory.IInventoryService;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class VendingMachine implements IVendingMachine{
    private IInventoryService inventoryService;
    private ChangeHandler changeHandler;
    private IVendingState machineState;
    private IVendingState machineEmptyState;
    private IVendingState machineReadyState;
    private IVendingState insertMoneyState;
    private IVendingState changeMoneyState;
    private IVendingState ejectOrderState;
    private IVendingState refundMoneyState;
    public VendingMachine(IInventoryService inventoryService, ChangeHandler changeHandler,ConcurrentHashMap<Order,Integer> refillMap) throws Exception {
        this.inventoryService = inventoryService;
        this.changeHandler = changeHandler;
        initializeState(this);
        this.setIVendingState(VendingStateEnum.MACHINE_EMPTY);
        if(refillMap!=null&&!refillMap.isEmpty()){
            inventoryService.refillInBulk(refillMap);
            this.setIVendingState(VendingStateEnum.MACHINE_READY);
        }
    }

    private void initializeState(VendingMachine vendingMachine) {
        machineEmptyState=new MachineEmptyState(vendingMachine);
        machineReadyState=new MachineReadyState(vendingMachine);
        insertMoneyState=new InsertMoneyState(vendingMachine);
        changeMoneyState=new ReturnChangeState(vendingMachine);
        ejectOrderState=new EjectOrderState(vendingMachine);
        refundMoneyState=new RefundMoneyState(vendingMachine);
        machineState=machineEmptyState;
    }

    @Override
    public boolean selectOrder(Item item) {
        try {
            machineState.selectOrder(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean payForOrder(Item item) {
        try{
            machineState.insertMoney(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean returnChange(Item item) {
        try {
            machineState.returnChange(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean refundMoney(Item item) {
        try {
            machineState.refundMoney(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean collectOrder(Item item){
        try {
            machineState.ejectOrder(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean refillInBulK(ConcurrentHashMap<Order, Integer> refillTab) {
        try {
            machineState.refill(refillTab);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public IInventoryService getInventoryService() {
        return inventoryService;
    }

    public void setInventoryService(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @Override
    public ChangeHandler getChangeHandler() {
        return changeHandler;
    }

    @Override
    public void setIVendingState(VendingStateEnum state) throws Exception {
        IVendingState stateToSet=null;
        switch (state){
            case MACHINE_EMPTY:stateToSet=machineEmptyState;
                    break;
            case EJECT_ORDER:stateToSet=ejectOrderState;
                break;
            case MACHINE_READY: stateToSet=machineReadyState;
            break;
            case INSERT_MONEY:stateToSet=insertMoneyState;
                break;
            case REFUND_MONEY:stateToSet=refundMoneyState;
                break;
            case RETURN_CHANGE:stateToSet=changeMoneyState;
                break;
            default:throw new Exception("InvalidState");

        }
        ;
        this.machineState=stateToSet;
    }

    @Override
    public IVendingState getState() {
        return machineState;
    }

    public void setChangeHandler(ChangeHandler changeHandler) {
        this.changeHandler = changeHandler;
    }

}

