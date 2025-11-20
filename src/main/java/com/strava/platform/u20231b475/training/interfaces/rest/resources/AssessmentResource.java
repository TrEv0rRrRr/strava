package com.strava.platform.u20231b475.training.interfaces.rest.resources;

import com.strava.platform.u20231b475.training.domain.model.valuobjects.AssessmentStatus;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.AssessmentType;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.AthleteId;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.BMI;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.CardioMetrics;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.CoachId;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.ConfidentialNote;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.PlankTime;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.PushUpCount;

/**
 * Resource for an assessment.
 */
public record AssessmentResource(Long id, AthleteId athleteId, CoachId coachId, BMI bmi, PushUpCount pushUps,
    PlankTime plankTime, CardioMetrics cardioMetrics, ConfidentialNote ConfidentialNote, AssessmentType type,
    AssessmentStatus status) {
}
