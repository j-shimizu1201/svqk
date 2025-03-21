package dev.aulait.svqk.interfaces.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.aulait.svqk.arch.exception.ErrorResponseDto;
import dev.aulait.svqk.arch.test.ConstraintViolationResponseDto;
import dev.aulait.svqk.arch.test.ValidationMessageUtils;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import jakarta.ws.rs.core.Response.Status;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest // <.>
class IssueControllerIT {

  IssueClient client = new IssueClient(); // <.>

  @Test // <.>
  void testCrud() {
    IssueDto issue = IssueDataFactory.createRandomIssue(); // <.>

    // Create
    int issueId = client.create(issue); // <.>

    // Reference
    IssueDto createdIssue = client.get(issueId);
    assertEquals(issue.getSubject(), createdIssue.getSubject()); // <.>

    // Update
    createdIssue.setSubject("test subject: " + RandomStringUtils.randomAlphanumeric(5));
    client.update(
        IssueUpdateDto.builder().issue(createdIssue).journal(new JournalDto()).build(), issueId);

    IssueDto updatedIssue = client.get(issueId);

    assertEquals(createdIssue.getSubject(), updatedIssue.getSubject());

    // Delete
    int deletedId = client.delete(issueId, updatedIssue);
    assertEquals(deletedId, issueId);

    ErrorResponseDto error = client.getWithError(issueId);
    assertEquals(Status.NOT_FOUND, error.getStatus());
  }

  @Test
  void testCreateButValidationError() {
    IssueDto issue = new IssueDto();

    ConstraintViolationResponseDto error = client.createButValidationError(issue);

    assertEquals(
        ValidationMessageUtils.getNotBlankMsg(), error.getViolations().get(0).getMessage());
  }
}
