package com.ryanharis.app.match.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryanharis.app.match.common.models.enums.Specifier;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchOddsDto implements Serializable {
  @Serial
  private static final long serialVersionUID = 52983524246112L;
  @Schema(description = "The identifier of this odd for a match, auto created upon match creation, not needed to be " +
      "provided by client", example = "1")
  private Long id;
  @NotNull(message = "The odd specifier cannot be null")
  @Schema(description = "The name of the specific specifier the odd is about. Values 1,2 or X", example = "X")
  private Specifier specifier;
  @NotNull(message = "The actual match odd cannot be null")
  @Min(value = 1, message = "The actual match odd must be greater than 1")
  @Schema(description = "The actual odd in decimal format of this specifier", example = "2,40")
  private BigDecimal odd;
}
