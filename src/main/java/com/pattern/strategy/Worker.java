package com.pattern.strategy;

import com.pattern.strategy.client.Robot;
import com.pattern.strategy.strategies.fly.Flyable;
import com.pattern.strategy.strategies.talk.Talkable;
import com.pattern.strategy.strategies.walk.Walkable;

public class Worker extends Robot {


    public Worker(Talkable t, Walkable w, Flyable f) {
        super(t, w, f);
    }

    @Override
    public void projection() {
        System.out.println("Worker Robot : Projection");
    }
}
