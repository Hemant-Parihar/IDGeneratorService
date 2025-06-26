package com.idgeneratorservice.service;

import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdStrategy implements IdGenerationStrategy {
    private final long startEpoch = 1609459200000L; // 2021-01-01
    private final long nodeId;
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    private static final long NODE_ID_BITS = 10L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long MAX_NODE_ID = -1L ^ (-1L << NODE_ID_BITS);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS);
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;
    private static final long NODE_ID_SHIFT = SEQUENCE_BITS;

    /**
     * Constructs a SnowflakeIdStrategy with a default node ID of 1.
     *
     * @throws IllegalArgumentException if the node ID is not within the valid range (0 to MAX_NODE_ID).
     */
    public SnowflakeIdStrategy() {
        this.nodeId = 1L;
        if (nodeId > MAX_NODE_ID || nodeId < 0) {
            throw new IllegalArgumentException("Node ID must be between 0 and " + MAX_NODE_ID);
        }
    }

    /**
     * Generates a unique ID string using the Snowflake algorithm.
     *
     * The generated ID encodes the current timestamp, node identifier, and a sequence number to ensure uniqueness within the same millisecond. If the system clock moves backwards, a RuntimeException is thrown.
     *
     * @return a unique ID as a string
     */
    @Override
    public synchronized String generateId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID.");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        long id = ((timestamp - startEpoch) << TIMESTAMP_LEFT_SHIFT) |
                 (nodeId << NODE_ID_SHIFT) |
                 sequence;

        return String.valueOf(id);
    }

    /**
     * Waits until the system clock advances past the specified timestamp and returns the new timestamp.
     *
     * @param lastTimestamp the timestamp to surpass
     * @return the current system time in milliseconds after it exceeds lastTimestamp
     */
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * Returns the name of the ID generation strategy, which is "SNOWFLAKE".
     *
     * @return the string "SNOWFLAKE"
     */
    @Override
    public String getStrategyName() {
        return "SNOWFLAKE";
    }
}
