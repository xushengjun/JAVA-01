package day01.homework1.lock;

/**
 * 共享变量，使用synchronized作为互斥锁,使用wait、notifyAll进行通信，
 * 子线程计算和主线程获取结果，争夺同一把锁
 * 若子线程先获取到锁，则进行计算，计算后再释放锁，主线程获取到锁，然后拿到计算结果
 * 若主线程先获取到锁,判断下计算结果，如果计算结果等于0，就释放锁，然后等子线程执行完后通知它拿锁
 */
public class ThreadDemo5 {
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        MethodClass5 methodClass = new MethodClass5();
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

class MethodClass5{
    int result;

    public synchronized void sum() {
        result = fibo(36);
        notifyAll();
    }
    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    public synchronized int getResult() throws InterruptedException {
        if (result==0){
            wait();
        }
        return result;
    }
}
