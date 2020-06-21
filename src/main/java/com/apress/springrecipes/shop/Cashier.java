package com.apress.springrecipes.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.util.Date;
import java.util.Locale;

@Component
public class Cashier {
    private String fileName;
    private String path;
    private BufferedWriter writer;
    @Autowired
    private MessageSource messageSource;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    public void checkout(ShoppingCart cart){
        String alert = messageSource.getMessage("alert.inventory.checkout", new Object[]{cart.getItems(), new Date()}, Locale.US);
        System.out.println(alert);
    }
}
