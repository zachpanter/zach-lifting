package com.github.zachpanter.zach_lifting_backend.service;

import com.github.zachpanter.zach_lifting_backend.model.MuscleGroupLookup;
import com.github.zachpanter.zach_lifting_backend.repository.LiftRepository;
import com.github.zachpanter.zach_lifting_backend.repository.MuscleGroupLookupRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MuscleGroupService {

    private final MuscleGroupLookupRepository muscleGroupLookupRepository;

    // MuscleGroupService is the constructor for MuscleGroupService
    @Autowired
    public MuscleGroupService(MuscleGroupLookupRepository muscleGroupLookupRepository) {
        this.muscleGroupLookupRepository = muscleGroupLookupRepository;
    }

    // getAllMuscleGroups retrieves all muscle groups
    public List<MuscleGroupLookup> getAllMuscleGroups() {
        return muscleGroupLookupRepository.findAll();
    }

    // getMuscleGroupById returns a specific muscle group given an id
    public Optional<MuscleGroupLookup> getMuscleGroupById(Long id) {
        return muscleGroupLookupRepository.findById(id);
    }

    // saveMuscleGroup handles the insertion logic for a given muscle group
    public MuscleGroupLookup saveMuscleGroup(MuscleGroupLookup muscleGroupLookup) {
        return muscleGroupLookupRepository.save(muscleGroupLookup);
    }

    // deleteMuscleGroup deletes a muscle group given an id
    public void deleteMuscleGroup(Long id) {
        muscleGroupLookupRepository.deleteById(id);
    }

    // getMuscleGroupByName tautologically retrieves the muscle groups info given its name
    public Optional<MuscleGroupLookup> getMuscleGroupByName(String name) {
        return muscleGroupLookupRepository.findByMuscleGroupName(name);
    }
}
