package com.pattern.factory.garlicbread.wheat;

import com.pattern.factory.garlicbread.GarlicBread;

public class BasicWheatGarlicBread extends GarlicBread {

    @Override
    public void prepare() {
        System.out.println("Singh : Wheat GarlicBread : Basic");
    }
}
