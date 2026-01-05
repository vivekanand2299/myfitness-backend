package com.example.myfitness.Repository.workoutRepos;

import com.example.myfitness.Entity.workoutPackage.ExerciseLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseLibraryRepository
        extends JpaRepository<ExerciseLibrary, Integer> {

    List<ExerciseLibrary> findTop10ByExNameStartingWithIgnoreCase(String q);

    List<ExerciseLibrary> findByMuscleGroup(String muscleGroup);
}

