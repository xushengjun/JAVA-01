package day01.homework1.future;

import java.util.concurrent.*;

/**
 * CompletableFuture allOf的使用
 */
public class ThreadDemo2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        MethodClass2 methodClass = new MethodClass2();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->methodClass.sum());

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+completableFuture.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass2{
    int result;

    public int sum()  {
        result = fibo(36);
        return result;
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
