package com.github.zachpanter.zach_lifting_backend.controller;

import com.github.zachpanter.zach_lifting_backend.model.Lift;
import com.github.zachpanter.zach_lifting_backend.service.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lifts")
public class LiftController {

    private final LiftService liftService;

    @Autowired
    public LiftController(LiftService liftService) {
        this.liftService = liftService;
    }

    @GetMapping
    public ResponseEntity<List<Lift>> getAllLifts() {
        List<Lift> lifts = liftService.getAllLifts();
        return new ResponseEntity<>(lifts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lift> getLiftById(@PathVariable Long id) {
        Optional<Lift> lift = liftService.getLiftById(id);
        return lift.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Lift> createLift(@RequestBody Lift lift) {
        // lift_id is serial PRIMARY KEY, so no need to provide it in the request body
        Lift savedLift = liftService.saveLift(lift);
        return new ResponseEntity<>(savedLift, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lift> updateLift(@PathVariable Long id, @RequestBody Lift liftDetails) {
        return liftService.getLiftById(id)
                .map(lift -> {
                    lift.setLiftName(liftDetails.getLiftName());
                    lift.setPriority(liftDetails.getPriority());
                    lift.setOneRmMax(liftDetails.getOneRmMax());
                    Lift updatedLift = liftService.saveLift(lift);
                    return new ResponseEntity<>(updatedLift, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLift(@PathVariable Long id) {
        try {
            liftService.deleteLift(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}