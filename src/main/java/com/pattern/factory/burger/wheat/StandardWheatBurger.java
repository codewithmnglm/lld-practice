package com.pattern.factory.burger.wheat;

import com.pattern.factory.burger.Burger;


public class StandardWheatBurger extends Burger {
    @Override
    public void prepare() {
        System.out.println("Kingh :-Wheat Standard Burger");
    }
}
