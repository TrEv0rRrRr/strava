package com.strava.platform.u20231b475.training.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.strava.platform.u20231b475.training.domain.services.AssessmentCommandService;
import com.strava.platform.u20231b475.training.interfaces.rest.resources.AssessmentResource;
import com.strava.platform.u20231b475.training.interfaces.rest.resources.CreateAssessmentResource;
import com.strava.platform.u20231b475.training.interfaces.rest.transform.AssessmentResourceFromEntityAssembler;
import com.strava.platform.u20231b475.training.interfaces.rest.transform.CreateAssessmentCommandFromResourceAssembler;

/**
 * Assessments Controller
 */
@RestController
@RequestMapping(value = "/api/v1/coaches", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Assessments", description = "Available Assessment Endpoints")
public class AssessmentController {
  private final AssessmentCommandService commandService;

  /**
   * Constructor
   * 
   * @param commandService The {@link AssessmentCommandService} instance
   */
  public AssessmentController(AssessmentCommandService commandService) {
    this.commandService = commandService;
  }

  /**
   * Create a new assessment
   * 
   * @param resource The {@link CreateAssessmentResource} instance
   * @return A {@link AssessmentResource} resource for the created assessment, or
   *         a
   *         bad
   *         request response if the assessment could not be created.
   */
  @PostMapping("/{coachId}/athletes/{athleteId}/assessments")
  @Operation(summary = "Creates a new assessment")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = ""),
      @ApiResponse(responseCode = "400", description = "")
  })
  public ResponseEntity<AssessmentResource> createAssessment(@PathVariable Long coachId,
      @PathVariable Long athleteId, @Valid @RequestBody CreateAssessmentResource resource) {
    var createAssessmentCommand = CreateAssessmentCommandFromResourceAssembler.toCommandFromResource(resource);

    var assessment = commandService.handle(createAssessmentCommand);

    if (assessment.isEmpty())
      return ResponseEntity.badRequest().build();

    var createdAssessment = assessment.get();
    var assessmentResource = AssessmentResourceFromEntityAssembler.toResourceFromEntity(createdAssessment);

    return new ResponseEntity<>(assessmentResource, HttpStatus.CREATED);
  }
}
