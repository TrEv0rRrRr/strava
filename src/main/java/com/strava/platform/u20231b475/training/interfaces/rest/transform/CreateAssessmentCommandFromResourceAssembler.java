package com.strava.platform.u20231b475.training.interfaces.rest.transform;

import com.strava.platform.u20231b475.training.domain.model.commands.CreateAssessmentCommand;
import com.strava.platform.u20231b475.training.interfaces.rest.resources.CreateAssessmentResource;

/**
 * Assembler to convert a CreateAssessmentResource to a CreateAssessmentCommand.
 */
public class CreateAssessmentCommandFromResourceAssembler {
  /**
   * Converts a CreateAssessmentResource to a CreateAssessmentCommand
   * 
   * @param resource The {@link CreateAssessmentResource} resource to convert
   * @return The {@link CreateAssessmentCommand} command
   */
  public static CreateAssessmentCommand toCommandFromResource(CreateAssessmentResource resource) {
    return new CreateAssessmentCommand(
        resource.bmi(),
        resource.pushUps(),
        resource.plankTime(),
        resource.maxHeartRate(),
        resource.restingHeartRate(),
        resource.vo2max(),
        resource.encryptedText(),
        resource.type());
  }
}
