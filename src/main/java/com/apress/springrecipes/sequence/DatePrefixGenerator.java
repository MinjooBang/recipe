package com.apress.springrecipes.sequence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DatePrefixGenerator implements PrefixGenerator {

    public String setPattern(String yyyMMdd) {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }
}
/*
@Primary - 모호한 자동 연결 명시 여러빈이 자동연결 대상일 경우 우선권 부여

 */