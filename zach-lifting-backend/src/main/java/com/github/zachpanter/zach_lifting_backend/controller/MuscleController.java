package com.github.zachpanter.zach_lifting_backend.controller;

import com.github.zachpanter.zach_lifting_backend.model.Muscle;
import com.github.zachpanter.zach_lifting_backend.service.MuscleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/muscles")
public class MuscleController {

    private final MuscleService muscleService;

    @Autowired
    public MuscleController(MuscleService muscleService) {
        this.muscleService = muscleService;
    }

    @GetMapping
    public ResponseEntity<List<Muscle>> findAll() {
        List<Muscle> muscles = muscleService.findAll();
        return ResponseEntity.ok(muscles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Muscle> findById(@PathVariable Long id) {
        Optional<Muscle> muscle = muscleService.findById(id);
        return muscle.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Muscle> create(@RequestBody Muscle muscle) {
        Muscle savedMuscle = muscleService.save(muscle);
        return new ResponseEntity<>(savedMuscle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Muscle> update(@PathVariable Long id, @RequestBody Muscle muscleDetails) {
        return muscleService.findById(id)
                .map(muscle -> {
                    muscle.setMuscleName(muscleDetails.getMuscleName());
                    muscle.setMuscleGroup(muscleDetails.getMuscleGroup());
                    Muscle updatedMuscle = muscleService.save(muscle);
                    return new ResponseEntity<>(updatedMuscle, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            muscleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
