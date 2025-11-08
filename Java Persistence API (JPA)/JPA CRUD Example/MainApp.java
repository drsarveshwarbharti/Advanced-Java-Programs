//2. Main Application – MainApp.java
package com.example.jpa; 
import javax.persistence.*;
import java.util.List;
public class MainApp {
    public static void main(String[] args) {
      
        // Step 1: Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("student_pu");
      
        // Step 2: Create EntityManager
        EntityManager em = emf.createEntityManager();
      
        try {
            // CREATE (CRUD OPERATIONS)
            // Step 3: Begin transaction
            em.getTransaction().begin(); 
            
            // Perform database operations (CRUD)
            Student s1 = new Student("Ravi Sharma", "ravi@example.com");
            Student s2 = new Student("Anita Verma", "anita@example.com");
            
            // Save entity to database
            em.persist(s1);
            em.persist(s2);
           
            // Step 4: Commit transaction
            em.getTransaction().commit();
          
            System.out.println("Students added successfully.\n");

            // Step 5: Close resources (Done after all CRUD Operations are executed)
            //em.close();
            //emf.close();

            // READ (JPQL)
            System.out.println("--- Student Records ---");
            List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            for (Student s : students) {
                System.out.println(s);
            }
            
            // UPDATE
            em.getTransaction().begin();
            Student studentToUpdate = em.find(Student.class, 1);
            if (studentToUpdate != null) {
                studentToUpdate.setEmail("ravi_updated@example.com");
                em.merge(studentToUpdate);
                System.out.println("\nUpdated: " + studentToUpdate);
            }
            em.getTransaction().commit();
            
            // DELETE
            em.getTransaction().begin();
            Student studentToDelete = em.find(Student.class, 2);
            if (studentToDelete != null) {
                em.remove(studentToDelete);
                System.out.println("\nDeleted: " + studentToDelete);
            }
            em.getTransaction().commit();
            
            // READ AGAIN
            System.out.println("\n--- Students after Update & Delete ---");
            students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
            for (Student s : students) {
                System.out.println(s);
            }
        } finally {
        em.close();
        emf.close();
        }
      }
  }
