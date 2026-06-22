package com.pattern.strategy.client;

import com.pattern.strategy.strategies.fly.Flyable;
import com.pattern.strategy.strategies.talk.Talkable;
import com.pattern.strategy.strategies.walk.Walkable;

public abstract class Robot {

    public abstract void projection();
    public Talkable t;
    public Walkable w;
    public Flyable f;

    public Robot(Talkable t , Walkable w, Flyable f){
        this.t = t;
        this.w = w;
        this.f = f;
    }


    public void walk(){
        w.walk();
    }
    public void fly(){
        f.fly();
    }
    public void talk(){
        t.talk();
    }



}
