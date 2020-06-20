package com.apress.springrecipes.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.shop.config.ShopConfiguration;

public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = context.getBean("aaa", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        Product dvdrw = context.getBean("dvdrw", Product.class);

        ShoppingCart cart1 = context.getBean("ShoppingCart",ShoppingCart.class);
        cart1.addItem(aaa);
        cart1.addItem(cdrw);

        System.out.println("shopping Cart1 contain..."+cart1.getItems());

        ShoppingCart cart2 = context.getBean("ShoppingCart",ShoppingCart.class);
        cart2.addItem(dvdrw);

        System.out.println("shopping Cart2 contain..."+cart2.getItems());
    }
}
