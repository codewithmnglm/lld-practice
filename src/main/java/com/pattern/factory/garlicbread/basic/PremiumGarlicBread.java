package com.pattern.factory.garlicbread.basic;

import com.pattern.factory.garlicbread.GarlicBread;

public class PremiumGarlicBread extends GarlicBread {


    @Override
    public void prepare() {
        System.out.println("Singh : GarlicBread : Premium");
    }
}
