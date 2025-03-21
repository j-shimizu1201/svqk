package dev.aulait.svqk.interfaces.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.SortedSet;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class IssueDto {

  @Schema(required = true, readOnly = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Integer id;

  @Schema(required = true)
  @NotBlank
  private String subject;

  private String description;

  private LocalDate dueDate;

  @Schema(required = true)
  private IssueStatusDto issueStatus;

  @Schema(required = true)
  private TrackerDto tracker;

  @Schema(required = true)
  private Long version;

  @Schema(required = true, readOnly = true)
  private LocalDateTime updatedAt;

  @Schema(required = true, readOnly = true)
  private SortedSet<JournalDto> journals;
}
