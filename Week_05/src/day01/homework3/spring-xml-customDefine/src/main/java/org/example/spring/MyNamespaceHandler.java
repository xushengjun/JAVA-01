package org.example.spring;

import org.example.model.Student;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("student",new StudentBeanDefinitionParser(Student.class));

    }
}