package com.example.myfitness.Repository.workoutRepos;

import com.example.myfitness.Entity.workoutPackage.UserExerciseLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExerciseLibraryRepository
        extends JpaRepository<UserExerciseLibrary, Integer> {

    List<UserExerciseLibrary> findTop10ByUserIdAndExNameStartingWithIgnoreCase(Long userId, String q);

    List<UserExerciseLibrary> findByUserId(Long userId);
}

