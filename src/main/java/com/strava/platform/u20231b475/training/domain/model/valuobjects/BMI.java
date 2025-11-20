package com.strava.platform.u20231b475.training.domain.model.valuobjects;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * BMI Value Object
 * 
 * @author Valentino Solis
 */
@Embeddable
public record BMI(@NotNull(message = "The BMI is required.") @DecimalMin("10.0") @DecimalMax("40.0") BigDecimal value) {
}
