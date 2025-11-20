package com.strava.platform.u20231b475.training.domain.model.aggregates;

import com.strava.platform.u20231b475.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.strava.platform.u20231b475.training.domain.model.commands.CreateAssessmentCommand;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.AssessmentStatus;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.AssessmentType;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.AthleteId;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.BMI;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.CardioMetrics;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.CoachId;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.ConfidentialNote;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.PlankTime;
import com.strava.platform.u20231b475.training.domain.model.valuobjects.PushUpCount;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Assessment Aggregate root
 * 
 * @author Valentino Solis
 */
@Entity
public class Assessment extends AuditableAbstractAggregateRoot<Assessment> {
  @Getter
  private LocalDate date;
  @Embedded
  @Getter
  @AttributeOverride(name = "id", column = @Column(name = "athlete_id"))
  private AthleteId athleteId;

  @Embedded
  @Getter
  @AttributeOverride(name = "id", column = @Column(name = "coach_id"))
  private CoachId coachId;

  @Embedded
  @Getter
  @AttributeOverride(name = "value", column = @Column(name = "bmi"))
  private BMI bmi;

  @Embedded
  @Getter
  @AttributeOverride(name = "value", column = @Column(name = "push_ups"))
  private PushUpCount pushUps;

  @Embedded
  @Getter
  @AttributeOverride(name = "value", column = @Column(name = "plank_time"))
  private PlankTime plankTime;

  @Embedded
  @Getter
  @AttributeOverrides({
      @AttributeOverride(name = "maxHeartRate", column = @Column(name = "max_heart_rate")),
      @AttributeOverride(name = "restingHeartRate", column = @Column(name = "resting_heart_rate")),
      @AttributeOverride(name = "vo2max", column = @Column(name = "vo2_max"))
  })
  private CardioMetrics cardioMetrics;

  @Embedded
  @Getter
  @AttributeOverride(name = "encryptedText", column = @Column(name = "encrypted_text"))
  private ConfidentialNote confidentialNote;

  @Enumerated(EnumType.STRING)
  @Getter
  private AssessmentType type;

  @Enumerated(EnumType.STRING)
  @Getter
  private AssessmentStatus status;

  public Assessment() {
  }

  public Assessment(CreateAssessmentCommand command) {
    this.athleteId = new AthleteId(command.athleteId());
    this.coachId = new CoachId(command.coachId());
    this.bmi = new BMI(command.bmi());
    this.pushUps = command.pushUps() != null ? new PushUpCount(command.pushUps()) : null;
    this.plankTime = command.plankTime() != null ? new PlankTime(command.plankTime()) : null;
    this.cardioMetrics = command.maxHeartRate() != null
        ? new CardioMetrics(command.maxHeartRate(), command.restingHeartRate(), command.vo2max())
        : null;
    this.confidentialNote = new ConfidentialNote(command.encryptedText());
    this.type = command.type();
    this.status = AssessmentStatus.RECORDED;
    this.date = LocalDate.now();

    validateTypeRules();
  }

  private void validateTypeRules() {
    switch (this.type) {

      case STRENGTH -> {
        if (this.pushUps == null || this.plankTime == null) {
          throw new IllegalArgumentException("For STRENGTH assessments, pushUps and plankTime are required.");
        }
      }

      case CARDIO -> {
        if (this.cardioMetrics == null) {
          throw new IllegalArgumentException("For CARDIO assessments, cardioMetrics must be provided.");
        }
      }

      case RECOVERY -> {
        if (this.cardioMetrics != null) {
          var r = this.cardioMetrics.restingHeartRate();
          if (r < 50 || r > 90) {
            // no es obligatorio, pero se recomienda: tú decides si lanzar excepción
            // o solo loguear/emitir un dominio event
            System.out.println("Warning: restingHeartRate is outside the recommended range for RECOVERY.");
          }
        }
      }

      default -> throw new IllegalArgumentException("Unknown assessment type: " + this.type);
    }
  }
}
