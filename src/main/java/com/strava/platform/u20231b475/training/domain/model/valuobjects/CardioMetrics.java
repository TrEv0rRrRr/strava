package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

/**
 * Cardio Metrics Value Object
 * 
 * @author Valentino Solis
 */
@Embeddable
public record CardioMetrics(
    @NotNull(message = "The max heart rate is required.") @Min(80) @Max(220) Integer maxHeartRate,
    @NotNull(message = "The resting heart rate is required.") @Min(30) @Max(120) Integer restingHeartRate,
    @NotNull(message = "The vo2max is required.") @Min(20) @Max(80) Integer vo2max) {

  public CardioMetrics {
    if (restingHeartRate > maxHeartRate)
      throw new IllegalArgumentException("The resting heart rate cannot be greater than the max heart rate.");
  }
}
