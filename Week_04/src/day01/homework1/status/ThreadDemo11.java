package day01.homework1.status;

import java.util.concurrent.ExecutionException;

/**
 * 使用Thread.activeCount()配合yield方法
 * 如果存活线程数量大于创建子线程之前的数量（即子线程还存活），就让主线程让出cpu执行权，退回到可执行状态
 */
public class ThreadDemo11 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int count = Thread.activeCount();
        System.out.println("当前线程总数："+count);
        long start=System.currentTimeMillis();
        MethodClass11 methodClass = new MethodClass11();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
            }
        });
        t.start();
        System.out.println("当前线程总数："+count);
        while (Thread.activeCount()>count){
            Thread.yield();
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass11{
    int result;
    public int sum() {
        return result = fibo(36);
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
