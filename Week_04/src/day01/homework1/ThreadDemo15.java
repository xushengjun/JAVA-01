package day01.homework1;

import java.util.concurrent.ExecutionException;

/**
 * 主线程和子线程之间没有线程通信，主线程采取傻瓜式地等待一定时间再输出
 * 子线程会在半秒钟之内结束计算，所以设定等待时间为1s钟
 */
public class ThreadDemo15 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        MethodClass15 methodClass = new MethodClass15();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
            }
        });
        t.start();
        Thread.sleep(1000);
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass15 {
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
