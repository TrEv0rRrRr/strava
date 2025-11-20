package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.validation.constraints.*;

public record CoachId(@NotNull(message = "The Id is required") @Min(1) Long id) {
  public CoachId {
    if (id <= 0)
      throw new IllegalArgumentException("The coach Id must be greater than 0");
  }
}
