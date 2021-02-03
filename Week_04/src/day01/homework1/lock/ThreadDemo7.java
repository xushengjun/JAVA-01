package day01.homework1.lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock和Condition搭配使用
 */
public class ThreadDemo7 {
    public static final Lock lock = new ReentrantLock();
    public static final Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        MethodClass7 methodClass = new MethodClass7();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
            }
        });
        t.start();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass7{
    int result;
    public int sum() {
        ThreadDemo7.lock.lock();
        try {
            result = fibo(36);
            ThreadDemo7.condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ThreadDemo7.lock.unlock();
        }
        return result;
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public int getResult() throws InterruptedException {
        ThreadDemo7.lock.lock();
        if (result==0){
            ThreadDemo7.condition.await();
        }
        ThreadDemo7.lock.unlock();
        return result;
    }

}
