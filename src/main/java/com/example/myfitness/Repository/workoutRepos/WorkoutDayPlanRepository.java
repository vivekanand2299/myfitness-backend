package com.example.myfitness.Repository.workoutRepos;

import com.example.myfitness.Entity.workoutPackage.WorkoutDayPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutDayPlanRepository extends JpaRepository<WorkoutDayPlan, Long> {

}
