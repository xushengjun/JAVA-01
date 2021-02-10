package org.example.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.example.Application;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {

    private final static Map<String, Object> IOC_MAP = new HashMap<>();

    static {
        //读取ioc配置文件，获取文件对象
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = Application.class.getClassLoader()
                .getResourceAsStream("application-ioc.xml");
        Document document = null;
        try {
            document = saxReader.read(inputStream);

            //获取bean节点集合
            Element root = document.getRootElement();
            List<Element> elements = root.selectNodes("//bean");
            if (elements == null || elements.size() == 0) {
                throw new RuntimeException("无bean标签");
            }

            //遍历bean节点集合，根据class属性，反射得到对象并存入容器IOC_MAP中
            for (Element element : elements) {
                //获取id和class属性值
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
//            System.out.println("id:"+id+",clazz:"+clazz);
                //反射得到对象
                Object object = Class.forName(clazz).newInstance();
                IOC_MAP.put(id, object);
            }
            //为含有property属性的节点注入属性
            List<Element> propertyElementList = root.selectNodes("//property");
            if (propertyElementList != null && propertyElementList.size() != 0) {
                for (Element element : propertyElementList) {
                    String ref = element.attributeValue("ref");
                    Element parent = element.getParent();
                    String id = parent.attributeValue("id");
                    Object targetObj = IOC_MAP.get(id);
                    Object argObj = IOC_MAP.get(ref);
                    for (Method method : targetObj.getClass().getMethods()) {
                        if (method.getName().equalsIgnoreCase("set" + ref)) {
                            method.invoke(targetObj, argObj);
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static Object getObject(String id) {
        return IOC_MAP.get(id);
    }
}
