package com.github.zachpanter.zach_lifting_backend.service;

import com.github.zachpanter.zach_lifting_backend.model.Muscle;
import com.github.zachpanter.zach_lifting_backend.repository.MuscleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MuscleService {

    private final MuscleRepository muscleRepository;

    @Autowired
    public MuscleService(MuscleRepository muscleRepository) {
        this.muscleRepository = muscleRepository;
    }

    public List<Muscle> findAll() {
        return muscleRepository.findAll();
    }

    public Optional<Muscle> findById(Long id) {
        return muscleRepository.findById(id);
    }

    public Muscle save(Muscle muscle) {
        return muscleRepository.save(muscle);
    }

    public void deleteById(Long id) {
        muscleRepository.deleteById(id);
    }

    public Optional<Muscle> findByName(String name) {
        return muscleRepository.findByMuscleName(name);
    }
}
