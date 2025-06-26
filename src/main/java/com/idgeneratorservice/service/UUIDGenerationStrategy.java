package com.idgeneratorservice.service;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UUIDGenerationStrategy implements IdGenerationStrategy {
    /**
     * Generates and returns a new unique identifier as a UUID string.
     *
     * @return a randomly generated UUID in string format
     */
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Returns the name of the ID generation strategy.
     *
     * @return the string "UUID"
     */
    @Override
    public String getStrategyName() {
        return "UUID";
    }
}
