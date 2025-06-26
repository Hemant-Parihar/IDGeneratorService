package com.idgeneratorservice.service;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UUIDGenerationStrategy implements IdGenerationStrategy {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getStrategyName() {
        return "UUID";
    }
}
