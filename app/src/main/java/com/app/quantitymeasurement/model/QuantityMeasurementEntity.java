package com.app.quantitymeasurement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "quantity_measurement",
        indexes = {
                @Index(name = "idx_operation", columnList = "operation"),
                @Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
                @Index(name = "idx_is_error", columnList = "is_error")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Double thisValue;

    @Column(nullable = true)
    private String thisUnit;

    @Column(name = "this_measurement_type", nullable = true)
    private String thisMeasurementType;

    @Column(nullable = true)
    private Double thatValue;

    @Column(nullable = true)
    private String thatUnit;

    @Column(nullable = true)
    private String thatMeasurementType;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = true)
    private String resultString;

    @Column(nullable = true)
    private Double resultValue;

    @Column(nullable = true)
    private String resultUnit;

    @Column(nullable = true)
    private String resultMeasurementType;

    @Column(nullable = true)
    private String errorMessage;

    @Column(name = "is_error", nullable = false)
    private boolean isError;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
