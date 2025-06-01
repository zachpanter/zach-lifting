package com.github.zachpanter.zach_lifting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set; // For the ManyToMany relationship with Lift

@Entity
@Table(name = "muscle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Muscle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial PRIMARY KEY
    @Column(name = "muscle_id")
    private Long muscleId;

    @Column(name = "muscle_name", unique = true, nullable = false, columnDefinition = "TEXT")
    private String muscleName;

    @ManyToOne(fetch = FetchType.LAZY) // Many Muscles belong to one MuscleGroupLookup
    @JoinColumn(name = "muscle_group_id", nullable = false) // Foreign key column
    private MuscleGroupLookup muscleGroup;

    // Many-to-many relationship with Lift via LiftMuscleXref (optional bidirectional)
    @ManyToMany(mappedBy = "muscles") // 'muscles' is the field in Lift that owns the relationship
    private Set<Lift> lifts;
}