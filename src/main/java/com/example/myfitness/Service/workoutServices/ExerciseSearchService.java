package com.example.myfitness.Service.workoutServices;

import com.example.myfitness.Entity.workoutPackage.ExerciseLibrary;
import com.example.myfitness.Entity.workoutPackage.UserExerciseLibrary;
import com.example.myfitness.Repository.workoutRepos.ExerciseLibraryRepository;
import com.example.myfitness.Repository.workoutRepos.UserExerciseLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseSearchService {

    @Autowired
    private ExerciseLibraryRepository masterRepo;

    @Autowired
    private UserExerciseLibraryRepository userRepo;

    // Unified suggestion list (master + user)
    public List<String> suggestExercises(Long userId, String query) {

        List<String> result = new ArrayList<>();

        // user custom first
        if (userId != null) {
            result.addAll(
                    userRepo.findTop10ByUserIdAndExNameStartingWithIgnoreCase(userId, query)
                            .stream()
                            .map(UserExerciseLibrary::getExName)
                            .toList()
            );
        }

        // then master
        result.addAll(
                masterRepo.findTop10ByExNameStartingWithIgnoreCase(query)
                        .stream()
                        .map(ExerciseLibrary::getExName)
                        .toList()
        );

        return result;
    }
}

