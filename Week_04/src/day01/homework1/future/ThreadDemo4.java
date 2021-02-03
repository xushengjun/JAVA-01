package day01.homework1.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用futureTask获取线程返回对象
 */
public class ThreadDemo4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start=System.currentTimeMillis();
        MethodClass4 methodClass = new MethodClass4();
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return methodClass.sum();
            }
        });
        Thread t = new Thread(futureTask);
        t.start();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+futureTask.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass4{
    volatile int result;
    public int sum() {
        return result = fibo(36);
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
