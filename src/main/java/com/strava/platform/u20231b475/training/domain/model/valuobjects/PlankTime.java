package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

/**
 * PlankTime Value Object
 * 
 * @author Valentino Solis
 */
@Embeddable
public record PlankTime(@NotNull @Min(0) @Max(600) Integer value) {
}
