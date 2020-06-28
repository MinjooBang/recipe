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

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:discounts.properties")
@ComponentScan(basePackages = "com.apress.springrecipes.shop")
public class ShopConfiguration {

    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {
        String path = System.getProperty("java.io.tmpdir" + "/cashier");
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
    public ProductCreator productCreatorFactory() {
        /*
        인스턴스 팩토리 메소드로 POJO 생성하기
         */
        ProductCreator factory = new ProductCreator();
        Map<String, Product> products = new HashMap<>();
        products.put("aaa", new Battery("AAA", 2.5));
        products.put("cdrw", new Battery("cdrw", 1.5));
        products.put("dvdrw", new Battery("dvdrw", 3.0));
        factory.setProducts(products);
        return factory;
    }

    @Bean
    public Product aaa() {
        //  return productCreatorFactory().creatProduct("aaa"); 인스턴스 팩토리 메소드
        Battery aaa = new Battery("AAA", 2.5);
        return aaa;

    }

    @Bean
    public Product cdrw() {
        // return productCreatorFactory().creatProduct("cdrw");인스턴스 팩토리 메소드
        Disc aaa = new Disc("CD-RW", 1.5);
        return aaa;
    }

    @Bean
    public Product dvdrw() {
        // return productCreatorFactory().creatProduct("dvdrw");인스턴스 팩토리 메소드
        Disc aaa = new Disc("DVD-RW", 3.0);
        return aaa;
    }

    @Bean
    public DiscountFactoryBean discountFactoryBeanAAA(){
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(aaa());
        factory.setDiscount(0.2);
        return factory;
    } @Bean
    public DiscountFactoryBean discountFactoryBeanCDRW(){
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(cdrw());
        factory.setDiscount(0.1);
        return factory;
    } @Bean
    public DiscountFactoryBean discountFactoryBeanDVDRE(){
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(dvdrw());
        factory.setDiscount(0.1);
        return factory;
    }



}
