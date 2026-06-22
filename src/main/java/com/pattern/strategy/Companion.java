package com.pattern.strategy;

import com.pattern.strategy.client.Robot;
import com.pattern.strategy.strategies.fly.Flyable;
import com.pattern.strategy.strategies.talk.Talkable;
import com.pattern.strategy.strategies.walk.Walkable;

public class Companion extends Robot {

    public Companion(Talkable t, Walkable w, Flyable f) {
        super(t, w, f);
    }
    @Override
    public void projection() {
      System.out.println("Companion Robot : Projection");
    }
}
