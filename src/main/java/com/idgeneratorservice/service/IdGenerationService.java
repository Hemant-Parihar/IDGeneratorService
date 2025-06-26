package com.idgeneratorservice.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class IdGenerationService {
    private final Map<String, IdGenerationStrategy> strategies;

    public IdGenerationService(List<IdGenerationStrategy> strategyList) {
        strategies = new HashMap<>();
        strategyList.forEach(strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    public String generateId(String strategyType) {
        IdGenerationStrategy strategy = strategies.get(strategyType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown strategy type: " + strategyType);
        }
        return strategy.generateId();
    }

    public List<String> getAvailableStrategies() {
        return strategies.keySet().stream().toList();
    }
}
