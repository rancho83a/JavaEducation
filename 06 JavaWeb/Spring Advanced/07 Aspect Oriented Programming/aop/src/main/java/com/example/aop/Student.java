package com.example.aop;

import org.springframework.stereotype.Component;

@Component
public class Student {
    public void sayHello(){
        System.out.println("Hello");
    }

    public void echo(String echo){
        System.out.println("Echo: "+echo);

    }
    public String concat (String a, String b){
        System.out.println(a+b);
        return a+b;
    }

    public void boom(){
        throw new IllegalStateException("BOOOOOOOM!!!!!!!!");
    }
}
