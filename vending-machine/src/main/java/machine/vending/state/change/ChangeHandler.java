package machine.vending.state.change;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ChangeHandler {
    public AtomicInteger totalMoney;

    public int getTotalMoney() {
        return totalMoney.get();
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = new AtomicInteger(totalMoney);
    }
    public boolean insertMoney(int money){
         totalMoney.addAndGet(money);
        return true;
    }

    /**
     * Do not know if the impl is correct
     *
     * @param orderValue
     * @return
     */
    public int getChange(int orderValue) throws Exception {
        if(totalMoney.get()>orderValue)
        return totalMoney.getAndAdd(-orderValue);
        else
            throw new Exception("Change not possible at the time");
    }
    public int refundMoney(int orderValue){
        totalMoney.getAndAdd(-orderValue);
        return orderValue;
    }
}
