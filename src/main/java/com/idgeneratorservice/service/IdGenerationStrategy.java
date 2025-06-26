package com.idgeneratorservice.service;

public interface IdGenerationStrategy {
    String generateId();
    String getStrategyName();
}
