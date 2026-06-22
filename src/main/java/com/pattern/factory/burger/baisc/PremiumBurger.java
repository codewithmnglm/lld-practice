package com.pattern.factory.burger.baisc;

import com.pattern.factory.burger.Burger;

public class PremiumBurger extends Burger {
    @Override
    public void prepare() {
        System.out.println("Singh :- Premimum Burger");
    }
}
