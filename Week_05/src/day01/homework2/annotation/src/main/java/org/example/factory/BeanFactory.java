package org.example.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface BeanFactory {
    Map<String,Object> IOC_MAP = new HashMap<>();

    Object getBeans(String name);

    Object getBeans(Class<?> clazz);

    Set<String> getAllBeanName();

}
