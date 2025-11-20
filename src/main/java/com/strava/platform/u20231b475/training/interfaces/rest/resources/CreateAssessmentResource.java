package com.strava.platform.u20231b475.training.interfaces.rest.resources;

import java.math.BigDecimal;

import com.strava.platform.u20231b475.training.domain.model.valuobjects.AssessmentType;

import jakarta.validation.constraints.*;

/**
 * Resource for creating an assessment
 */
public record CreateAssessmentResource(@NotNull @Min(1) Long athleteId,
        @NotNull @Min(1) Long coachId,
        @NotNull @DecimalMin("10.0") @DecimalMax("40.0") BigDecimal bmi,
        @NotNull @Min(0) @Max(200) Integer pushUps,
        @NotNull @Min(0) @Max(600) Integer plankTime,
        @NotNull @Min(80) @Max(220) Integer maxHeartRate,
        @NotNull @Min(30) @Max(120) Integer restingHeartRate,
        @NotNull @Min(20) @Max(80) Integer vo2max,
        @NotBlank String encryptedText,
        @NotNull AssessmentType type) {
}
