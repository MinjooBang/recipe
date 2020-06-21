package com.apress.springrecipes.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Date;

@Component
public class Cashier {
    @Value("checkout")
    private String fileName;
    @Value("c:/Windows/Temp/cashier")
    private String path;
    private BufferedWriter writer;

   /* 2.7
   @Autowired
    private MessageSource messageSource;*/

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @PostConstruct
    public void openFile() throws IOException {
        File targetDir = new File(path);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists()) {
            checkoutFile.createNewFile();
        }
        writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(checkoutFile, true)));
    }


   /*2.7
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }*/

    public void checkout(ShoppingCart cart) throws IOException {
        writer.write(new Date() + "\t" + cart.getItems() + "\r\n");
        writer.flush();
       /* 2.7
       String alert = messageSource.getMessage("alert.inventory.checkout", new Object[]{cart.getItems(), new Date()}, Locale.US);
        System.out.println(alert);*/
    }
    @PreDestroy
    public void closeFile() throws IOException {
        writer.close();
    }
}
