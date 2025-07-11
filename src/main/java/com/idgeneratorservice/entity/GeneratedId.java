package com.idgeneratorservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "generated_ids")
public class GeneratedId {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID entityId;

    @Column(name = "generated_id", nullable = false)
    private String generatedId;

    @Column(name = "strategy_type", nullable = false)
    private String strategyType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public GeneratedId() {
        this.createdAt = LocalDateTime.now();
    }

    public GeneratedId(String generatedId, String strategyType) {
        this();
        this.generatedId = generatedId;
        this.strategyType = strategyType;
    }
}
