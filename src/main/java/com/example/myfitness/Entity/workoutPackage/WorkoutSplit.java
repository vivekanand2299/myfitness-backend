package com.example.myfitness.Entity.workoutPackage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "workout_splits")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "split_id")
    private Long splitId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "split_name", nullable = false)
    private String splitName;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;


    // getters & setters
}

