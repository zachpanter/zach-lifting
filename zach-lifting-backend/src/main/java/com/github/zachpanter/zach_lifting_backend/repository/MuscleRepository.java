package com.github.zachpanter.zach_lifting_backend.repository;

import com.github.zachpanter.zach_lifting_backend.model.Muscle;
import com.github.zachpanter.zach_lifting_backend.model.MuscleGroupLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Long> {
    Optional<Muscle> findByMuscleName(String muscleName);
    List<Muscle> findByMuscleGroup(MuscleGroupLookup muscleGroup);
    List<Muscle> findByMuscleGroupMuscleGroupId(Long muscleGroupId); // Query by foreign key ID
}