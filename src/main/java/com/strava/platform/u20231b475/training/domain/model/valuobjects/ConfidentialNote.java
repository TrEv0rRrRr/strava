package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

/**
 * Confidential note value object
 * 
 * @author Valentino Solis
 */
@Embeddable
public record ConfidentialNote(
    @NotBlank(message = "The confidential note cannot be empty.") @Size(min = 15, max = 500, message = "The confidential note must be between 15 and 500 characters.") String encryptedText) {
}
