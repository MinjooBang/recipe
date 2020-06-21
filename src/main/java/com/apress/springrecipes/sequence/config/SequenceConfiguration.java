package com.apress.springrecipes.sequence.config;

import com.apress.springrecipes.sequence.DatePrefixGenerator;
import com.apress.springrecipes.sequence.SequenceGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SequenceConfiguration {
    @Bean
    public DatePrefixGenerator datePrefixGenerator(){
        DatePrefixGenerator dpg = new DatePrefixGenerator();
        dpg.setPattern("yyyMMdd");
        return dpg;
    }
    @Bean
    @DependsOn("datePrefixGenerator")
    public SequenceGenerator sequenceGenerator(){
        SequenceGenerator sequence = new SequenceGenerator();
        sequence.setInitial(100000);
        sequence.setSuffix("A");
        sequence.setPrefixGenerator(datePrefixGenerator());
        return sequence;

    }
}
// 2.3 POJO 레퍼런스와 자동 연결을 이용해서 다른 POJO랑 상호 작용
/*
@Autowired - @Bean 설정 객체 주입
 */