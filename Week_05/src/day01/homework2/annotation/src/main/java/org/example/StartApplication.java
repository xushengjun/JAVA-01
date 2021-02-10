package org.example;

import org.example.factory.AnnotationBeanFactory;
import org.example.factory.BeanFactory;
import org.example.service.AccountService;

public class StartApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        BeanFactory beanFactory = new AnnotationBeanFactory();
        AccountService accountService = (AccountService) beanFactory.getBeans("accountService");
        accountService.transfer("jack","rose",1000);
    }



}
