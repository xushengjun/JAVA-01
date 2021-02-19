package org.example.doublecheck;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双重检测法
 * 既支持延迟加载，也支持高并发
 */
public class IdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static IdGenerator instance = null;

    private IdGenerator() {
    }

    public static IdGenerator getInstance(){
        if (instance ==null){
            synchronized (IdGenerator.class){
                if (instance==null){
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public int incAndGet(){
        return atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = IdGenerator.getInstance();
        System.out.println(idGenerator.incAndGet());
    }
}
