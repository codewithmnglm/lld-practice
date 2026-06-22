package com.pattern.factory.burerhouse;

import com.pattern.factory.burger.Burger;
import com.pattern.factory.burger.wheat.BasicWheatBurger;
import com.pattern.factory.burger.wheat.PremiumWheatBurger;
import com.pattern.factory.burger.wheat.StandardWheatBurger;
import com.pattern.factory.factory.MealFactory;
import com.pattern.factory.garlicbread.GarlicBread;
import com.pattern.factory.garlicbread.wheat.BasicWheatGarlicBread;
import com.pattern.factory.garlicbread.wheat.PremiumWheatGarlicBread;
import com.pattern.factory.garlicbread.wheat.StandardWheatGarlicBread;

public  class KingBurger extends MealFactory {


    @Override
    public Burger createBurger(String type) {

        if (type == "basic") {
            return new BasicWheatBurger();
        } else if (type == "premimum") {
            return new PremiumWheatBurger();
        } else if (type == "standard") {
            return new StandardWheatBurger();
        }
        else throw new IllegalArgumentException("Burger Type Not Found");
    }

    @Override
    public GarlicBread createGarlicBread(String type) {
        if (type == "basic") {
            return new BasicWheatGarlicBread();
        } else if (type == "premimum") {
            return new PremiumWheatGarlicBread();
        } else if (type == "standard") {
            return new StandardWheatGarlicBread();
        }
        else throw new IllegalArgumentException("Garlic Bread Type Not Found");
    }


}
