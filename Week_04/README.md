### 学习笔记

### 作业说明

（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？写出你的方法，越多越好，提交到 Github。

- 共包含15种方式实现

  1. `VolatileAndCycleImpl.java` ：完全无锁化，通过Volatile保证可见性，再循环等待的方式实现
  2. `ObjectWaitNotifyImpl.java` ：Object.wait()、Object.notify()/Object.notifyAll() 配合synchronized实现
  3. `ThreadJoinImpl.java` ：通过Thread.join方法实现
  4. `SemaphoreImpl.java` ：通过Semaphore实现
  5. `CyclicBarrierImpl.java` ：通过CyclicBarrier实现
  6. `CountDownLatchImpl.java` ：通过CountDownLatch实现
  7. `ExchangerImpl.java` ：通过Exchanger实现，较为特殊
  8. `ForkJoinPoolImpl.java`：使用ForkJoinPool框架实现
  9. `BlockingQueueImpl.java`：利用各种阻塞队列
  10. `LockSupportImpl.java`：利用LockSupport工具类
  11. `FutureImpl.java`：线程池submit方法配合Future实现
  12. `CompletableFutureImpl.java`：CompletableFuture的实现
  13. `CompletionServiceImpl.java`：CompletionService的实现
  14. `ConditionImpl.java`：Lock、Condition的方式实现
  15. `ThreadSleepImpl.java`：通过粗暴的Thread.sleep()实现，前提是子线程能在预期时间内执行完

思维导图见 ‘Java并发编程.png’

![Java并发编程]('https://github.com/ifermo/JAVA-000/blob/main/Week_04/Java并发编程.png')