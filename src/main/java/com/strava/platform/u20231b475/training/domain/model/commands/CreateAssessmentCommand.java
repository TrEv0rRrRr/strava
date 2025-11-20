package com.strava.platform.u20231b475.training.domain.model.commands;

import java.math.BigDecimal;

import com.strava.platform.u20231b475.training.domain.model.valuobjects.AssessmentType;

/**
 * Create Assessment Command
 * 
 * @author Valentino Solis
 */
public record CreateAssessmentCommand(Long athleteId, Long coachId, BigDecimal bmi, Integer pushUps, Integer plankTime,
    Integer maxHeartRate, Integer restingHeartRate, Integer vo2max, String encryptedText, AssessmentType type) {
}
