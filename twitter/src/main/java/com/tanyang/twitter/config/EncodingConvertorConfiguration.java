package com.tanyang.twitter.config;

import com.tanyang.twitter.condition.GBKCondition;
import com.tanyang.twitter.condition.UTF8Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncodingConvertorConfiguration {

    @Bean
    @Conditional(UTF8Condition.class)
    public EncodingConvertorConfiguration createUTF8EncodingConvertor() {
        System.out.println("生成UTF8类型的编码转换器");
        return null;
    }

    @Bean
    @Conditional(GBKCondition.class)
    public EncodingConvertorConfiguration createGBKEncodingConvertor() {
        System.out.println("生成GBK类型的编码转换器");
        return null;
    }
}

