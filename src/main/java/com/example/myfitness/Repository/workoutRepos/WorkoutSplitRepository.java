package com.example.myfitness.Repository.workoutRepos;

import com.example.myfitness.Entity.workoutPackage.WorkoutSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutSplitRepository extends JpaRepository<WorkoutSplit, Long> {

    Optional<WorkoutSplit> findByUserId(Long userId);
}
