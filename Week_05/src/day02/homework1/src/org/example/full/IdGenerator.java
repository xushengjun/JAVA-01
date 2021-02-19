package org.example.full;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 饱汉式
 * 不支持延迟加载
 */
public class IdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() { }

    public static IdGenerator getInstance(){
        return instance;
    }
    public int incrementAndGet(){
        return atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        System.out.println(IdGenerator.getInstance().incrementAndGet());
    }
}
