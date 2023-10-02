package com.example.lab.spring.boot.mvc.spring.config.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;

public class InstantConverter implements Converter<Timestamp, Instant> {

    @Override
    public Instant convert(Timestamp source) {
        return source.toLocalDateTime().toInstant(ZoneOffset.UTC);
    }
}
