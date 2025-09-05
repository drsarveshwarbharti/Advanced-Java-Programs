// MainDemo.java
// Demonstration of multiple Design Patterns in Java
// Patterns: Singleton, Factory, Builder, Observer, DAO, MVC

import java.util.*;

// --------------------- Singleton Pattern ---------------------
class Logger {
    private static Logger instance;
    private Logger() {} // private constructor
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}

// --------------------- Factory Pattern ---------------------
interface Shape {
    void draw();
}
class Circle implements Shape {
    public void draw() { System.out.println("Drawing Circle"); }
}
class Square implements Shape {
    public void draw() { System.out.println("Drawing Square"); }
}
class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) return new Circle();
        if (type.equalsIgnoreCase("square")) return new Square();
        return null;
    }
}

// --------------------- Builder Pattern ---------------------
class Computer {
    private String CPU, RAM, Storage, GPU;
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GPU = builder.GPU;
    }
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + Storage + ", GPU=" + GPU + "]";
    }
    static class Builder {
        String CPU, RAM, Storage, GPU;
        public Builder setCPU(String cpu) { this.CPU = cpu; return this; }
        public Builder setRAM(String ram) { this.RAM = ram; return this; }
        public Builder setStorage(String storage) { this.Storage = storage; return this; }
        public Builder setGPU(String gpu) { this.GPU = gpu; return this; }
        public Computer build() { return new Computer(this); }
    }
}

// --------------------- Observer Pattern ---------------------
interface Observer {
    void update(String news);
}
class Subscriber implements Observer {
    private String name;
    public Subscriber(String name) { this.name = name; }
    public void update(String news) {
        System.out.println(name + " received update: " + news);
    }
}
class NewsAgency {
    private List<Observer> subs = new ArrayList<>();
    public void addObserver(Observer o) { subs.add(o); }
    public void removeObserver(Observer o) { subs.remove(o); }
    public void notifyAllObservers(String news) {
        for (Observer o : subs) o.update(news);
    }
}

// --------------------- DAO Pattern ---------------------
class Student {
    int id; String name;
    Student(int id, String name) { this.id = id; this.name = name; }
    public String toString() { return id + " - " + name; }
}
interface StudentDAO {
    List<Student> getAllStudents();
    Student getStudent(int id);
    void updateStudent(Student s);
    void deleteStudent(int id);
}
class StudentDAOImpl implements StudentDAO {
    List<Student> students = new ArrayList<>();
    public StudentDAOImpl() {
        students.add(new Student(1,"Alice"));
        students.add(new Student(2,"Bob"));
    }
    public List<Student> getAllStudents() { return students; }
    public Student getStudent(int id) {
        return students.stream().filter(s->s.id==id).findFirst().orElse(null);
    }
    public void updateStudent(Student s) {
        for (Student st : students) {
            if (st.id == s.id) { st.name = s.name; }
        }
    }
    public void deleteStudent(int id) {
        students.removeIf(s -> s.id == id);
    }
}

// --------------------- MVC Pattern ---------------------
class StudentModel {
    String name; String rollNo;
    StudentModel(String name, String rollNo){ this.name=name; this.rollNo=rollNo; }
}
class StudentView {
    void printStudentDetails(StudentModel s) {
        System.out.println("Student: " + s.name + ", Roll No: " + s.rollNo);
    }
}
class StudentController {
    private StudentModel model;
    private StudentView view;
    StudentController(StudentModel m, StudentView v){ this.model=m; this.view=v; }
    void setName(String name){ model.name=name; }
    void updateView(){ view.printStudentDetails(model); }
}

// --------------------- Main Demo Program ---------------------
public class MainDemo {
    public static void main(String[] args) {
        Logger log = Logger.getInstance();
        log.log("Starting Design Pattern Demo...");

        // Singleton
        Logger log2 = Logger.getInstance();
        log.log("Logger instances are same: " + (log == log2));

        // Factory
        ShapeFactory factory = new ShapeFactory();
        Shape circle = factory.getShape("circle");
        circle.draw();

        // Builder
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9").setRAM("32GB").setStorage("1TB SSD").setGPU("RTX 4090").build();
        log.log(gamingPC.toString());

        // Observer
        NewsAgency agency = new NewsAgency();
        Observer s1 = new Subscriber("Alice");
        Observer s2 = new Subscriber("Bob");
        agency.addObserver(s1);
        agency.addObserver(s2);
        agency.notifyAllObservers("Breaking News: Design Patterns are fun!");

        // DAO
        StudentDAO dao = new StudentDAOImpl();
        log.log("Students in DB: " + dao.getAllStudents());
        dao.updateStudent(new Student(1,"Alicia"));
        log.log("After Update: " + dao.getAllStudents());

        // MVC
        StudentModel sm = new StudentModel("John","101");
        StudentView sv = new StudentView();
        StudentController sc = new StudentController(sm, sv);
        sc.updateView();
        sc.setName("Johnny");
        sc.updateView();

        log.log("Demo Completed.");
    }
}
