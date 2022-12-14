package com.study.openapi.redis.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Component
@ReadingConverter
public class BytesToAtomicIntegerConverter implements Converter<byte[], AtomicLong> {

    @Override
    public AtomicLong convert(byte[] source) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }

        int n = NumberUtils.parseNumber(
                new String(source, StandardCharsets.UTF_8), Integer.class);
        return new AtomicLong(n);
    }
}
