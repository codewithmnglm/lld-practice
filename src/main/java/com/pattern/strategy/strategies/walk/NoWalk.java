package com.pattern.strategy.strategies.walk;

public class NoWalk implements Walkable {

    @Override
    public void walk() {
        System.out.println("No Walk");
    }
}
