package com.example.myfitness.MyFitnessControllers;

import com.example.myfitness.Entity.User;
import com.example.myfitness.Entity.workoutPackage.ExerciseLibrary;
import com.example.myfitness.Entity.workoutPackage.UserExerciseLibrary;
import com.example.myfitness.Entity.workoutPackage.WorkoutDayExercise;
import com.example.myfitness.Entity.workoutPackage.WorkoutSplit;
import com.example.myfitness.Exception.MyFitnessUnAuthException;
import com.example.myfitness.Repository.UserRepository;
import com.example.myfitness.Repository.workoutRepos.ExerciseLibraryRepository;
import com.example.myfitness.Security.SecurityUtil;
import com.example.myfitness.Service.workoutServices.WorkoutCentralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RequestMapping("/main")
@RestController
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseLibraryRepository exerciseLibraryRepository;

    @GetMapping("/getUsers")
    public List<User> getUsers(){

        return userRepository.findAll();
    }

    @Autowired
    WorkoutCentralService workoutCentralService;

    @PostMapping("/saveSplit")
    public WorkoutSplit saveSplit(@RequestBody WorkoutSplit workoutSplit){
        Long userId = SecurityUtil.getCurrentUserId();

        if(userRepository.existsById(userId) && userId.equals(workoutSplit.getUserId()))
            return workoutCentralService.createWorkoutSplit(userId, workoutSplit);

        throw new MyFitnessUnAuthException();
    }

    @DeleteMapping("/deleteSplit")
    public void deleteSplit(@RequestParam Long id){
        workoutCentralService.deleteWorkoutSplit(id);
    }

    @GetMapping("getSplit")
    public WorkoutSplit getSplit(@RequestParam Long id){
        return workoutCentralService.getWorkoutSplitByUserId(id);
    }

    @PostMapping("saveExercise")
    public void saveExercise(@RequestBody WorkoutDayExercise workoutDayExercise){
        if(!isNull(workoutDayExercise) && workoutDayExercise.getPlanId() != null && (workoutDayExercise.getMasterExId() != null || workoutDayExercise.getUserExId() != null)){
            workoutCentralService.saveExercise(workoutDayExercise);
        }
        else
            throw new MyFitnessUnAuthException();
    }

    @PostMapping("saveUserExercise")
    public Long saveUserExercise(@RequestBody UserExerciseLibrary userExerciseLibrary){
        workoutCentralService.saveUserExerciseLibrary(userExerciseLibrary);
    }

//    @PostMapping("/seed")
//    public String seed() {
//        workoutCentralService.seedTestWorkoutForSplit2();
//        return "Seeded";
//    }
}
