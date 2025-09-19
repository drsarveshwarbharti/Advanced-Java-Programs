import java.util.*;

// ========== DAO PATTERN ==========
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

//DriverClass
public class DesignPatternsDemo {
    public static void main(String[] args) {
       
        // DAO
        StudentDAO dao = new StudentDAOImpl();
        dao.save(new Student(1, "Ravi"));
        System.out.println("[DAO] Fetched: " + dao.find(1).name);
        
    }
}

/*
Output (Sample):
[DAO] Saved Student: Ravi
[DAO] Fetched: Ravi
*/
