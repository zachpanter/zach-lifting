package com.github.zachpanter.zach_lifting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime; // For TIMESTAMP WITHOUT TIME ZONE

@Entity
@Table(name = "log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial PRIMARY KEY
    @Column(name = "log_id")
    private Long logId;

    @Column(name = "timestamp", nullable = false)
    // Default NOW() in SQL. Initialize in constructor/setter if you want Java to control it.
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY) // Many Logs can belong to one Lift
    @JoinColumn(name = "lift_id", nullable = false) // Foreign key column
    private Lift lift;

    @Column(name = "reps", nullable = false)
    private Integer reps; // int in SQL

    @Column(name = "resistance", nullable = false)
    private Integer resistance; // int in SQL
}