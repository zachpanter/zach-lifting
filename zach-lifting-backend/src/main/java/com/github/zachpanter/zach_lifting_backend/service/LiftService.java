package com.github.zachpanter.zach_lifting_backend.service;

import com.github.zachpanter.zach_lifting_backend.model.Lift;
import com.github.zachpanter.zach_lifting_backend.repository.LiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiftService {

    private final LiftRepository liftRepository;

    @Autowired
    public LiftService(LiftRepository liftRepository) {
        this.liftRepository = liftRepository;
    }

    public List<Lift> getAllLifts() {
        return liftRepository.findAll();
    }

    public Optional<Lift> getLiftById(Long id) {
        return liftRepository.findById(id);
    }

    public Lift saveLift(Lift lift) {
        return liftRepository.save(lift);
    }

    public void deleteLift(Long id) {
        liftRepository.deleteById(id);
    }

    public Optional<Lift> getLiftByName(String liftName) {
        return liftRepository.findByLiftName(liftName);
    }
}