package tienda;
import java.util.*;
import tienda.observer.Observer;
import tienda.observer.Subject;
/**
 *
 * @author Usuario
 */
public class Inventory implements Subject {
    private Map<Integer,Integer> stock = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

    public void setStock(int productId, int qty) { stock.put(productId, qty); notifyObservers(); }
    public int getStock(int productId) { return stock.getOrDefault(productId, 0); }
    public void reduceStock(int productId, int qty) {
        int current = getStock(productId);
        stock.put(productId, Math.max(0, current - qty));
        notifyObservers();
    }
    public void increaseStock(int productId, int qty) {
        int current = getStock(productId);
        stock.put(productId, current + qty);
        notifyObservers();
    }

    public void register(Observer o) { observers.add(o); }
    public void unregister(Observer o) { observers.remove(o); }
    public void notifyObservers() { for (Observer o : observers) o.update(this); }
}
