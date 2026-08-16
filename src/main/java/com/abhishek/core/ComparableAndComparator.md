### **Comparator vs Comparable Interface in Java**


### **1. `Comparable` Interface**

The **`Comparable`** interface is used when you want to define the natural ordering of objects of a class. It is typically implemented by the class whose instances need to be compared and sorted. This interface has a **single method**: `compareTo()`, which is used to compare the current object (`this`) with another object.

#### **Usage**:
The `Comparable` interface is used when there is a **natural ordering** of objects, and you want to allow them to be sorted (e.g., in a `TreeSet`, `TreeMap`, or via `Collections.sort()`).

#### **Example**:
```java
import java.util.*;

class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Overriding compareTo() method to compare by age
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.age, other.age); // Comparing by age
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20)
        );

        Collections.sort(students); // Sort using natural order (age)
        System.out.println(students);
    }
}
```

**Output:**
```
[Charlie (20), Alice (22), Bob (25)]
```

In this example, the `Student` class implements `Comparable<Student>` to compare students based on their age.

---

### **2. `Comparator` Interface**
The `Comparator` interface is used when you need multiple ways of comparing objects or want to define a custom sorting order (e.g., sorting by name, age, etc.). It can be used with collections like `TreeSet`, `TreeMap`, or `Collections.sort()`.

#### **Example**:
```java
import java.util.*;

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20)
        );

        // Sort by name using Comparator
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
        students.sort(nameComparator);

        System.out.println("Sorted by Name: " + students);

        // Sort by age using Comparator
        Comparator<Student> ageComparator = Comparator.comparingInt(Student::getAge);
        students.sort(ageComparator);

        System.out.println("Sorted by Age: " + students);
    }
}
```

**Output:**
```
Sorted by Name: [Alice (22), Bob (25), Charlie (20)]
Sorted by Age: [Charlie (20), Alice (22), Bob (25)]
```

In this example, we define custom comparators to sort students by `name` and by `age`. You can use different `Comparator` implementations to sort in different ways.

---

### **Comparing `Comparable` and `Comparator`**

| Feature               | `Comparable`                             | `Comparator`                                      |
|-----------------------|------------------------------------------|--------------------------------------------------|
| **Interface Method**   | `compareTo(T o)`                         | `compare(T o1, T o2)`                            |
| **Implemented by**     | The class whose objects need to be compared | A separate class or anonymous class (or lambda)  |
| **Use Case**           | For **natural ordering** of objects      | For **custom ordering** (multiple ways to compare)|
| **Where Used**         | In data structures that require natural ordering (`TreeSet`, `TreeMap`) | In sorting utilities or when using `Collections.sort()`, `TreeSet`, etc. for custom order |
| **When to Use**        | When you want to define a **default sorting order** for the class | When you need **multiple sorting orders** or don’t want to modify the class |
| **Flexibility**        | Less flexible, as you can only define one sorting order | More flexible, can define multiple sorting orders |

---


### **Example with Lambda Expressions:**

You can simplify `Comparator` with lambda expressions in Java 8:

```java
import java.util.*;

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 22),
            new Student("Bob", 25),
            new Student("Charlie", 20)
        );

        // Sort by age using Comparator with Lambda
        students.sort((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge()));
        System.out.println("Sorted by Age: " + students);

        // Sort by name using Comparator with Lambda
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Sorted by Name: " + students);
    }
}
```

**Output:**
```
Sorted by Age: [Charlie (20), Alice (22), Bob (25)]
Sorted by Name: [Alice (22), Bob (25), Charlie (20)]
```
