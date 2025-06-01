package com.github.zachpanter.zach_lifting_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "muscle_group_lookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuscleGroupLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial PRIMARY KEY
    @Column(name = "muscle_group_id")
    private Long muscleGroupId;

    @Column(name = "muscle_group_name", unique = true, nullable = false, columnDefinition = "TEXT")
    private String muscleGroupName;
}