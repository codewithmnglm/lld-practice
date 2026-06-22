package com.pattern.strategy.strategies.talk;

import com.pattern.strategy.strategies.walk.Walkable;

public class NormalTalk implements Talkable {

    @Override
    public void talk() {
        System.out.println("Normal Talk");
    }
}
