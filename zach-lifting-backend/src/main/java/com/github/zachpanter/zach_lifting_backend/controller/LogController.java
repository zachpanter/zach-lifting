package com.github.zachpanter.zach_lifting_backend.controller;

import com.github.zachpanter.zach_lifting_backend.model.Log;
import com.github.zachpanter.zach_lifting_backend.model.Lift; // Required for handling nested Lift objects
import com.github.zachpanter.zach_lifting_backend.service.LogService;
import com.github.zachpanter.zach_lifting_backend.service.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;
    private final LiftService liftService; // To handle Lift association in Log requests

    @Autowired
    public LogController(LogService logService, LiftService liftService) {
        this.logService = logService;
        this.liftService = liftService;
    }

    @GetMapping
    public ResponseEntity<List<Log>> getAllLogs() {
        List<Log> logs = logService.getAllLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogById(@PathVariable Long id) {
        Optional<Log> log = logService.getLogById(id);
        return log.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Log> createLog(@RequestBody Log log) {
        // Ensure the associated Lift exists and is properly set
        if (log.getLift() == null || log.getLift().getLiftId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Lift ID is required for a log
        }
        Optional<Lift> existingLift = liftService.getLiftById(log.getLift().getLiftId());
        if (existingLift.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Associated Lift not found
        }
        log.setLift(existingLift.get()); // Set the managed Lift entity

        Log savedLog = logService.saveLog(log);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Log> updateLog(@PathVariable Long id, @RequestBody Log logDetails) {
        return logService.getLogById(id)
                .map(log -> {
                    // Update fields
                    log.setReps(logDetails.getReps());
                    log.setResistance(logDetails.getResistance());

                    // Update associated Lift if provided in payload
                    if (logDetails.getLift() != null && logDetails.getLift().getLiftId() != null) {
                        liftService.getLiftById(logDetails.getLift().getLiftId())
                                .ifPresent(log::setLift);
                    }
                    Log updatedLog = logService.saveLog(log);
                    return new ResponseEntity<>(updatedLog, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLog(@PathVariable Long id) {
        try {
            logService.deleteLog(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-lift/{liftId}")
    public ResponseEntity<List<Log>> getLogsByLiftId(@PathVariable Long liftId) {
        List<Log> logs = logService.getLogsByLiftId(liftId);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}