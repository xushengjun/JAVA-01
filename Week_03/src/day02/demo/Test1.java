package day02.demo;

public class Test1 {

    public static void main(String[] args) {
        Thread a  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程名："+Thread.currentThread().getName());
            }
        });

        a.setName("thread-1");
        a.setDaemon(true);
        a.start();
    }

}
