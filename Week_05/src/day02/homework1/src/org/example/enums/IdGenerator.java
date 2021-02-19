package org.example.enums;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 枚举
 * 通过枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性
 */
public enum IdGenerator {
    INCTANCE;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public long getId(){
        return atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = IdGenerator.INCTANCE;
        System.out.println(idGenerator.getId());
    }
}
