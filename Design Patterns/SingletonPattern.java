
import java.util.*;

// ========== SINGLETON PATTERN ==========
/*
Singleton Pattern
Ensures only one instance of a class exists.
Useful for logging, configuration, database connections.
*/

class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
    public void showMessage() {
        System.out.println("[Singleton] Only one instance exists!");
    }
}

//Driver Class
public class SingletonPattern {
    public static void main(String[] args) {
        
        // 1. Singleton
        Singleton single = Singleton.getInstance();
        single.showMessage();
        
    }
}

/*
Output (Sample):
[Singleton] Only one instance exists!
*/
