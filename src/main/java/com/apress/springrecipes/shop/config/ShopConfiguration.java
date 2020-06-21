package com.apress.springrecipes.shop.config;

import com.apress.springrecipes.shop.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan(basePackages = "com.apress.springrecipes.shop")
public class ShopConfiguration {

    @Bean(initMethod = "openFile",destroyMethod = "closeFile")
    public Cashier cashier(){
        String path = System.getProperty("java.io.tmpdir"+"/cashier");
        Cashier c1 = new Cashier();
        c1.setFileName("checkout");
        c1.setPath(path);
        return c1;
    }
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(1); //쓸모없는 메시지를 다시 읽지 x
        return messageSource;
    }

    @Value("classpath:banner.txt")
    private Resource banner;

    @Bean
    public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BannerLoader bannerLoader() {
        BannerLoader bl = new BannerLoader();
        bl.setBanner(banner);
        return bl;
    }

    @Bean
    public Product aaa() {
        Battery p1 = new Battery("AAA", 2.5);
        p1.setRechargeable(true);
        return p1;
    }

    @Bean
    public Product cdrw() {
        Disc p2 = new Disc("CD-RW", 1.5);
        p2.setCapacity(700);
        return p2;
    }

    @Bean
    public Product dvdrw() {
        Disc p2 = new Disc("DBD-RW", 3.0);
        p2.setCapacity(700);
        return p2;

    }


}
