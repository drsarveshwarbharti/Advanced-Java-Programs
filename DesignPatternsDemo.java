// Main Demo Program for Common Design Patterns in Java
//Java program that demonstrates multiple design patterns (Singleton, Factory, Observer, Builder, DAO, MVC) in one place

//What are Design Patterns? Design patterns are proven solutions to common software design problems.
//They provide a template for writing reusable, maintainable, and flexible code

/* 
Creational Patterns: Singleton, Factory, Builder
Behavioral Patterns: Observer
Enterprise Patterns: DAO, MVC
*/

import java.util.*;

// ========== 1. SINGLETON PATTERN ==========
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

// ========== 2. FACTORY PATTERN ==========
/*
Factory Pattern
Provides a way to create objects without exposing the creation logic.
Useful when the exact type of object is determined at runtime.
*/

interface Shape { void draw(); }

class Circle implements Shape {
    public void draw() { System.out.println("[Factory] Drawing Circle"); }
}

class Square implements Shape {
    public void draw() { System.out.println("[Factory] Drawing Square"); }
}

class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE")) return new Circle();
        if (type.equalsIgnoreCase("SQUARE")) return new Square();
        return null;
    }
}

// ========== 3. OBSERVER PATTERN ==========
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

// ========== 4. BUILDER PATTERN ==========
/*
Builder Pattern
Provides a way to construct complex objects step by step.
Useful when constructors have many parameters.
*/

class UserProfile {
    private String name;
    private int age;
    private String email;

    private UserProfile(UserBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public String toString() {
        return "[Builder] User: " + name + ", Age: " + age + ", Email: " + email;
    }

    static class UserBuilder {
        String name; int age; String email;
        UserBuilder setName(String n) { this.name = n; return this; }
        UserBuilder setAge(int a) { this.age = a; return this; }
        UserBuilder setEmail(String e) { this.email = e; return this; }
        UserProfile build() { return new UserProfile(this); }
    }
}

// ========== 5. DAO PATTERN ==========
/* 
DAO (Data Access Object) Pattern
Separates the persistence logic (database access) from business logic.
Makes the system easier to maintain and test.
*/
class Student {
    int id; String name;
    Student(int id, String name) { this.id = id; this.name = name; }
}

interface StudentDAO {
    void save(Student s);
    Student find(int id);
}

class StudentDAOImpl implements StudentDAO {
    private Map<Integer, Student> db = new HashMap<>();
    public void save(Student s) {
        db.put(s.id, s);
        System.out.println("[DAO] Saved Student: " + s.name);
    }
    public Student find(int id) {
        return db.getOrDefault(id, new Student(id, "Not Found"));
    }
}

// ========== 6. MVC PATTERN ==========
/*
MVC (Model-View-Controller) Pattern
Model → represents data & business logic
View → UI (what user sees)
Controller → handles user input and updates Model/View
*/

class StudentModel {
    private String name;
    StudentModel(String name) { this.name = name; }
    public String getName() { return name; }
}

class StudentView {
    public void printStudent(String name) {
        System.out.println("[MVC] Student Name: " + name);
    }
}

class StudentController {
    private StudentModel model;
    private StudentView view;
    StudentController(StudentModel m, StudentView v) {
        this.model = m; this.view = v;
    }
    void updateView() { view.printStudent(model.getName()); }
}

// ========== MAIN DEMO ==========
public class DesignPatternsDemo {
    public static void main(String[] args) {
        
        // 1. Singleton
        Singleton single = Singleton.getInstance();
        single.showMessage();
        
        // 2. Factory
        ShapeFactory factory = new ShapeFactory();
        Shape circle = factory.getShape("circle");
        circle.draw();
        
        // 3. Observer
        Subject subject = new Subject();
        User u1 = new User("Alice");
        User u2 = new User("Bob");
        subject.addObserver(u1);
        subject.addObserver(u2);
        subject.notifyObservers("New message available!");
        
        // 4. Builder
        UserProfile profile = new UserProfile.UserBuilder()
                                .setName("John")
                                .setAge(25)
                                .setEmail("john@example.com")
                                .build();
        System.out.println(profile);
        
        // 5. DAO
        StudentDAO dao = new StudentDAOImpl();
        dao.save(new Student(1, "Ravi"));
        System.out.println("[DAO] Fetched: " + dao.find(1).name);
        
        // 6. MVC
        StudentModel model = new StudentModel("Simran");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
    }
}

/*
Output (Sample):
[Singleton] Only one instance exists!
[Factory] Drawing Circle
[Observer] Alice received: New message available!
[Observer] Bob received: New message available!
[Builder] User: John, Age: 25, Email: john@example.com
[DAO] Saved Student: Ravi
[DAO] Fetched: Ravi
[MVC] Student Name: Simran
*/
