package com.idgeneratorservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "generated_ids")
public class GeneratedId {

    // SequenceGenerator delegates the ID generation directly to the database.
    // Database sequences are inherently atomic and thread-safe, ensuring uniqueness across concurrent calls.
    // If multiple microservices connect to the same database, they will always draw unique IDs from the shared sequence.
    // If your microservices ever use separate databases, then uniqueness cannot be guaranteed by this strategy alone.

    /*
    •	If performance and scalability are crucial, consider using UUIDs instead of sequence-generated IDs.

            @Id
            @GeneratedValue(strategy = GenerationType.UUID)
            private UUID id;

        UUIDs are safe, performant, and universally unique across distributed services.
     */

    @Id
    @SequenceGenerator( name = "pk_generator",
                        sequenceName = "unique_seq",
                        allocationSize = 1 )
    @GeneratedValue( generator = "pk_generator",
                     strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructors
    public GeneratedId() {
        this.createdAt = LocalDateTime.now();
    }
}
