## 在main线程中新建线程并获取返回值的方式：

1. 传统Thread
```java
// 1, 传统Thread
Thread t = new Thread(() -> {
    TestMain.message = "message1";
});
t.start();
try {
    t.join();
} catch (InterruptedException e) {
    e.printStackTrace();
}
System.out.println(message);
```

2. 使用自定义的Thread
```java
// 2. Thread-自定义
MyThread myThread = new MyThread();
myThread.start();
myThread.join();
System.out.println(myThread.returnMessage);
```

3. 使用线程池的execute+static参数的方式
```java
// 3. ExecutorService-Runnable
ExecutorService executorService = Executors.newFixedThreadPool(3);
executorService.execute(() -> {
    TestMain.message = "message2";
    synchronized (flag) {
        flag.notifyAll();
    }
});
synchronized (flag) {
    flag.wait(100);
}
System.out.println(message);
```
4. 使用ExecutorService线程池，提交自定义Thread
```java
// 4. ExecutorService-MyThread
executorService.execute(myThread);
// myThread的wait
System.out.println(message);
```

5. 使用自定义Runnable接口实现类，通过实现类里面的属性传递信息
```java
// 5. runnable--自定义runnable 通过参数获取结果
MyTask1 myTask1 = new MyTask1("taskName1");
Thread td = new Thread(myTask1);
td.join();
System.out.println(myTask1.returnMessage);
```

6. 使用Callable接口，通过Future的get获取返回值
```java
// 6. Callable-submit-get
Future<String> submit = executorService.submit(() -> "message3");
System.out.println(submit.get());
```

7. 自定义Callable实现类，通过Future获取返回值。以及第8种方式是通过自定义Callable的属性来获取返回值。
```
// 7. Callable-自定义Callable，通过future获取结果
MyTask2 callable2 = new MyTask2();
Future<String> submit2 = executorService.submit(callable2);
System.out.println(submit2.get());
// 8. Callable-自定义Callable，通过Field获得参数
System.out.println(callable2.returnMessage);
```