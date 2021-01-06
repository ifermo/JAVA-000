## 配置 redis 的主从复制，sentinel 高可用，Cluster 集群

#### 1. 主从复制

下面以配置1主2从的Redis 主从为例
关键配置信息如下

```sh
# 主机 redis.conf:
	port 6379
  bind 127.0.0.1
  
# 从机1 redis.conf：
  port 6380
  bind 127.0.0.1
  slaveof 127.0.0.1 6379 // 设置master服务器为6379

# 从机2 redis.conf：
  port 6381
  bind 127.0.0.1
  slaveof 127.0.0.1 6379 // 设置master服务器为6379
```
查看结果
```bash
127.0.0.1:6379> info replication
# Replication
role:master
connected_slaves:0
master_replid:c4a8773ffeacf3f44291ee14e3eb94d4ed5c2dcb
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0

127.0.0.1:6379> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6379
master_link_status:up
master_last_io_seconds_ago:2
master_sync_in_progress:0
slave_repl_offset:70
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:b7d438ecc727b9186991517a44807db03aa28934
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:70
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:71
repl_backlog_histlen:0

127.0.0.1:6379> info replication
# Replication
role:slave
master_host:127.0.0.1
master_port:6379
master_link_status:up
master_last_io_seconds_ago:2
master_sync_in_progress:0
slave_repl_offset:0
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:b7d438ecc727b9186991517a44807db03aa28934
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:0
```



#### 2. sentinel 高可用

  `sentinel.conf`是启动redis-sentinel的核心配置文件，从官网下载：
```shell
wget http://download.redis.io/redis-stable/sentinel.conf
```
根据上面下载好的sentinel.conf，找到并修改如下配置：

```sh
# <master-name>:自定义集群名，<master-redis-ip>:主库ip <master-redis-port>:主库port <quorum>:最小投票数
# sentinel monitor <master-name> <master-redis-ip> <master-redis-port> <quorum>
sentinel monitor mycache 127.0.0.1 6379 2

# 配置集群实例密码
# 
# sentinel auth-pass mycache 123456

daemonize yes
```

sentinel集群关键配置信息：
```sh
# sentinel0 sentinel0.conf:
port 26379
sentinel monitor mycache 127.0.0.1 6379 2
sentinel down-after-milliseconds mycache 10000
sentinel failover-timeout mycache 15000

# sentinel1 sentinel1.conf:
port 26380
sentinel monitor mycache 127.0.0.1 6379 2
sentinel down-after-milliseconds mycache 10000
sentinel failover-timeout mycache 15000

# sentinel2 sentinel2.conf:
port 26381
sentinel monitor mycache 127.0.0.1 6379 2
sentinel down-after-milliseconds mycache 10000
sentinel failover-timeout mycache 15000
```

启动redis-sentinel：
```sh
redis-sentinel sentinel0.conf
redis-sentinel sentinel1.conf
redis-sentinel sentinel2.conf
```

使用redis-sentinel API查看监控状况（查看redis-sentinel0）
```sh
                _._                                                  
           _.-``__ ''-._                                             
      _.-``    `.  `_.  ''-._           Redis 6.0.9 (00000000/0) 64 bit
  .-`` .-```.  ```\/    _.,_ ''-._                                   
 (    '      ,       .-`  | `,    )     Running in sentinel mode
 |`-._`-...-` __...-.``-._|'` _.-'|     Port: 26379
 |    `-._   `._    /     _.-'    |     PID: 14
  `-._    `-._  `-./  _.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |           http://redis.io        
  `-._    `-._`-.__.-'_.-'    _.-'                                   
 |`-._`-._    `-.__.-'    _.-'_.-'|                                  
 |    `-._`-._        _.-'_.-'    |                                  
  `-._    `-._`-.__.-'_.-'    _.-'                                   
      `-._    `-.__.-'    _.-'                                       
          `-._        _.-'                                           
              `-.__.-'                                               

