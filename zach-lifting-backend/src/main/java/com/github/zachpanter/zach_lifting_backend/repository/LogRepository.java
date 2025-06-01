package com.github.zachpanter.zach_lifting_backend.repository;

import com.github.zachpanter.zach_lifting_backend.model.Log;
import com.github.zachpanter.zach_lifting_backend.model.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByLift(Lift lift);
    List<Log> findByLiftLiftId(Long liftId);
    List<Log> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Log> findByRepsGreaterThan(Integer reps);
}