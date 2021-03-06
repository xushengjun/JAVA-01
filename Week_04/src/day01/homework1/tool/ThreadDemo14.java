package day01.homework1.tool;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

/**
 * CyclicBarrier的使用
 */
public class ThreadDemo14 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        MethodClass14 methodClass = new MethodClass14();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    methodClass.sum();
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        cyclicBarrier.await();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass14{
    int result;
    public void sum()  {
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
