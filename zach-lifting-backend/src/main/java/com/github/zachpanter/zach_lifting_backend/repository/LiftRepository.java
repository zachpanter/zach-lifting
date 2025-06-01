package com.github.zachpanter.zach_lifting_backend.repository;

import com.github.zachpanter.zach_lifting_backend.model.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LiftRepository extends JpaRepository<Lift, Long> {
    Optional<Lift> findByLiftName(String liftName);
    List<Lift> findByPriorityGreaterThan(Integer priority);
}