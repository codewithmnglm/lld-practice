package com.pattern.factory.burger.wheat;

import com.pattern.factory.burger.Burger;

public class PremiumWheatBurger extends Burger {
    @Override
    public void prepare() {
        System.out.println("Kingh :-Wheat Premium Burger");
    }
}
