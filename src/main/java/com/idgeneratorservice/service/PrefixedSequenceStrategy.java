package com.idgeneratorservice.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicLong;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PrefixedSequenceStrategy implements IdGenerationStrategy {
    private final AtomicLong sequence = new AtomicLong(0);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String DEFAULT_PREFIX = "ID";

    @Override
    public String generateId() {
        String dateStr = LocalDateTime.now().format(DATE_FORMAT);
        return String.format("%s-%s-%06d",
            DEFAULT_PREFIX,
            dateStr,
            sequence.incrementAndGet());
    }

    @Override
    public String getStrategyName() {
        return "PREFIXED_SEQUENCE";
    }
}
