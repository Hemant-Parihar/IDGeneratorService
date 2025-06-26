package com.idgeneratorservice.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class IdGenerationService {
    private final Map<String, IdGenerationStrategy> strategies;

    /**
     * Constructs an IdGenerationService with the provided list of ID generation strategies.
     *
     * Each strategy is registered and accessible by its unique strategy name.
     *
     * @param strategyList the list of ID generation strategies to register
     */
    public IdGenerationService(List<IdGenerationStrategy> strategyList) {
        strategies = new HashMap<>();
        strategyList.forEach(strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    /**
     * Generates an ID using the specified strategy type.
     *
     * @param strategyType the name of the ID generation strategy to use
     * @return the generated ID as a string
     * @throws IllegalArgumentException if the specified strategy type is not registered
     */
    public String generateId(String strategyType) {
        IdGenerationStrategy strategy = strategies.get(strategyType.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown strategy type: " + strategyType);
        }
        return strategy.generateId();
    }

    /**
     * Returns a list of all available ID generation strategy names.
     *
     * @return a list of strategy names currently registered in the service
     */
    public List<String> getAvailableStrategies() {
        return strategies.keySet().stream().toList();
    }
}
