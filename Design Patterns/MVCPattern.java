import java.util.*;

// ========== MVC PATTERN ==========
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

//Driver Class
public class MVCPattern {
    public static void main(String[] args) {
        
        // MVC
        StudentModel model = new StudentModel("Simran");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
    }
}

/*
Output (Sample):
[MVC] Student Name: Simran
*/
