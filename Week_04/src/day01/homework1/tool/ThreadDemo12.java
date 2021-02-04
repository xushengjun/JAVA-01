package day01.homework1.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * countDownLatch的使用
 */
public class ThreadDemo12 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        MethodClass12 methodClass = new MethodClass12();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
                countDownLatch.countDown();
            }
        });

        t.start();
        countDownLatch.await();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass12{
    int result;
    public void sum() {
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
