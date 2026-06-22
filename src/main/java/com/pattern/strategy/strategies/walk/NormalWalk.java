package com.pattern.strategy.strategies.walk;

public class NormalWalk implements Walkable {


    @Override
    public void walk() {
        System.out.println("Normal Walk");
    }
}
