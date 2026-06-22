package com.pattern.strategy.strategies.fly;

import com.pattern.strategy.strategies.walk.Walkable;

public class NormalFly implements Flyable {

    @Override
    public void fly() {
        System.out.println("Normal fly");
    }
}
