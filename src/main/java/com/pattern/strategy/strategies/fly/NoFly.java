package com.pattern.strategy.strategies.fly;


public class NoFly implements Flyable {


    @Override
    public void fly() {
        System.out.println("No fly");
    }
}
