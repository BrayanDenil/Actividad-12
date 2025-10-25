package tienda.observer;


/**
 *
 * @author Usuario
 */

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();
}
