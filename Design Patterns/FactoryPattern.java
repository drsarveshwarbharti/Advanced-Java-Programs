
import java.util.*;

// ========== FACTORY PATTERN ==========
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

//Driver Class
public class FactoryPattern {
    public static void main(String[] args) {
      
        // Factory
        ShapeFactory factory = new ShapeFactory();
        Shape circle = factory.getShape("circle");
        circle.draw();
        
    }
}

/*
Output (Sample):
[Factory] Drawing Circle
*/
