package day01.homework1.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享变量，lock作为锁，，
 * 子线程计算和主线程获取结果，争夺同一把锁
 * 若子线程先获取到锁，则进行计算，计算后再释放锁，主线程获取到锁，然后拿到计算结果
 * 主线程在30ms后开始不断去试着拿锁，并且每个之后每个30ms拿一下锁，拿到了就是子线程执行完成了
 */
public class ThreadDemo3 {
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        MethodClass3 methodClass = new MethodClass3();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
            }
        });
        t.start();

        int result = methodClass.getResult();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass3{
    volatile int result;
    private Lock lock = new ReentrantLock();
    public  void sum() {
        lock.lock();
        try {
            result = fibo(36);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    public  int getResult() throws InterruptedException {
        while (true){
            if (lock.tryLock(30, TimeUnit.MILLISECONDS)) return result;
        }
    }
}
