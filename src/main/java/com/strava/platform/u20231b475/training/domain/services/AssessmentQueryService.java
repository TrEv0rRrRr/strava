package com.strava.platform.u20231b475.training.domain.services;

import java.util.Optional;

import com.strava.platform.u20231b475.training.domain.model.aggregates.Assessment;
import com.strava.platform.u20231b475.training.domain.model.queries.GetAssessmentByAthleteIdAndCoachIdAndDateQuery;

/**
 * Assessment Query Service
 */
public interface AssessmentQueryService {
  /**
   * Handle get Assessment by athleteId, coachId and the actual date
   * 
   * @param query The {@link GetAssessmentByAthleteIdAndCoachIdAndDateQuery} query
   * @return A {@link Assessment} instance if the query is valid, otherwise empty
   */
  Optional<Assessment> handle(GetAssessmentByAthleteIdAndCoachIdAndDateQuery query);
}
