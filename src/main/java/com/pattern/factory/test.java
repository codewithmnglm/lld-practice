package com.pattern.factory;

import com.pattern.factory.burerhouse.SinghBurger;
import com.pattern.factory.factory.MealFactory;
import com.pattern.factory.garlicbread.GarlicBread;

public class test {


    public static void main() {

       MealFactory mealFactory = new SinghBurger();
       GarlicBread gb = mealFactory.createGarlicBread("premimum");
       gb.prepare();


    }
}
