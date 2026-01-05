package com.example.myfitness.Service.workoutServices;

import com.example.myfitness.Entity.workoutPackage.UserExerciseLibrary;
import com.example.myfitness.Entity.workoutPackage.WorkoutDayExercise;
import com.example.myfitness.Entity.workoutPackage.WorkoutDayPlan;
import com.example.myfitness.Entity.workoutPackage.WorkoutSplit;
import com.example.myfitness.MyFitnessConstants.DayOfWeekEnum;
import com.example.myfitness.Repository.workoutRepos.WorkoutDayExerciseRepository;
import com.example.myfitness.Repository.workoutRepos.WorkoutDayPlanRepository;
import com.example.myfitness.Repository.workoutRepos.WorkoutSplitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutCentralService {
    @Autowired
    private WorkoutDayExerciseRepository workoutDayExerciseRepository;

    @Autowired
    private WorkoutSplitRepository  workoutSplitRepository;

    @Autowired
    private WorkoutDayPlanRepository  workoutDayPlanRepository;


    @Transactional
    public WorkoutSplit createWorkoutSplit(Long userid, WorkoutSplit split){
         WorkoutSplit workoutSplit = WorkoutSplit.builder()
                .userId(userid)
                .splitName(split.getSplitName())
                .isActive(true).build();

         workoutSplitRepository.save(workoutSplit);

        initializeWeek(workoutSplit);
        return workoutSplit;
    }

    public void initializeWeek(WorkoutSplit workoutSplit) {
        Long splitId = workoutSplit.getSplitId();

        initializeDay(splitId, DayOfWeekEnum.MONDAY,    "Monday",    false);
        initializeDay(splitId, DayOfWeekEnum.TUESDAY,   "Tuesday",   false);
        initializeDay(splitId, DayOfWeekEnum.WEDNESDAY, "Wednesday", false);
        initializeDay(splitId, DayOfWeekEnum.THURSDAY,  "Thursday",  false);
        initializeDay(splitId, DayOfWeekEnum.FRIDAY,    "Friday",    false);
        initializeDay(splitId, DayOfWeekEnum.SATURDAY,  "Saturday",  false);
        initializeDay(splitId, DayOfWeekEnum.SUNDAY,    "Sunday",    true);   // Rest day
    }

    private void initializeDay(Long splitId, DayOfWeekEnum day, String name, boolean isRest) {
        WorkoutDayPlan dayPlan = new WorkoutDayPlan();
        dayPlan.setSplitId(splitId);
        dayPlan.setDayOfWeek(day);
        dayPlan.setDayName(name);
        dayPlan.setIsRestDay(isRest);
        workoutDayPlanRepository.save(dayPlan);
    }


    public void deleteWorkoutSplit(Long workoutSplitId){
        workoutSplitRepository.deleteById(workoutSplitId);
    }
    public WorkoutSplit getWorkoutSplitByUserId(Long workoutSplitId){
        return workoutSplitRepository.findByUserId(workoutSplitId).get();
    }

    public void saveExercise(WorkoutDayExercise workoutDayExercise){
        workoutDayExerciseRepository.save(workoutDayExercise);
    }
    @Transactional
    public void seedTestWorkoutForSplit2() {

        // PUSH DAY
        WorkoutDayPlan pushDay = new WorkoutDayPlan();
        pushDay.setSplitId(6L);
        pushDay.setDayOfWeek(DayOfWeekEnum.MONDAY);
        pushDay.setDayName("Push Day");
        pushDay.setNotes("Chest, Shoulder & Triceps");
        pushDay.setIsRestDay(false);
        pushDay = workoutDayPlanRepository.save(pushDay);

        // PULL DAY
        WorkoutDayPlan pullDay = new WorkoutDayPlan();
        pullDay.setSplitId(6L);
        pullDay.setDayOfWeek(DayOfWeekEnum.TUESDAY);
        pullDay.setDayName("Pull Day");
        pullDay.setNotes("Back & Biceps");
        pullDay.setIsRestDay(false);
        pullDay = workoutDayPlanRepository.save(pullDay);

        // PUSH DAY EXERCISES
        workoutDayExerciseRepository.save(makeMasterExercise(pushDay.getPlanId(), 101, 4, 12, 1));
        workoutDayExerciseRepository.save(makeMasterExercise(pushDay.getPlanId(), 102, 3, 15, 2));

        // PULL DAY EXERCISES
        workoutDayExerciseRepository.save(makeMasterExercise(pullDay.getPlanId(), 201, 4, 10, 1));
        workoutDayExerciseRepository.save(makeMasterExercise(pullDay.getPlanId(), 202, 3, 12, 2));
    }

    private WorkoutDayExercise makeMasterExercise(Long planId, int masterExId, int sets, int reps, int seq) {
        WorkoutDayExercise ex = new WorkoutDayExercise();
        ex.setPlanId(planId);
        ex.setMasterExId(masterExId);
        ex.setUserExId(null);
        ex.setSets(sets);
        ex.setReps(reps);
        ex.setSeq(seq);
        ex.setRestSeconds(60);
        return ex;
    }

    public void saveUserExerciseLibrary(UserExerciseLibrary userExerciseLibrary) {

    }
}
