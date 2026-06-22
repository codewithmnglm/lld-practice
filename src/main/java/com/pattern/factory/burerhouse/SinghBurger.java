package com.pattern.factory.burerhouse;

import com.pattern.factory.burger.Burger;
import com.pattern.factory.burger.baisc.BasicBurger;
import com.pattern.factory.burger.baisc.PremiumBurger;
import com.pattern.factory.burger.baisc.StandardBurger;
import com.pattern.factory.factory.MealFactory;
import com.pattern.factory.garlicbread.GarlicBread;
import com.pattern.factory.garlicbread.basic.BasicGarlicBread;
import com.pattern.factory.garlicbread.basic.PremiumGarlicBread;
import com.pattern.factory.garlicbread.basic.StandardGarlicBread;



public class SinghBurger extends MealFactory {

    @Override
    public Burger createBurger(String type) {

        if (type == "basic") {
            return new BasicBurger();
        } else if (type == "premimum") {
            return new PremiumBurger();
        } else if (type == "standard") {
            return new StandardBurger();
        } else throw new IllegalArgumentException("Burger Type Not Found");
    }

    @Override
    public GarlicBread createGarlicBread(String type) {
        if (type == "basic") {
            return new BasicGarlicBread();
        } else if (type == "premimum") {
            return new PremiumGarlicBread();
        } else if (type == "standard") {
            return new StandardGarlicBread();
        }
        else throw new IllegalArgumentException("Garlic Bread Type Not Found");
    }
}

