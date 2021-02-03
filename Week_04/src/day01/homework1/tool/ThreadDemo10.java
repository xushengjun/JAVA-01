package day01.homework1.tool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

/**
 * Semaphore的使用
 */
public class ThreadDemo10 {

    public static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        MethodClass10 methodClass = new MethodClass10();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(1);
                    methodClass.sum();
                    semaphore.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        semaphore.acquire(1);
        t.start();
        semaphore.release(1);
        semaphore.acquire(2);
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
        semaphore.release(2);
    }
}

class MethodClass10{
    int result;
    public void sum() throws InterruptedException {
        result = fibo(36);
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public  int getResult() {
        return result;
    }

}
