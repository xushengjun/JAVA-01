package org.example;

import org.example.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        Student student = ctx.getBean(Student.class);
        System.out.println(student);
    }
}
