package org.example;

import org.example.factory.ProxyFactory;

public class Child {
    public void eat(){
        System.out.println("我是小孩，我爱吃奶！");
    }

    public static void main(String[] args) {
        Child child = (Child) ProxyFactory.getProxy("cglib",new Child());
        child.eat();
    }
}
