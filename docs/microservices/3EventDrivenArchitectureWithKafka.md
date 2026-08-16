
# **Event-Driven Microservices and Apache Kafka**

## **1. Introduction to Microservice Communication**
- When two applications communicate, they often follow a request-response pattern.
- In a microservices architecture, one microservice sends an HTTP request to another and receives an HTTP response.
- This communication style is effective in many cases but has limitations when broadcasting messages to multiple services.

### **Illustration: Limitations of Direct Request-Response**
*(Refer to the first diagram in the image.)*
![KafkaInMicroservice Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/EventDrivenArchitechueWithKafka.JPG?raw=true)
- The **Products Microservice** directly calls the **Email Notification Microservice** using request-response.
- If the Email Notification Microservice is down, the message is lost.
- Other microservices (SMS and Push Notification) do not receive this event efficiently.
- This setup lacks scalability and reliability.

## **2. Challenges of Direct Request-Response Communication**
- If a microservice needs to communicate with multiple other microservices simultaneously, direct HTTP requests become inefficient.
- Issues arise when:
    - The number of receiving microservices increases.
    - New microservices need to be added dynamically.
    - The sender doesn’t know how many services will need the message.
- Direct HTTP request-response communication does not scale well in these scenarios.

## **3. Introduction to Event-Driven Architecture**
- **Solution:** Use Apache Kafka and an **event-driven** architecture.
- Instead of sending HTTP requests to multiple microservices, the sender publishes a message to an **Apache Kafka topic**.
- Microservices that are interested in the message subscribe to the topic and receive the message when it becomes available.

### **Illustration: Event-Driven Communication using Apache Kafka**
*(Refer to the second diagram in the image.)*
![KafkaInMicroservice Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/EventDrivenArchitechueWithKafka2.JPG?raw=true)
- The **Orders Microservice** (Producer) publishes an **"Order Created Event"** to **Apache Kafka**.
- Multiple consumer microservices (**SMS Notification, Push Notification, Email Notification, Supplier Notification, Shipment Notification, Call Center Notification**) subscribe to the Kafka topic.
- **Key Differences from Direct Communication:**
    - There is no direct dependency between producer and consumers.
    - Consumers process messages asynchronously.
    - If a consumer (e.g., **Email Notification Microservice**) is down, it can still process the message later when it comes back online.

### **Key Terminology:**
- **Producer & Consumer Model:**
    - The sender (microservice on the left) is the **Producer**.
    - The receivers (microservices on the right) are **Consumers**.
- **Publisher & Subscriber Model:**
    - The sender is the **Publisher**.
    - The receivers are **Subscribers** who listen for new messages.

## **4. How Event-Driven Architecture Works?**
- Example:
    - A **Products Microservice** receives a request to create a new product.
    - After creating the product, it publishes a **"Product Created" event** to Kafka.
    - All subscribed microservices (such as inventory, notifications, analytics) receive and process this event.
- This architecture is **scalable** and **loosely coupled** because:
    - The producer does not need to know about the consumers.
    - Consumers can dynamically subscribe/unsubscribe from the topic.

## **5. When to Use Event-Driven Architecture vs. Request-Response?**
- **Use Request-Response** when a direct and immediate response is required.
- **Use Event-Driven Communication** when:
    - Multiple services need the same message.
    - Services operate independently and asynchronously.
    - The system needs to be scalable and extensible.

## **6. Advantages of Event-Driven Architecture**
1. **Asynchronous Processing:**
    - The publisher does not wait for all consumers to process the message.
    - The response to the original request is immediate.
2. **Loosely Coupled Services:**
    - The publisher does not know how many consumers exist.
    - New consumers can be added anytime without modifying the publisher.
3. **Fault Tolerance & Reliability:**
    - If a consumer microservice is down, it will still receive messages when it comes back online.
    - Unlike request-response, where a failed destination service results in a lost message.
4. **Scalability:**
    - Allows handling a large number of microservices without affecting performance.

## **7. Key Takeaways**
- Event-driven architecture is a powerful alternative to direct request-response communication.
- Apache Kafka is a widely used tool for implementing this model.
- Asynchronous event-based messaging ensures scalability, flexibility, and resilience.
- However, event-driven communication should be applied **wisely**—use request-response where appropriate.

---

### **How to Use the Image in Study Material?**
- Place the first diagram under **Challenges of Direct Request-Response Communication** to highlight its limitations.
- Place the second diagram under **Introduction to Event-Driven Architecture** to show how Kafka solves these issues.
