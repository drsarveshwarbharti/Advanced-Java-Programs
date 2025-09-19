import java.util.*;

// ========== OBSERVER PATTERN ==========
/*
Observer Pattern
Defines a one-to-many relationship between objects.
When one object changes state, all dependents are notified.
Example: Used in event handling systems (e.g., GUI, message queues).
*/

interface Observer {
    void update(String message);
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    void addObserver(Observer o) { observers.add(o); }
    void notifyObservers(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}

class User implements Observer {
    private String name;
    User(String name) { this.name = name; }
    public void update(String msg) {
        System.out.println("[Observer] " + name + " received: " + msg);
    }
}

//Driver Class
public class ObserverPattern {
    public static void main(String[] args) {
        
        // Observer
        Subject subject = new Subject();
        User u1 = new User("Alice");
        User u2 = new User("Bob");
        subject.addObserver(u1);
        subject.addObserver(u2);
        subject.notifyObservers("New message available!");
    }
}

/*
Output (Sample):

[Observer] Alice received: New message available!
[Observer] Bob received: New message available!

*/
