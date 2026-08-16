# **Apache Kafka for Microservices**

## **1. Introduction to Apache Kafka**
- **Definition:** Apache Kafka is a distributed event streaming platform used for collecting, processing, storing, and integrating data at scale.
- **Developer's Perspective:**
    - Runs as a **cluster** across multiple servers and data centers.
    - Facilitates **event-driven applications** that continuously exchange data.
    - Supports **scalability**:
        - **Scale down** with fewer instances for small user traffic.
        - **Scale up** with more instances for large user traffic.
    - Ensures **efficient message exchange** between microservices without performance bottlenecks.

---

## **2. Kafka in Microservices Architecture**
### **2.1. Microservices Communication with Kafka**
- Kafka helps microservices communicate asynchronously via events.
- Example scenario:
    - **Producer Microservice**: The **Products Microservice** publishes an event when a new product is created.
    - **Consumer Microservices**: Multiple services subscribe to this event to receive the update.
    - As per the diagram, microservices such as **SMS Notification, Push Notification, and Email Notification** act as consumers.

**📌 Refer Image Section:** Diagram illustrating Kafka in Microservices Architecture.
![KafkaInMicroservice Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/KafkaInMicroservice1.JPG?raw=true)

### **2.2. Core Components of Kafka in Microservices**
1. **Producer:**
    - Microservice that publishes events to Kafka.
    - Uses **Producer API** to send events to Kafka.

2. **Consumer:**
    - Microservice that consumes events from Kafka.
    - Uses **Consumer API** to read events.
    - Consumers are **dynamic**:
        - Can **scale up/down** as load changes.
        - **IP addresses and port numbers change dynamically**, making them **location-transparent** to the producer.

3. **Broker:**
    - Kafka **server** that accepts events from producers and stores them.
    - Ensures **fault tolerance** by running multiple brokers in a cluster:
        - If one broker fails, others continue serving requests.

4. **Topic:**
    - Logical storage unit inside Kafka where events are stored.
    - **Durable storage:** Events are persisted on disk and replicated across Kafka cluster nodes for reliability.
    - Works similarly to a **message queue**, but events are **not deleted** immediately after consumption.

5. **Partition:**
    - A **topic is divided into multiple partitions**.
    - Events are **distributed across partitions** for parallel processing.
    - **Parallelism & scalability:**
        - Consumers can **read partitions in parallel**, making event processing much faster.
        - More consumer instances can be started to scale up processing.

6. **Replication:**
    - Kafka ensures **high availability** through **in-sync replicas**.
    - If one broker fails, data is retrieved from replicas in other brokers, ensuring reliability.

---

## **3. How Kafka Works in Microservices**
1. **Producer publishes an event**
    - Example: **Products Microservice** creates a `ProductCreated` event in JSON format.
    - Uses **Producer API** to send the event to a Kafka **broker**.

2. **Broker receives and stores the event**
    - Kafka broker **persists events on disk**.
    - Events are **replicated across multiple brokers** for fault tolerance.

3. **Consumers read events from the Kafka topic**
    - Multiple microservices consume the event using **Consumer API**.
    - Each consumer reads from **its own partition** in parallel, improving efficiency.
    - As per the diagram, the **SMS, Push Notification, and Email Notification microservices** act as consumers, subscribing to the topic.

**📌 Refer Image Section:** Diagram illustrating Kafka Producer-Broker-Consumer flow with partitions and replication.
![KafkaInMicroservice Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/KafkaInMicroservice2.JPG?raw=true)
4. **Event Retention in Kafka**
    - Events **remain in the topic** even after being read (unlike traditional message queues).
    - Allows multiple consumers to read the same event at different times.

---

## **4. Advantages of Kafka in Microservices**
- **Decoupling of microservices**:
    - Producers & consumers operate independently without direct knowledge of each other.
- **Scalability**:
    - Supports high-throughput event processing with parallel consumers.
- **Fault tolerance**:
    - Event replication across multiple brokers ensures reliability.
- **Durability**:
    - Events persist in Kafka topics even after consumption.
- **Performance optimization**:
    - Partitioning allows faster, parallel event processing.

---

## **5. Summary**
- **Kafka enables asynchronous, event-driven microservices communication.**
- **Producers publish events → Kafka brokers store them → Consumers read events asynchronously.**
- **Kafka topics store events in partitions for scalability and parallel processing.**
- **Kafka ensures fault tolerance and durability through replication across multiple brokers.**
- **Real-world example:** A **Products Microservice** produces an event, which is stored in Kafka brokers and consumed by the **SMS Notification, Push Notification, and Email Notification Microservices**.

**📌 Refer Image Section:** Diagram illustrating end-to-end Kafka workflow in microservices architecture.
![KafkaInMicroservice Diagram](https://github.com/javaByAbhishek/abhishek-tech-notes/blob/main/src/main/resources/assets/KafkaInMicroservice.JPG?raw=true)

