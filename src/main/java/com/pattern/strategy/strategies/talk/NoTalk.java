package com.pattern.strategy.strategies.talk;

public class NoTalk implements Talkable {


    @Override
    public void talk() {
        System.out.println("No Talk");
    }
}
