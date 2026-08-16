To install Apache Kafka on Windows 11, follow these steps:

### **Step 1: Install Java (JDK)**
Kafka requires Java, so first, install the **Java Development Kit (JDK)** (at least Java 8).

1. Download the latest JDK from the official Oracle or OpenJDK website:
  - [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
  - [OpenJDK](https://jdk.java.net/)
2. Install the JDK and set the **JAVA_HOME** environment variable:
  - Open **System Properties** → **Advanced** → **Environment Variables**.
  - Under **System Variables**, click **New**, and set:
    - **Variable name**: `JAVA_HOME`
    - **Variable value**: Path to your JDK installation (e.g., `C:\Program Files\Java\jdk-XX.X.X`)
  - Add `JAVA_HOME\bin` to the **Path** variable.

To verify, open **Command Prompt** and type:
```sh
java -version
```

### **Step 2: Download Apache Kafka**
1. Go to the official [Kafka download page](https://kafka.apache.org/downloads).
2. Select the latest **binary download** (Scala version doesn't matter).
3. Extract the downloaded **ZIP file** to a directory (e.g., `C:\kafka`).

### **Step 3: Configure Kafka**
Kafka needs **Zookeeper** to manage brokers. It comes bundled with Kafka.

1. Open the extracted Kafka folder (`C:\kafka`).
2. Edit the **config files**:
  - Open `config\zookeeper.properties`, find the line:
    ```
    dataDir=/tmp/zookeeper
    ```
    and change it to:
    ```
    dataDir=C:/kafka/data/zookeeper
    ```
  - Open `config\server.properties`, find the line:
    ```
    log.dirs=/tmp/kafka-logs
    ```
    and change it to:
    ```
    log.dirs=C:/kafka/data/kafka-logs
    ```

### **Step 4: Start Zookeeper and Kafka**
1. Open **Command Prompt** in the Kafka directory (`C:\kafka`).
2. Start Zookeeper:
   ```sh
   bin\windows\zookeeper-server-start.bat config\zookeeper.properties
   ```
   Keep this window open.

3. Open a new **Command Prompt** in the same directory and start Kafka:
   ```sh
   bin\windows\kafka-server-start.bat config\server.properties
   ```

### **Step 5: Test Kafka Installation**
To create and test a topic:

1. Open a new Command Prompt and create a topic:
   ```sh
   bin\windows\kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
   ```
2. List available topics:
   ```sh
   bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
   ```
3. Start a producer:
   ```sh
   bin\windows\kafka-console-producer.bat --topic test-topic --bootstrap-server localhost:9092
   ```
   Type messages and press **Enter**.

4. Open a new Command Prompt and start a consumer:
   ```sh
   bin\windows\kafka-console-consumer.bat --topic test-topic --from-beginning --bootstrap-server localhost:9092
   ```
   You should see the messages sent by the producer.
