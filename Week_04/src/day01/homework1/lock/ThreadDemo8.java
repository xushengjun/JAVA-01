package day01.homework1.lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport的使用
 */
public class ThreadDemo8 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        MethodClass8 methodClass = new MethodClass8();
        Thread main = Thread.currentThread();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
                LockSupport.unpark(main);
            }
        });

        t.start();
        if (methodClass.getResult()==0){
            LockSupport.park();
        }
//        t.interrupt();  或者使用interrupt，也能将子线程从暂停状态恢复
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+methodClass.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }
}

class MethodClass8{
    int result;
    public int sum() {
        try {
            result = fibo(36);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return result;
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    public int getResult() throws InterruptedException {
        return result;
    }

}
