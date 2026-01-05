package com.example.myfitness.Entity.workoutPackage;

import com.example.myfitness.MyFitnessConstants.DayOfWeekEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "workout_day_plans")
public class WorkoutDayPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "split_id", nullable = false)
    private Long splitId;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeekEnum dayOfWeek;

    @Column(name = "day_name")
    private String dayName;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "is_rest_day")
    private Boolean isRestDay = false;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    // getters & setters
}

