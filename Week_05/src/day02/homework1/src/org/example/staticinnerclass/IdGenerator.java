package org.example.staticinnerclass;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 静态内部类
 * 当外部类IdGenerator被加载时，并不会加载SigletonHolder类
 * 只有当调用getInstance方法时，SigletonHolder类才会被加载，这个时候才会创建instance
 * instance的唯一性、创建过程的线程安全性，都由JVM来保证
 *  这种实现既安全，又能做到延迟加载
 */
public class IdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private IdGenerator() {
    }

    private static class SigletonHolder{
        private static final IdGenerator instance = new IdGenerator();
    }

    public static IdGenerator getInstance(){
        return SigletonHolder.instance;
    }
    public int incAndGet(){
        return atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = IdGenerator.getInstance();
        System.out.println(idGenerator.incAndGet());
    }
}
