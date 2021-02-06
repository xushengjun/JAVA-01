package day01.homework2;

import day01.homework2.FileUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = new HelloClassLoader().findClass("Hello");
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] vars = null;
        try {
            vars = FileUtils.getContent
                    ("/Users/xushengjun/Documents/workspace/JAVA-01/Week_01/src/day01/homework2/Hello.xlass");

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[vars.length];
        for (int i = 0; i < vars.length; i++) {
            bytes[i] = (byte) (255 - vars[i]);
        }

        return defineClass(name,bytes,0, bytes.length);
    }

}
