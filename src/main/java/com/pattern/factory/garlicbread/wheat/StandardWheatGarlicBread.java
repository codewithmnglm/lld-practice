package com.pattern.factory.garlicbread.wheat;

import com.pattern.factory.garlicbread.GarlicBread;

public class StandardWheatGarlicBread extends GarlicBread {

    @Override
    public void prepare() {
        System.out.println("Singh : Wheat GarlicBread : Standard");
    }
}
