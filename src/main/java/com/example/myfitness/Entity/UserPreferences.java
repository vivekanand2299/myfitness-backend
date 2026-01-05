package com.example.myfitness.Entity;

import com.example.myfitness.MyFitnessConstants.MeasurementSystem;
import com.example.myfitness.MyFitnessConstants.Theme;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "user_preferences",
        uniqueConstraints = @UniqueConstraint(columnNames = "user_id")
)
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    // One-to-One with User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "measurement_system")
    private MeasurementSystem measurementSystem = MeasurementSystem.METRIC;

    @Enumerated(EnumType.STRING)
    private Theme theme = Theme.AUTO;

    @Column(name = "notification_enabled")
    private Boolean notificationEnabled = true;

    @Column(name = "weekly_goal_days")
    private Integer weeklyGoalDays = 5;

    @Column(name = "target_weight_kg")
    private Double targetWeightKg;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
