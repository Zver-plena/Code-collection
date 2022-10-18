package com.example.springbootmaster.service;

/**
 * @author mrl
 * @data 2022/7/31
 */
public class HelloServiceImpl implements HelloService  {

    @Override
    public void sayHello(String name){
        if(name == null || name.trim() == ""){
            throw new RuntimeException("parameter is null");
        }
        System.out.println("hello " + name);
    }
}
