package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

/**
 * PushUpCount value object
 * 
 * @author Valentino Solis
 */
@Embeddable
public record PushUpCount(@NotNull(message = "The push up count is required.") @Min(0) @Max(200) Integer value) {
}
