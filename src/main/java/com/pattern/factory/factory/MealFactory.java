package com.pattern.factory.factory;

import com.pattern.factory.burger.Burger;
import com.pattern.factory.garlicbread.GarlicBread;

public abstract class MealFactory {


    public abstract Burger createBurger(String name);
    public abstract GarlicBread createGarlicBread(String name);



}
