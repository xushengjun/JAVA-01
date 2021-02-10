package org.example.factory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Object getProxy(String proxyType,Object obj){
        if ("jdk".equals(proxyType)){
            Class<?>[] interfaces = obj.getClass().getInterfaces();
            if (interfaces==null||interfaces.length==0){
                throw new RuntimeException(String.format("该类:%s没有实现接口，无法通过jdk的方式进行代理",obj.getClass().getName()));
            }
            return getJdkProxy(obj);
        }else if ("cglib".equals(proxyType)){
            return getCglib(obj);
        }else {
            throw new RuntimeException(String.format("该类型：%s，暂不支持",proxyType));
        }
    }

    private static Object getCglib(Object obj) {
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)  {
                Object result = null;
                System.out.println("cglib开始代理");
                try {
                    result = method.invoke(obj,args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                System.out.println("cglib结束代理");
                return result;
            }
        });
    }

    private static Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                obj.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = null;
                        System.out.println("jdk开始代理");
                        try {
                            result = method.invoke(obj,args);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        System.out.println("jdk结束代理");
                        return result;
                    }
                });
    }
}
