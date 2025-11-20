package com.strava.platform.u20231b475.training.domain.services;

import java.util.Optional;

import com.strava.platform.u20231b475.training.domain.model.aggregates.Assessment;
import com.strava.platform.u20231b475.training.domain.model.commands.CreateAssessmentCommand;

/**
 * Assessment Command Service
 */
public interface AssessmentCommandService {
  /**
   * Handle create an Assessment Command
   * 
   * @param command The {@link CreateAssessmentCommand} command
   * @return A {@link Assessment} instance if the command is valid, otherwise
   *         false
   * @throws IllegalArgumentException if the assessment already exists
   */
  Optional<Assessment> handle(CreateAssessmentCommand command);
}
