
### **The `equals()` and `hashCode()` Contract in Java**

The **`equals()`** and **`hashCode()`** methods are essential for object comparison and proper functioning in hash-based collections like `HashMap`, `HashSet`, `Hashtable`, and others. The Java **Object class** provides default implementations for these methods, but **to ensure correct behavior** in many cases, you must **override both methods** in your custom classes.

#### **1. `equals()` Contract:**

- **Reflexive:** An object must equal itself.  
  `x.equals(x)` should always return `true`.

- **Symmetric:** If `x.equals(y)` is `true`, then `y.equals(x)` must also be `true`.

- **Transitive:** If `x.equals(y)` is `true` and `y.equals(z)` is `true`, then `x.equals(z)` must be `true`.

- **Consistent:** If `x.equals(y)` is `true` or `false` for some time, it should consistently return the same result, as long as the objects are not modified.

- **Null Comparison:** `x.equals(null)` must return `false`, i.e., an object is not equal to `null`.

#### **2. `hashCode()` Contract:**

- **Consistent:** If two objects are equal (as determined by `equals()`), they must return the same hash code.  
  `x.equals(y)` should imply `x.hashCode() == y.hashCode()`.

- **Non-equality does not imply non-equal hash codes:** If `x.equals(y)` is `false`, it’s not required that `x.hashCode()` and `y.hashCode()` are different, but it’s **desirable** for performance reasons to minimize hash collisions.

- **Consistency:** If the fields that determine equality (`equals()`) do not change during the object’s lifetime, the hash code should remain constant.

#### **3. `equals()` and `hashCode()` Relationship:**

- **Critical relationship:** If you override `equals()`, **you must also override `hashCode()`**. Failing to do so can lead to **undefined behavior** when the object is used in hash-based collections like `HashMap` or `HashSet`.

- **When `equals()` returns `true`, `hashCode()` must be the same:**  
  If `x.equals(y)` is `true`, then `x.hashCode()` should equal `y.hashCode()`.

- **When `equals()` returns `false`, hash codes don’t have to be different** (though it’s often desirable to improve collection performance).

#### **4. Practical Example:**

For a `Money` class:

```java
import java.util.Objects;

public class Money {
    private double amount;
    private String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Money))
      return false;
    Money money = (Money)o;
    boolean currencyEquals = (this.currency == null && money.currency == null)
            || (this.currency != null && this.currency.equals(money.currency));
    return this.amount == money.amount && currencyEquals;
  }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
```

In this example:
- **`equals()`** compares the `amount` and `currency` of two `Money` objects.
- **`hashCode()`** generates a hash code based on the same fields to ensure consistency between `equals()` and `hashCode()`.

#### **5. Why It's Important:**

- **Correct behavior in collections:** Hash-based collections, such as `HashMap` or `HashSet`, rely on both `equals()` and `hashCode()` for efficient operations (like storing and retrieving objects). If you fail to override both methods correctly, you may experience **unexpected behavior**, such as objects being incorrectly placed in collections or failing equality checks.

- **Performance:** A good `hashCode()` implementation minimizes hash collisions, which improves performance when working with hash-based collections.

---

### **Key Takeaways:**

- Always override **both** `equals()` and `hashCode()` if your class is to be used in hash-based collections or if you need to compare objects based on their **content** rather than their **memory reference**.
- The contract requires that **equal objects must have equal hash codes**.
- **Consistency** is crucial: if an object’s state doesn’t change, its hash code should not change.
- Failing to follow the contract leads to **subtle bugs** and **performance issues**, especially when objects are used in collections like `HashMap`, `HashSet`, etc.

---
The default implementation of `equals()` in the `Object` class compares the **identity** of the objects, which means it checks whether the two objects are the **same instance** (i.e., they have the same memory address).

When you override `equals()` in your class (such as `Money`), you are specifying your own logic for comparing the **contents** of the objects, rather than relying on identity.

Let's break it down again with regard to the `currency` field:

1. **When `currency` is a `String`that overrides `equals()`**:
  - The `currency.equals(money.currency)` method will use the `String` class's `equals()` method.
  - The `String` class overrides `equals()` to compare the actual contents of the objects. So, for example, two different `String` objects with the same content will be considered equal.
  
2. **When `currency` is some other object (not a primitive like `String`) and you don't override `equals()`**:
  - If the `currency` field is an object of a custom class that **does not override `equals()`**, then `currency.equals(money.currency)` will indeed compare the **identity** of the objects (using `==`), because the default `equals()` implementation in the `Object` class compares references (memory addresses).
  - If you don't override `equals()` in the custom class of `currency`, two distinct objects with the same content will be considered different because the `equals()` method checks if they are the same object in memory, rather than comparing their fields.

### Example with `String`:
If `currency` is a `String`, this is what happens:

```java
public class Money {
    private double amount;
    private String currency; // String is immutable and overrides equals()

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Money))
            return false;
        
        Money money = (Money) o;
        boolean currencyEquals = (this.currency == null && money.currency == null)
                || (this.currency != null && this.currency.equals(money.currency)); // Uses String's equals()
        return this.amount == money.amount && currencyEquals;
    }
}
```

In this case, `String`'s `equals()` method is used to compare the actual **content** of the strings (e.g., `"USD"` vs `"USD"`).

### Example with a custom class:

If `currency` is a custom class and you don't override `equals()` in that class, you’ll end up with the default reference comparison behavior:

```java
public class Money {
    private double amount;
    private Currency currency; // Custom class without overridden equals()

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Money))
            return false;
        
        Money money = (Money) o;
        boolean currencyEquals = (this.currency == null && money.currency == null)
                || (this.currency != null && this.currency.equals(money.currency)); // Uses Object's equals() by default
        return this.amount == money.amount && currencyEquals;
    }
}
```

In this case, if the `Currency` class **does not override `equals()`**, the comparison `this.currency.equals(money.currency)` would be comparing **object identity**, not the actual content of the `Currency` objects, unless `currency` is pointing to the same object.

### Key Takeaways:
- If the `currency` field is a class like `String`, which overrides `equals()`, `currency.equals(money.currency)` will **compare the contents** of the strings.
- If the `currency` field is a custom object **without an overridden `equals()`**, the default `equals()` method (which compares object identity) will be used, and two distinct `currency` objects with the same content will be considered **not equal**.
- For best practice, always override `equals()` (and `hashCode()`) in any custom classes that are being compared. This way, you control how comparisons are made and ensure the correct behavior, especially when those objects are used in collections like `HashSet` or `HashMap`.