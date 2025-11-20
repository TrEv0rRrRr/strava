package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

/**
 * Confidential note value object
 * 
 * @author Valentino Solis
 */
@Embeddable
public record ConfidentialNote(@NotNull @NotBlank @Size(min = 15, max = 500) String encryptedText) {
}
