package day01.homework1.lock;

/**
 * 共享变量，使用join进行通信
 */
public class ThreadDemo4 {
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        MethodClass4 methodClass = new MethodClass4();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                methodClass.sum();
            }
        });
        t.start();
        t.join();
        int result = methodClass.result;
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程
    }


}

class MethodClass4{
    int result;

    public void sum() {
        result = fibo(36);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
