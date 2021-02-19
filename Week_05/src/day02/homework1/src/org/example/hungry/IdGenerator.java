package org.example.hungry;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 懒汉式有性能问题，不支持高并发
 */
public class IdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static IdGenerator instance = null;

    private IdGenerator() { }

    public static synchronized IdGenerator getInstance(){
        if (instance==null){
            instance = new IdGenerator();
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
