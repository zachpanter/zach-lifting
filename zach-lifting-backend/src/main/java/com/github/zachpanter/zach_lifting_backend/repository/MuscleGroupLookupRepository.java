package com.github.zachpanter.zach_lifting_backend.repository;

import com.github.zachpanter.zach_lifting_backend.model.MuscleGroupLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleGroupLookupRepository extends JpaRepository<MuscleGroupLookup, Long> {
    Optional<MuscleGroupLookup> findByMuscleGroupName(String muscleGroupName);
}