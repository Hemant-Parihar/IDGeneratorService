package com.idgeneratorservice.service;

public interface IdGenerationStrategy {
    /**
 * Generates and returns a unique identifier as a string.
 *
 * @return a newly generated unique identifier
 */
String generateId();
    /**
 * Returns the name of the ID generation strategy.
 *
 * @return the strategy name
 */
String getStrategyName();
}
