package org.example;

import org.example.Config.BeanConfiguration;
import org.example.Controller.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {

        App app = new App();
        app.Run();

    }
}