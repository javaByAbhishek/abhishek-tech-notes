### **Functional Interfaces**

A **Functional Interface** in Java is an interface that has **only one abstract method**. These interfaces can have multiple **default** or **static** methods, but they must have exactly one abstract method. Functional interfaces are used primarily to enable **lambda expressions** and **method references**.

#### **Key Characteristics of Functional Interfaces:**
1. **Single Abstract Method**: A functional interface must have only one abstract method. It can have multiple methods from the `Object` class (like `equals()`, `hashCode()`, etc.) but only one abstract method.
2. **Can Have Default and Static Methods**: A functional interface can have multiple default or static methods.

#### **Example of a Functional Interface:**

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    void myMethod(); // Single abstract method
    
    // Can have default methods
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }
    
    // Can have static methods
    static void staticMethod() {
        System.out.println("This is a static method.");
    }
}
```
#### **Lambda Expression Example:**

You can implement a functional interface using a **lambda expression**.

```java
public class Test {
    public static void main(String[] args) {
        MyFunctionalInterface myFunc = () -> System.out.println("Lambda Expression Implementation");
        myFunc.myMethod();  // Calls the implemented method via lambda
    }
}
```

In this case, the `myMethod()` is implemented using a lambda expression.

---

### **Default Methods in Functional Interfaces**

A **default method** is a method in an interface that has a body. Default methods were introduced in **Java 8** to allow developers to add new methods to interfaces without breaking the implementing classes. They are commonly used to provide default behavior, so implementing classes don’t need to override them unless they require a different implementation.

#### **Key Points about Default Methods:**
- A default method can have a body.
- A class implementing the interface is not required to implement the default method unless it needs a specific implementation.
- Default methods can be overridden in the implementing class if needed.

#### **Example of Default Method:**

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    void myMethod(); // Abstract method
    
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }
}
```

You can call the default method using the interface, like this:

```java
public class Test {
    public static void main(String[] args) {
        MyFunctionalInterface myFunc = () -> System.out.println("Implementing the abstract method");
        myFunc.myMethod();  // Calls the abstract method implementation
        myFunc.defaultMethod();  // Calls the default method
    }
}
```

In this case, if you don’t override the `defaultMethod()`, it will be executed as defined in the interface.

---

### **Static Methods in Functional Interfaces**

A **static method** in an interface is like a static method in a class — it belongs to the interface and not to the instances of the implementing classes. Static methods are called using the interface name itself, not through an instance.

#### **Key Points about Static Methods:**
- Static methods cannot be overridden by implementing classes.
- Static methods can only be called on the interface itself (not on the instance).

#### **Example of Static Method:**

```java
@FunctionalInterface
public interface MyFunctionalInterface {
    void myMethod(); // Abstract method
    
    // Static method
    static void staticMethod() {
        System.out.println("This is a static method.");
    }
}
```

You can call the static method like this:

```java
public class Test {
    public static void main(String[] args) {
        // Calling static method using the interface name
        MyFunctionalInterface.staticMethod();  // Outputs: This is a static method.
    }
}
```

---

### **Why Use Functional Interfaces?**

Functional interfaces are the foundation for using **lambda expressions** in Java. The primary goal of functional interfaces is to provide a clear target type for lambda expressions or method references, which can be passed around as arguments or returned from methods. They enable more concise, readable, and functional-style programming.

#### **Common Built-in Functional Interfaces in Java 8:**
- `Runnable`: Represents a task with no arguments and no return value.
- `Callable`: Represents a task that can return a result.
- `Predicate<T>`: Represents a boolean-valued function of one argument.
- `Function<T, R>`: Represents a function that accepts one argument and returns a result.
- `Consumer<T>`: Represents an operation that accepts a single input argument and returns no result.
- `Supplier<T>`: Represents a supplier of results.

---

In summary, functional interfaces allow for cleaner and more flexible code in Java, especially with the introduction of lambda expressions. Default and static methods within functional interfaces provide additional flexibility and allow you to evolve interfaces without breaking existing implementations.