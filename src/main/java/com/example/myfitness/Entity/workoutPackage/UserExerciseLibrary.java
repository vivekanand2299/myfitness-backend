package com.example.myfitness.Entity.workoutPackage;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_exercise_library")
public class UserExerciseLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private Integer exId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "ex_name")
    private String exName;

    @Column(columnDefinition = "TEXT")
    private String desc;

    private String type;

    @Column(name = "muscle_group")
    private String muscleGroup;

    private String equipment;

    private String level;

    private String rating;

    private String ratingdesc;

    @Column(name = "video_url", length = 500)
    private String videoUrl;

    // getters & setters
}

