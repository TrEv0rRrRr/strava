package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.validation.constraints.*;

public record CoachId(@NotNull(message = "The Id is required") @Min(1) Long Id) {
  public CoachId {
    if (Id <= 0)
      throw new IllegalArgumentException("The Id must be greater than 0");
  }
}
