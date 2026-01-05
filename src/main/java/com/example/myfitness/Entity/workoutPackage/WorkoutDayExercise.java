package com.example.myfitness.Entity.workoutPackage;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "workout_day_exercises")
public class WorkoutDayExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Long mapId;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    // If exercise comes from exercise_library
    @Column(name = "master_ex_id")
    private Integer masterExId;

    // If exercise comes from user_exercise_library
    @Column(name = "user_ex_id")
    private Integer userExId;

    private Integer sets;
    private Integer reps;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "rest_seconds")
    private Integer restSeconds;

    private Integer seq;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    // getters & setters
}

