package com.strava.platform.u20231b475.training.application.internal.commandservices;

import java.time.LocalDate;
import java.util.Optional;

import com.strava.platform.u20231b475.training.domain.model.aggregates.Assessment;
import com.strava.platform.u20231b475.training.domain.model.commands.CreateAssessmentCommand;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.AthleteId;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.CoachId;
import com.strava.platform.u20231b475.training.domain.services.AssessmentCommandService;
import com.strava.platform.u20231b475.training.infrastructure.persistence.jpa.repositories.AssessmentRepository;

/**
 * Assessment command service implementation
 * 
 * @author Valentino Solis
 */
public class AssessmentCommandServiceImpl implements AssessmentCommandService {
  private final AssessmentRepository repository;

  /**
   * Constructor
   * 
   * @param repository The {@link AssessmentRepository} instance
   */
  public AssessmentCommandServiceImpl(AssessmentRepository repository) {
    this.repository = repository;
  }

  // inherited javadoc
  @Override
  public Optional<Assessment> handle(CreateAssessmentCommand command) {
    var athleteId = new AthleteId(command.athleteId());
    var coachId = new CoachId(command.coachId());
    LocalDate date = LocalDate.now();
    if (repository.existsByAthleteIdAndCoachIdAndDate(athleteId, coachId, date)) {
      throw new IllegalArgumentException("An assessment for the given athlete, coach and date already exists.");
    }

    var assessment = new Assessment(command);
    repository.save(assessment);

    return Optional.of(assessment);
  }
}
