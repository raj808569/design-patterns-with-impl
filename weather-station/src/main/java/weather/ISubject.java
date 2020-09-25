package weather;

import java.util.List;

/**
 * The implementation should maintain the list
 * of observers
 */
public interface ISubject {
    public void register(IObserver observer);
    public void deRegister(IObserver observer) throws Exception;
    public void notifyObserver(ISubject observable);
    public void stateChanged();
}
