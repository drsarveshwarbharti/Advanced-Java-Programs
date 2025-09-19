import java.util.*;

// ========== BUILDER PATTERN ==========
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

//Driver Class
public class BuilderPattern {
    public static void main(String[] args) {
        
        // Builder
        UserProfile profile = new UserProfile.UserBuilder()
                                .setName("John")
                                .setAge(25)
                                .setEmail("john@example.com")
                                .build();
        System.out.println(profile);
      
    }
}

/*
Output (Sample):
[Builder] User: John, Age: 25, Email: john@example.com
*/
