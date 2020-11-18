## 单例的各种实现

  为了便于说明，模拟一个计数器场景的实现，当然这个🌰并没有什么实际价值，这里仅仅是为了说明各种单例实现的优缺点。
```java
public interface Counter {
    int incr();
}
```

### 饿汉式
  在类加载的期间，就将 instance 静态实例初始化好了，所以，instance 实例的创建是线程安全的。不过，这样的实现方式不支持延迟加载实例。
```java
public class HungryImpl implements Counter {
    private AtomicInteger counter = new AtomicInteger(0);

    private static HungryImpl INSTANCE = new HungryImpl();

    private HungryImpl() {
    }

    public static HungryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public int incr() {
        return counter.incrementAndGet();
    }
}
```

### 懒汉式
  懒汉式相对于饿汉式的优势是支持延迟加载；这种实现方式会导致频繁加锁、释放锁，以及并发度低等问题，频繁的调用会产生性能瓶颈。
```java
public class LazyImpl implements Counter{
    private AtomicInteger counter = new AtomicInteger(0);

    private static LazyImpl INSTANCE;

    private LazyImpl() {
    }

    public static synchronized LazyImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyImpl();
        }
        return INSTANCE;
    }

    @Override
    public int incr() {
        return counter.incrementAndGet();
    }
}
```
### 双重检查锁
  既支持延迟加载、又支持高并发的单例实现方式。只要 instance 被创建之后，再调用getInstance() 函数都不会进入到加锁逻辑中。所以，这种实现方式解决了懒汉式并发度低的问题。
```java
public class DoubleDetectImpl implements Counter {
    private AtomicInteger counter = new AtomicInteger(0);

    // 禁止指令重排序
    private volatile static DoubleDetectImpl INSTANCE;

    private DoubleDetectImpl() {
    }
    
    public static DoubleDetectImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleDetectImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleDetectImpl();
                }
            }
        }
        return INSTANCE;
    }
    
    @Override
    public int incr() {
        return counter.incrementAndGet();
    }
}
```

### 静态内部类
  INSTANCE 的唯一性、创建过程的线程安全性，都由 JVM 来保证既支持延迟加载，也支持高并发，实现起来相比双重检查锁简单。
```java
public class StaticInnerClassImpl implements Counter {
    private AtomicInteger counter = new AtomicInteger(0);

    private StaticInnerClassImpl() {
    }

    @Override
    public int incr() {
        return counter.incrementAndGet();
    }

    public static class SingletonHolder {
        private static final StaticInnerClassImpl INSTANCE = new StaticInnerClassImpl();
    }

    public static StaticInnerClassImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

### 利用枚举（推荐）
  通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
```java
public enum EnumerateImpl implements Counter {
    INSTANCE;

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public int incr() {
        return counter.incrementAndGet();
    }
}
```

### 单例存在哪些问题
  1. 对面向对象的特性支持不友好
  2. 隐藏了类之间的依赖关系
  3. 对代码的扩展性不友好
  4. 单例对代码的可测试性不友好
  5. 不支持带参数的构造函数