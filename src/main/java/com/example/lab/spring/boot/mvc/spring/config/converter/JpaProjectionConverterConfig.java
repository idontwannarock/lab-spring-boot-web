package com.example.lab.spring.boot.mvc.spring.config.converter;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.convert.support.GenericConversionService;

import java.lang.reflect.Field;

@Configuration
class JpaProjectionConverterConfig {

    @SuppressWarnings("java:S3011")
    @EventListener(ApplicationReadyEvent.class)
    public void offsetDateTimeConversion() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = Class.forName("org.springframework.data.projection.ProxyProjectionFactory");
        Field field = aClass.getDeclaredField("CONVERSION_SERVICE");
        field.setAccessible(true);
        GenericConversionService service = (GenericConversionService) field.get(null);

        // 此段為註冊 JPA 做 projection 時使用的各種 converters
        service.addConverter(new InstantConverter());

        service.addConverter(new ChatroomStatusConverter());
        service.addConverter(new ChatroomUserRoleConverter());
        service.addConverter(new MessageStatusConverter());
    }
}
