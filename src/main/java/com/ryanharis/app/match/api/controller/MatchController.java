package com.ryanharis.app.match.api.controller;

import com.ryanharis.app.match.api.exceptions.ApiErrorDetails;
import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.common.models.MatchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Matches API", description = "Operations on sport matches")
@RestController
@RequestMapping(value = "/api/v1/matches", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MatchController {

  @Operation(summary = "Retrieves all matches from database")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "A list with all the available matches. Can be empty if none " +
          "exists"),
      @ApiResponse(
          responseCode = "500",
          description = ApiErrorDetails.GENERIC_ERROR_CODE + "-" +
              ApiErrorDetails.GENERIC_ERROR_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class)))
  })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<MatchDto> getAll() throws MatchAppException;

  @Operation(summary = "Get a single match by id")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Match found"),
      @ApiResponse(
          responseCode = "404",
          description = ApiErrorDetails.NO_DATA_FOUND_WITH_DETAILS_CODE + "-" +
              ApiErrorDetails.NO_DATA_FOUND_WITH_DETAILS_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class))),
      @ApiResponse(
          responseCode = "500",
          description = ApiErrorDetails.GENERIC_ERROR_CODE + "-" +
              ApiErrorDetails.GENERIC_ERROR_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class)))
  })
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  MatchDto getById(@PathVariable Long id) throws MatchAppException;

  @Operation(summary = "Create a new match with its odds")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Created")
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  MatchDto create(@RequestBody @Valid MatchDto matchDto) throws MatchAppException;

  @Operation(summary = "Update an existing match by id")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Updated"),
      @ApiResponse(
          responseCode = "400",
          description = ApiErrorDetails.BAD_REQUEST_DET_CODE + "-" +
              ApiErrorDetails.BAD_REQUEST_DET_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class))),
      @ApiResponse(
          responseCode = "500",
          description = ApiErrorDetails.GENERIC_ERROR_CODE + "-" +
              ApiErrorDetails.GENERIC_ERROR_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class)))
  })
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  MatchDto update(@PathVariable Long id, @RequestBody @Valid MatchDto matchDto) throws MatchAppException;

  @Operation(summary = "Delete an existing match by id")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Deleted"),
      @ApiResponse(
          responseCode = "404",
          description = ApiErrorDetails.NO_DATA_FOUND_WITH_DETAILS_CODE + "-" +
              ApiErrorDetails.NO_DATA_FOUND_WITH_DETAILS_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class)
          )
      ),
      @ApiResponse(
          responseCode = "500",
          description = ApiErrorDetails.GENERIC_ERROR_CODE + "-" +
              ApiErrorDetails.GENERIC_ERROR_MSG,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiErrorDetails.class)))
  })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable Long id) throws MatchAppException;
}
