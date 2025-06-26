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

    /**
     * Generates a unique identifier string composed of a fixed prefix, the current date in "yyyyMMdd" format, and a zero-padded sequence number.
     *
     * @return the generated unique identifier string
     */
    @Override
    public String generateId() {
        String dateStr = LocalDateTime.now().format(DATE_FORMAT);
        return String.format("%s-%s-%06d",
            DEFAULT_PREFIX,
            dateStr,
            sequence.incrementAndGet());
    }

    /**
     * Returns the name of this ID generation strategy.
     *
     * @return the string "PREFIXED_SEQUENCE"
     */
    @Override
    public String getStrategyName() {
        return "PREFIXED_SEQUENCE";
    }
}
