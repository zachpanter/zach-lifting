package com.github.zachpanter.zach_lifting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set; // For the ManyToMany relationship with Muscle

@Entity
@Table(name = "lift")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial PRIMARY KEY
    @Column(name = "lift_id")
    private Long liftId;

    @Column(name = "lift_name", unique = true, nullable = false, columnDefinition = "TEXT")
    private String liftName;

    @Column(name = "priority")
    private Integer priority; // int in SQL

    @Column(name = "one_rm_max")
    private Integer oneRmMax; // int in SQL

    // Many-to-many relationship with Muscle via LiftMuscleXref
    // This is optional if you only interact via the XREF table directly.
    // If you want direct access from Lift to its Muscles:
    @ManyToMany
    @JoinTable(
            name = "lift_muscle_xref", // The name of the join table
            joinColumns = @JoinColumn(name = "lift_id"), // Column in join table referring to Lift
            inverseJoinColumns = @JoinColumn(name = "muscle_id") // Column in join table referring to Muscle
    )
    private Set<Muscle> muscles;
}