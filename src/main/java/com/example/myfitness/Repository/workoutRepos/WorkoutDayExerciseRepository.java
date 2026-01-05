package com.example.myfitness.Repository.workoutRepos;

import com.example.myfitness.Entity.workoutPackage.WorkoutDayExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutDayExerciseRepository extends JpaRepository<WorkoutDayExercise, Integer>
{
}
