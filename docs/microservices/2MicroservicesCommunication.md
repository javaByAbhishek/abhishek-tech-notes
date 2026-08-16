# **Microservices Communication**

## **1. Overview of Microservices Architecture**
![Microservices Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/MS.JPG?raw=true)

- Microservices architecture consists of multiple independent services working together as a large system.
- Each microservice is a standalone web service running on a unique port.
- Clients (e.g., mobile apps, web apps) communicate with microservices using **HTTP requests**.
---

## **2. Key Components of a Microservices System**
### **2.1 Client Applications**
- Mobile applications or web applications interact with microservices via REST APIs.
- They send **HTTP requests** and receive **HTTP responses**.

### **2.2 Spring Cloud Environment**
- Provides infrastructure support for building microservices.
- **Key components:** _(Refer to the first section of the image)_
   - **API Gateway:** Acts as an entry point for all requests.
   - **Config Server:** Centralized configuration management.
   - **Service Discovery (Eureka):** Helps microservices discover each other dynamically.

### **2.3 Business Microservices**
- **Users Microservice:** Handles user-related data.
- **Products Microservice:** Manages product details.
- **Orders Microservice:** Manages order processing.
- **Payment Microservice:** Handles payment transactions.

---

## **3. Communication Between Microservices**
### **3.1 Direct Communication (HTTP)**
- Microservices communicate by making **HTTP requests**.
- A microservice cannot directly query another microservice's database.

**Example:**
- **Orders Microservice** does not access the **Payments Database** directly.
- Instead, it sends an **HTTP request** to the **Payments Microservice**, which processes the request and sends a response.

_(Refer to the first section of the image to see how HTTP requests and responses work in microservices.)_

### **3.2 Synchronous vs Asynchronous Communication**
#### **3.2.1 Synchronous Communication**
- One microservice sends a request and **waits** for a response.
- Used when an immediate response is required.
  ![Microservices Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/MS2.JPG?raw=true)

- **Example:** _(Refer to the second section of the image)_
   - Albums Microservice sends an **HTTP GET request** to Photos Microservice.
   - It waits until it receives a **JSON array of photos** or a timeout occurs.

#### **3.2.2 Asynchronous Communication**
- A microservice sends a request but **does not wait** for an immediate response.
- Used when waiting is unnecessary or a delayed response is acceptable.

**Example:** _(Refer to the second section of the image)_
- Orders Microservice sends an **HTTP POST request** to Email Notification Microservice.
- Orders Microservice does **not wait** for the response and continues executing other tasks.
- The response may come **later**, and the microservice must be prepared to handle it asynchronously.

---

## **4. Challenges of Direct HTTP Communication**
1. **Service Availability Issue**
   - If Email Notification Microservice is **temporarily down**, it will **miss** HTTP requests.
   - If it becomes available after hours, it will have lost critical notifications.

2. **Scalability Issue**
   - If a new **Shipping Microservice** is added, Orders Microservice needs to be **updated** to send HTTP requests to it.
   - Every time a new microservice is added, **code changes** are required.
     ![Microservices Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/MS3.JPG?raw=true)

_(Refer to the third section of the image, where Orders Microservice sends notifications to multiple services, but Email Notification Microservice fails.)_

---

## **5. Event-Driven Communication with Apache Kafka**
- **Solution to the challenges of direct HTTP communication.**
- Instead of direct HTTP requests, microservices **publish events** to a **message broker** (e.g., Apache Kafka).
- Other microservices **subscribe** to the event stream and receive notifications when an event occurs.

### **Benefits of Kafka-Based Communication**
1. **Reliability:** Messages are stored in Kafka and can be processed even if a microservice is temporarily unavailable.
2. **Scalability:** New microservices can subscribe to events without modifying existing microservices.
3. **Loose Coupling:** Microservices do not need to know about each other’s endpoints.

---

## **6. Summary**
| **Aspect** | **Direct HTTP (Synchronous/Asynchronous)** | **Event-Driven (Kafka)** |
|------------|---------------------------------|--------------------------|
| **Communication** | One-to-one | One-to-many |
| **Waiting for Response** | Yes (Synchronous), No (Asynchronous) | No |
| **Handling Failures** | If a service is down, requests are lost | Messages are stored and processed later |
| **Scalability** | New services require code updates | New services subscribe without changes to existing code |

**Conclusion:**
- **Direct HTTP** is useful for simple, immediate interactions.
- **Event-driven communication (Kafka)** is better for scalable and fault-tolerant systems.

---
