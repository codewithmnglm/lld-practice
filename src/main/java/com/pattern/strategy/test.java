package com.pattern.strategy;

import com.pattern.strategy.client.Robot;
import com.pattern.strategy.strategies.fly.NormalFly;
import com.pattern.strategy.strategies.talk.NormalTalk;
import com.pattern.strategy.strategies.walk.NoWalk;
import com.pattern.strategy.strategies.walk.NormalWalk;
import com.pattern.strategy.strategies.walk.Walkable;

public class test {

    static void main() {

        Robot robot = new Worker(new NormalTalk(), new NoWalk(), new NormalFly());
        robot.projection();
        robot.walk();

    }
}
