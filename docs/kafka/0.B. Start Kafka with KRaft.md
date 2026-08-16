### **1️⃣ Validate `config\kraft\server.properties`**
1. Open the file located at:
   ```
   C:\kafka\config\kraft\server.properties
   ```
2. Modify it to match a **single-node KRaft setup**:
   ```
   process.roles=controller,broker
   node.id=1
   controller.quorum.voters=1@localhost:9093
   listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
   advertised.listeners=PLAINTEXT://localhost:9092
   log.dirs=C:/kafka/data/kraft-combined-logs
   controller.listener.names=CONTROLLER
   num.partitions=1
   default.replication.factor=1
   offsets.topic.replication.factor=1
   transaction.state.log.replication.factor=1
   transaction.state.log.min.isr=1
   min.insync.replicas=1
   ```

---

### **2️⃣ Format the Storage Directory**
Before starting Kafka in KRaft mode, we need to **format the storage directory**.

1. Open **Command Prompt** as **Administrator**.
2. Navigate to Kafka folder:
   ```sh
   cd C:\kafka
   ```
3. Format storage (use an auto-generated UUID):
   ```sh
   bin\windows\kafka-storage.bat format -t $(powershell -Command "[guid]::NewGuid()") -c config\kraft\server.properties
   ```
    - If `$(powershell -Command "[guid]::NewGuid()")` doesn't work, manually generate a UUID:
      ```powershell
      powershell -Command "[guid]::NewGuid()"
      ```
      Then run:
      ```sh
      bin\windows\kafka-storage.bat format -t <YOUR_UUID> -c config\kraft\server.properties
      ```

---

### **3️⃣ Start Kafka in KRaft Mode**
Now, start Kafka **without Zookeeper**:

```sh
bin\windows\kafka-server-start.bat config\kraft\server.properties
```

---

### **4️⃣ Verify Kafka Setup**
✅ **Check if Kafka is running**:  
Run this in a new terminal:
```sh
bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
```
If the Kafka service is up, it won't throw any connection errors.

✅ **Create a topic**:
```sh
bin\windows\kafka-topics.bat --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

✅ **Test producer**:
```sh
bin\windows\kafka-console-producer.bat --topic my-topic --bootstrap-server localhost:9092
```
Type some messages and press **Enter**.

✅ **Test consumer**:
```sh
bin\windows\kafka-console-consumer.bat --topic my-topic --from-beginning --bootstrap-server localhost:9092
```
- `--property print.key=true` : This option ensures that the key of the messages is printed in addition to the value.
```sh
bin\windows\kafka-console-consumer.bat --topic product-created-events-topic --bootstrap-server localhost:9092 --property print.key=true
```

## 🎯 **Summary**
- ✅ **Modify `config\kraft\server.properties` instead.**
- ✅ **Format the storage directory before running Kafka.**
- ✅ **Start Kafka with `kafka-server-start.bat config\kraft\server.properties`.**