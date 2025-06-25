package com.ryanharis.app.match.common.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryanharis.app.match.common.models.enums.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchDto implements Serializable {
  @Serial
  private static final long serialVersionUID = 134363524246112L;
  @Schema(description = "The identifier of a match, auto generated upon creation, not needed to be provided by " +
      "client", example = "1")
  private Long id;
  @Schema(description = "The description of a match", example = "Greek Cup Final")
  private String description;
  @NotNull(message = "The date of the match cannot be null")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Date must be in dd/MM/yyyy format")
  @Schema(description = "The date when the match takes place in dd/MM/yyyy format", example = "30/07/2025")
  private String matchDate;
  @NotNull(message = "The time of the match cannot be null")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  @Pattern(regexp = "^(?:[01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time must be in HH:mm format (24-hour)")
  @Schema(description = "The time when the match takes place in HH:mm format", example = "21:30")
  private String matchTime;
  @NotNull(message = "Home team name cannot be null")
  @Schema(description = "The name of the home court team", example = "Panathinaikos BC")
  private String teamA;
  @NotNull(message = "Away team name cannot be null")
  @Schema(description = "The name of the away team", example = "Olympiacos BC")
  private String teamB;
  @NotNull(message = "The sport type cannot be null")
  @Schema(description = "The sport type of the match, enum value", example = "BASKETBALL")
  private Sport sport;
  @Valid
  @Schema(description = "A list containing the odds for all each specifier of this match")
  private List<MatchOddsDto> odds;
}
