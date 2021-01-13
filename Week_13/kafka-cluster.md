fka集群

1. 部署一个3节点的`kafka`集群，需要准备好3个配置文件`kafka900x.properties`，分别修改对应的位置。

   ```sh
   broker.id=1 #三个配置文件分别修改为1,2,3
   log.dirs=/tmp/kafka-logs1 #三个配置文件分别修改为logs1,logs2,logs3
   listeners=PLAINTEXT://localhost:9092 #三个配置文件分别修改为9092,19092,29092
   ```

2. 清理zk的数据，可以使用工具`ZooInspector`

   ```sh
   D:\zk\ZooInspector\build>java -jar zookeeper-dev-ZooInspector.jar
   ```

3. 启动三个`kafka`

   ```sh
   bin/kafka-server-start.sh kafka9092.properties
   bin/kafka-server-start.sh kafka19092.properties
   bin/kafka-server-start.sh kafka29092.properties
   ```

   

4. 集群测试

   ```sh
   # 创建带有3个分区2连个副本的topic
   bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test-demo --partitions 3 --replication-factor 2
   # 生产者
   bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic test-demo
   #消费者
   bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-demo --from-beginning
   ```

5. 性能测试

   ```sh
   #producer
   bin/kafka-producer-perf-test.sh --topic test-demo --num-records 1000000 --record-size 10000 --throughput 200000 --producer-props bootstrap.servers=localhost:9092
   #consumer
   bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9092 --topic test-demo --fetch-size 1048576 --messages 10000 --threads 1
   ```
