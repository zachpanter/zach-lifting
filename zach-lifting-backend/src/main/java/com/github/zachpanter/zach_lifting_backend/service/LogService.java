package com.github.zachpanter.zach_lifting_backend.service;

import com.github.zachpanter.zach_lifting_backend.model.Log;
import com.github.zachpanter.zach_lifting_backend.model.Lift;
import com.github.zachpanter.zach_lifting_backend.repository.LogRepository;
import com.github.zachpanter.zach_lifting_backend.repository.LiftRepository; // Needed to fetch Lift by ID
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final LiftRepository liftRepository; // To validate and associate Lift with Log

    @Autowired
    public LogService(LogRepository logRepository, LiftRepository liftRepository) {
        this.logRepository = logRepository;
        this.liftRepository = liftRepository;
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public Optional<Log> getLogById(Long id) {
        return logRepository.findById(id);
    }

    public Log saveLog(Log log) {
        // Ensure timestamp is set if not already (default NOW() in SQL)
        if (log.getTimestamp() == null) {
            log.setTimestamp(LocalDateTime.now());
        }
        // Validate or retrieve the associated Lift object
        if (log.getLift() == null || log.getLift().getLiftId() == null) {
            throw new IllegalArgumentException("Lift must be provided for a Log entry.");
        }
        // If Lift is only provided by ID, fetch the full object
        liftRepository.findById(log.getLift().getLiftId())
                .ifPresent(log::setLift); // Set the managed Lift entity

        return logRepository.save(log);
    }

    public void deleteLog(Long id) {
        logRepository.deleteById(id);
    }

    public List<Log> getLogsByLiftId(Long liftId) {
        return logRepository.findByLiftLiftId(liftId);
    }

    public List<Log> getLogsBetweenTimestamps(LocalDateTime start, LocalDateTime end) {
        return logRepository.findByTimestampBetween(start, end);
    }
}