14:X 06 Jan 2021 12:57:38.081 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
14:X 06 Jan 2021 12:57:38.087 # Could not rename tmp config file (Device or resource busy)
14:X 06 Jan 2021 12:57:38.087 # WARNING: Sentinel was not able to save the new configuration on disk!!!: Device or resource busy
14:X 06 Jan 2021 12:57:38.087 # Sentinel ID is 9c15cb3c616b9d06b57f872508373ce8fe9de411
14:X 06 Jan 2021 12:57:38.087 # +monitor master mymaster 127.0.0.1 6379 quorum 2
14:X 06 Jan 2021 12:57:38.087 # +monitor master mycache 192.168.0.197 6379 quorum 2
14:X 06 Jan 2021 12:57:38.092 * +slave slave 172.17.0.1:6379 172.17.0.1 6379 @ mycache 192.168.0.197 6379
14:X 06 Jan 2021 12:57:38.095 # Could not rename tmp config file (Device or resource busy)
14:X 06 Jan 2021 12:57:38.095 # WARNING: Sentinel was not able to save the new configuration on disk!!!: Device or resource busy
14:X 06 Jan 2021 12:57:48.222 * +convert-to-slave slave 172.17.0.1:6379 172.17.0.1 6379 @ mycache 192.168.0.197 6379
```

#### 3. Cluster 集群

1. 创建redis配置文件（redis-cluster.tmpl）
我在路径/home下创建一个文件夹redis-cluster,在路径/home/redis-cluster下创建一个文件redis-cluster.tmpl，并把以下内容复制过去
```sh
port ${PORT}                                       ##节点端口
protected-mode no                                  ##开启集群模式
cluster-enabled yes                                ##cluster集群模式
cluster-config-file nodes.conf                     ##集群配置名
cluster-node-timeout 5000                          ##超时时间
cluster-announce-ip 192.168.XX.XX                  ##实际为各节点网卡分配ip  先用上网关ip代替
cluster-announce-port ${PORT}                      ##节点映射端口
cluster-announce-bus-port 1${PORT}                 ##节点总线端口
appendonly yes                                     ##持久化模式
```
2. 在/home/redis-cluster下生成conf和data目标，并生成配置信息
```bash
 $ for port in `seq 7000 7005`; do \
  mkdir -p ./${port}/conf \
  && PORT=${port} envsubst < ./redis-cluster.tmpl > ./${port}/conf/redis.conf \
  && mkdir -p ./${port}/data; \
  done
```
3. 创建3个redis容器
```bash
$ for port in `seq 6379 6382`; do \
docker run -d -ti -p ${port}:${port} -p 1${port}:1${port} \
-v /home/redis-cluster/${port}/conf/redis.conf:/usr/local/etc/redis/redis.conf \
-v /home/redis-cluster/${port}/data:/data \
--restart always --name redis-${port} --net redis-net \
--sysctl net.core.somaxconn=1024 redis redis-server /usr/local/etc/redis/redis.conf; \
done
```
4. 通过命令docker ps可查看刚刚生成的6个容器信息
```bash
[root@node-01 redis-cluster]# docker ps
CONTAINER ID        IMAGE                                         COMMAND                  CREATED             STATUS                PORTS                                                              NAMES
15c4790c2c35        redis                                         "docker-entrypoint..."   10 seconds ago      Up 9 seconds          0.0.0.0:6379->6379/tcp, 6379/tcp, 0.0.0.0:6379->16379/tcp         redis-6379
45bec3374b87        redis                                         "docker-entrypoint..."   11 seconds ago      Up 10 seconds         0.0.0.0:6380->6380/tcp, 6379/tcp, 0.0.0.0:6380->16380/tcp         redis-6380
45482dbb89e5        redis                                         "docker-entrypoint..."   11 seconds ago      Up 10 seconds         0.0.0.0:6381->6381/tcp, 6379/tcp, 0.0.0.0:6381->16381/tcp         redis-6381
```

5. 创建集群

   进入一个节点：

   ```bash
   docker exec -it redis-6379 bash
   ```
   使用redis创建集群，redis-cli：
   ```bash
   redis-cli --cluster create 192.168.0.197:6379 192.168.0.197:6380 192.168.0.197:6381
   ```