package machine.vending.machine;

import machine.vending.IVendingMachine;
import machine.vending.VendingMachine;
import machine.vending.order.Chocolate;
import machine.vending.state.Order;
import machine.vending.state.change.ChangeHandler;
import machine.vending.state.inventory.IInventoryService;
import machine.vending.state.inventory.InventoryService;
import machine.vending.state.inventory.Item;

import java.util.concurrent.ConcurrentHashMap;

public class Application {
    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<Order,Integer> map=new ConcurrentHashMap<>();
        IInventoryService inventoryService=new InventoryService(new ConcurrentHashMap<>());
        ChangeHandler changeHandler=new ChangeHandler();
        changeHandler.setTotalMoney(100);
        setRefillMap(map);
        String brand="brand";
        String type="type";
        int price=12;
        Chocolate chocolate=new Chocolate(brand,type,price);
        map.put(chocolate,10);
        IVendingMachine vendingMachine=new VendingMachine(inventoryService,changeHandler,map);
        Item item=new Item() {
            @Override
            public Order getOrder() {
                return chocolate;
            }

            @Override
            public int getOrderValue() {
                return chocolate.getPrice();
            }
        };
        if(vendingMachine.selectOrder(item)){
            if(vendingMachine.payForOrder(item)){
                if(vendingMachine.returnChange(item)){
                    if(vendingMachine.collectOrder(item)){
                        System.out.println("Happy case passed");
                    }
                }
            }
        }
        if(vendingMachine.selectOrder(item)){
            if(vendingMachine.payForOrder(item)){
                if(vendingMachine.refundMoney(item)){

                        System.out.println("Happy case passed");

                }
            }
        }


    }

    private static void setRefillMap(ConcurrentHashMap<Order, Integer> map) {

    }
}
